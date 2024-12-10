
ALTER TABLE ourusers ADD COLUMN role_id INT;

INSERT INTO roles (name) VALUES ('ADMIN'), ('USER'), ('MANAGER');

UPDATE ourusers SET role_id = (SELECT role_id FROM roles WHERE name = ourusers.role);

ALTER TABLE ourusers DROP COLUMN role;

ALTER TABLE ourusers MODIFY role_id INT NOT NULL;
ALTER TABLE ourusers ADD CONSTRAINT fk_ourusers_role FOREIGN KEY (role_id) REFERENCES roles(role_id);


