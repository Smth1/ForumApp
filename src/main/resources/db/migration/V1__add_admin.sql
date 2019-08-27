INSERT INTO usr (id, username, password, active)
    values (2, 'admin', '123', true);

INSERT INTO user_role (user_id, roles)
    values (2,'USER'), (2,'ADMIN');