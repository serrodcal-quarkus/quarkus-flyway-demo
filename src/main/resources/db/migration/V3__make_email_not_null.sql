UPDATE persons SET email = 'default@example.com' WHERE email IS NULL;

ALTER TABLE persons ALTER COLUMN email SET NOT NULL;

