CREATE DATABASE IF NOT EXISTS api_tpo_db;
CREATE USER 'api_tpo_db_usr'@'localhost' IDENTIFIED BY 'api_tpo_db_pass';
GRANT ALL PRIVILEGES ON *.* TO 'api_tpo_db_usr'@'localhost';