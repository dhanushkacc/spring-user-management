CREATE TABLE roles (
                       role_id INT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE permissions (
                             permission_id INT AUTO_INCREMENT PRIMARY KEY,
                             name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE role_permission (
                                 role_id INT NOT NULL,
                                 permission_id INT NOT NULL,
                                 PRIMARY KEY (role_id, permission_id),
                                 FOREIGN KEY (role_id) REFERENCES roles(role_id) ON DELETE CASCADE,
                                 FOREIGN KEY (permission_id) REFERENCES permissions(permission_id) ON DELETE CASCADE
);
