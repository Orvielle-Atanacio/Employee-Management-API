DROP TABLE IF EXISTS employee CASCADE;
DROP TABLE IF EXISTS departments CASCADE;
DROP TABLE IF EXISTS roles CASCADE;
DROP TABLE IF EXISTS members CASCADE;

CREATE TABLE IF NOT EXISTS members (
    user_id VARCHAR(50) PRIMARY KEY,
    pw VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    user_id VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES members(user_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS employee (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department_id INT NOT NULL,
    CONSTRAINT fk_department FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE CASCADE
);

DELETE FROM employee;
DELETE FROM departments;
DELETE FROM roles;
DELETE FROM members;

INSERT INTO members (user_id, pw, active) VALUES
('employee', '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', true),
('manager',  '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', true),
('admin',    '{bcrypt}$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', true);

INSERT INTO roles (user_id, role) VALUES
('employee', 'ROLE_EMPLOYEE'),
('manager',  'ROLE_MANAGER'),
('manager',  'ROLE_EMPLOYEE'),
('admin',    'ROLE_ADMIN'),
('admin',    'ROLE_EMPLOYEE');

INSERT INTO departments (name) VALUES
('Engineering'),
('Human Resources'),
('Marketing'),
('Sales'),
('Finance');

INSERT INTO employee (first_name, last_name, email, department_id) VALUES
('John',    'Doe',     'john.doe@company.com', 1),
('Jane',    'Smith',   'jane.smith@company.com', 1),
('Maria',   'Santos',  'maria.santos@company.com', 2),
('Michael', 'Chen',    'michael.chen@company.com', 3),
('Sarah',   'Johnson', 'sarah.johnson@company.com', 4),
('David',   'Brown',   'david.brown@company.com', 5);
