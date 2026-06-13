psql "postgresql://postgres:postgres@localhost:5432/"
CREATE USER mochicafe WITH PASSWORD 'mochicafe';
CREATE DATABASE mochicafe OWNER mochicafe;
quit;