-- src/main/resources/db/migration/V2__add_email_to_person.sql
ALTER TABLE person ADD COLUMN email VARCHAR(255);