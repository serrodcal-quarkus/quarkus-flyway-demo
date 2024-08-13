# Baseline

## Setting up

Start PostgreSQL:

```shell
docker run --rm --name postgres -p 5432:5432 -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -d postgres:14
```

Get into container:

```shell
docker exec -ti postgres bash
```

Log into database:

```shell
psql -U quarkus_test
```

Create table and sequence manually:

```shell
CREATE TABLE persons (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);
  
CREATE SEQUENCE persons_seq start with 1 increment by 1;
```

Start the application:

```shell
./mvnw clean quarkus:dev   
```

## Create baseline

Uncomment flyway properties as follows:

```
quarkus.flyway.migrate-at-start=true
quarkus.flyway.schemas=public
quarkus.flyway.baseline-on-migrate=true
quarkus.flyway.baseline-version=1
```

Create `V1__init_flyway.sql` in `src/main/resources/db/migration` as empty file.

Start the application:

```shell
./mvnw clean quarkus:dev   
```

You see in console output that Flyway has created a new version starting in that point:

```
2024-08-13 14:37:08,415 INFO  [org.fly.cor.int.sch.JdbcTableSchemaHistory] (Quarkus Main Thread) Schema history table "public"."flyway_schema_history" does not exist yet
2024-08-13 14:37:08,417 INFO  [org.fly.cor.int.com.DbValidate] (Quarkus Main Thread) Successfully validated 1 migration (execution time 00:00.006s)
2024-08-13 14:37:08,429 INFO  [org.fly.cor.int.sch.JdbcTableSchemaHistory] (Quarkus Main Thread) Creating Schema History table "public"."flyway_schema_history" with baseline ...
2024-08-13 14:37:08,447 INFO  [org.fly.cor.int.com.DbBaseline] (Quarkus Main Thread) Successfully baselined schema with version: 1
2024-08-13 14:37:08,471 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Current version of schema "public": 1
2024-08-13 14:37:08,472 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Schema "public" is up to date. No migration necessary.
```

## Modify database using Flyway

Create `V2__add_email.sql` in `src/main/resources/db/migration` with:

```
ALTER TABLE persons ADD COLUMN email VARCHAR(255);
```

Add the necessary code, edit `application.properties` to have only `quarkus.flyway.migrate-at-start=true` and start up again the application:

```shell
./mvnw clean quarkus:dev   
```

You see in console output that Flyway has created a new version with the modification:

```
2024-08-13 14:38:12,803 INFO  [org.fly.cor.int.com.DbValidate] (Quarkus Main Thread) Successfully validated 3 migrations (execution time 00:00.009s)
2024-08-13 14:38:12,824 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Current version of schema "public": 1
2024-08-13 14:38:12,830 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Migrating schema "public" to version "2 - add email"
2024-08-13 14:38:12,843 INFO  [org.fly.cor.int.com.DbMigrate] (Quarkus Main Thread) Successfully applied 1 migration to schema "public", now at version v2 (execution time 00:00.006s)
```

## Checking out history version

Check out history version as follows:

```
quarkus_test=# select * from flyway_schema_history;
 installed_rank | version |      description      |   type   |             script             |  checksum   | installed_by |        installed_on        | execution_time | success 
----------------+---------+-----------------------+----------+--------------------------------+-------------+--------------+----------------------------+----------------+---------
              1 | 1       | << Flyway Baseline >> | BASELINE | << Flyway Baseline >>          |             | quarkus_test | 2024-08-13 14:37:08.428274 |              0 | t
              2 | 2       | add email             | SQL      | db/migration/V2__add_email.sql | -1029565624 | quarkus_test | 2024-08-13 14:38:12.822376 |              6 | t
(2 rows)
```
