INSERT INTO permissions (name) VALUES
                                   ('VIEW_USERS'),
                                   ('ADD_USER'),
                                   ('UPDATE_USER'),
                                   ('DELETE_USER'),
                                   ('VIEW_ROLES'),
                                   ('ADD_ROLE'),
                                   ('DELETE_ROLE'),
                                   ('VIEW_PERMISSIONS'),
                                   ('ADD_PERMISSION'),
                                   ('REMOVE_PERMISSION_FROM_ROLE'),
                                   ('ASSIGN_PERMISSION_TO_ROLE'),
                                   ('ASSIGN_ROLE_TO_USER');

-- Assign some permissions to ADMIN role
INSERT INTO role_permission (role_id, permission_id)
SELECT (SELECT role_id FROM roles WHERE name='ADMIN'), permission_id FROM permissions;

-- For USER or MANAGER, assign some permissions
-- MANAGER do  view/add/update/delete users:
INSERT INTO role_permission (role_id, permission_id)
SELECT (SELECT role_id FROM roles WHERE name='MANAGER'), permission_id
FROM permissions
WHERE name IN ('VIEW_USERS','ADD_USER','UPDATE_USER','DELETE_USER');
