# SCENARIOS

## Setting up

Just run the following command:

```shell
docker run --rm --name postgres -p 5432:5432 -e POSTGRES_USER=quarkus_test -e POSTGRES_PASSWORD=quarkus_test -e POSTGRES_DB=quarkus_test -d postgres:14
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
