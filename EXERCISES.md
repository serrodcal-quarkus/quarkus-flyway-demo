# FLYWAY EXERCISES

## 1. Initial database creation and initial values

`V1__create_database.sql`:

```sql
CREATE TABLE persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL
);
```

`V1.1__initial_inserts.sql`:

```sql
INSERT INTO persons (name, age) VALUES ('John Foden', 30), ('Anna Deyoe', 25), ('David Sanchez', 35);
```

## 2. New colums

`V2__alter_table_persons.sql`:

```sql
ALTER TABLE persons ADD COLUMN email VARCHAR(150) UNIQUE;

ALTER TABLE persons ADD COLUMN birth_date DATE;

UPDATE persons SET birth_date = '1990-01-01' WHERE name = 'John Foden';
UPDATE persons SET birth_date = '1995-06-15' WHERE name = 'Anna Deyoe';
UPDATE persons SET birth_date = '1985-03-10' WHERE name = 'David Sanchez';
```

## 3. Add relations

`V3__create_table_address.sql`:

```sql
CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    street VARCHAR(150) NOT NULL,
    city VARCHAR(100) NOT NULL,
    person_id INT NOT NULL,
    CONSTRAINT fk_person FOREIGN KEY (person_id) REFERENCES persons(id)
);

INSERT INTO addresses (street, city, person_id) VALUES ('Calle Falsa 123', 'Madrid', 1), 
('Avenida Siempre Viva 742', 'Barcelona', 2), ('Calle Luna 456', 'Sevilla', 3);
```

## 4. Rename colums

`V4__rename_column_name.sql`:

```sql
ALTER TABLE persons RENAME COLUMN name TO full_name;
```

## 5. Migrate data to a new colum

`V5__split_full_name.sql`:

```sql
ALTER TABLE persons ADD COLUMN name VARCHAR(100);
ALTER TABLE persons ADD COLUMN surname VARCHAR(100);

UPDATE persons SET name = split_part(full_name, ' ', 1);
UPDATE persons SET surname = split_part(full_name, ' ', 2);

ALTER TABLE persons DROP COLUMN full_name;
```



