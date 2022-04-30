USE mysql;
UPDATE user SET authentication_string=PASSWORD('root') WHERE User='root';
FLUSH PRIVILEGES;