# SCENARIOS

Here, let's work on several scenarios to learn flyway and to be familiar with DB migrations.

DB migrations are needed when a new application is developed. Among iterations, the applications includes new features which require some change in databases.

## Setting up

Just run the following command:

```shell
docker run --rm --name postgres -p 5432:5432 -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -d postgres:14
```

## Checking out

First, get into postgres container:

```shell
docker exec -ti postgres bash
```

Then, log into `psql`:

```shell
psql -U quarkus_test
```

You should be in `quarkus_test` database, if not just run `\c quarkus_test`.

Describe the table:

```shell
\d persons;
```

Get tuples with:

```shell
select * from persons;
```

## V1

First, let's move to the first scenario branch

```shell
git switch test_v1
```

Compile:

```shell
./mvnw clean package -DskipTests
```

Run the application:

```
java -jar target/quarkus-app/quarkus-run.jar 
```

Finally, import `Flyway.postman_collection.json` in Postman.

## V2

First, let's move to the first scenario branch

```shell
git switch test_v2
```

Compile:

```shell
./mvnw clean package -DskipTests
```

Run the application:

```
java -jar target/quarkus-app/quarkus-run.jar 
```

Finally, import `Flyway.postman_collection.json` in Postman.

## V3

First, let's move to the first scenario branch

```shell
git switch test_v3
```

Compile:

```shell
./mvnw clean package -DskipTests
```

Run the application:

```
java -jar target/quarkus-app/quarkus-run.jar 
```

Finally, import `Flyway.postman_collection.json` in Postman.
