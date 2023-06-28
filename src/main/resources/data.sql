INSERT INTO users (id, family_name, birthday, gender, age, is_blocked, created)
VALUES
    (1000, 'User1', '1901-01-01', 'M', 41, false, '2000-01-01 00.00.01'),
    (1001, 'User2', '1902-01-01', 'F', 42, false, '2000-01-01 00.00.02'),
    (1002, 'User3', '1903-01-01', 'F', 43, false, '2000-01-01 00.00.03');

INSERT INTO company (id, company_name, is_activity, created)
VALUES
    (1000, 'Horns and hooves LLC', true, '2023-06-01 23.59.59'),
    (1001, 'Microogle', true, '2020-01-13 23.59.59');

INSERT INTO userjob_info (id, user_id, company_id,  is_activity, created)
VALUES
    (1000, 1001, 1000, true, '2001-01-01 00.00.01'),
    (1001, 1002, 1000, false, '2001-01-01 00.00.02');