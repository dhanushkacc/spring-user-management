CREATE TABLE ourusers (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          name VARCHAR(255) NOT NULL,
                          password VARCHAR(255) NOT NULL,
                          city VARCHAR(255),
                          role VARCHAR(255) NOT NULL
);