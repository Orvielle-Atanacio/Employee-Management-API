-- Clear existing data (Important: delete in correct order to avoid foreign key constraints)
DELETE FROM employees;
DELETE FROM departments;
DELETE FROM roles;
DELETE FROM members;

-- Insert test users (passwords are all 'password' encrypted with BCrypt)
INSERT INTO members (user_id, pw, active) VALUES
('employee', '$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', true),
('manager', '$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', true),
('admin', '$2a$10$qeS0HEh7urweMojsnwNAR.vcXJeXR1UcMRZ2WcGQl9YeuspUdgF.q', true);

-- Insert roles for these users
INSERT INTO roles (user_id, role) VALUES
('employee', 'ROLE_EMPLOYEE'),
('manager', 'ROLE_MANAGER'),
('manager', 'ROLE_EMPLOYEE'),
('admin', 'ROLE_ADMIN'),
('admin', 'ROLE_EMPLOYEE');

-- Insert sample departments
INSERT INTO departments (name) VALUES
('Engineering'),
('Human Resources'),
('Marketing'),
('Sales'),
('Finance');

-- Insert sample employees
INSERT INTO employees (first_name, last_name, email, department_id) VALUES
('John', 'Doe', 'john.doe@company.com', 1),
('Jane', 'Smith', 'jane.smith@company.com', 1),
('Maria', 'Santos', 'maria.santos@company.com', 2),
('Michael', 'Chen', 'michael.chen@company.com', 3),
('Sarah', 'Johnson', 'sarah.johnson@company.com', 4),
('David', 'Brown', 'david.brown@company.com', 5);