INSERT INTO users
VALUES
    (1, 'Johnson', '', 'Nick', '1981-12-11', 'M', 42, 'description', false, '2022-06-01 23.59.59', '2023-06-01 23.59.59'),
    (2, 'Marley', '', 'Bob', '1945-02-06', 'M', 78, 'yo man', false, '2022-06-02 23.59.59', '2023-06-02 23.59.59');

INSERT INTO company (id, company_name, is_activity, created)
VALUES
    ('1', 'Horns and hooves LLC', true, '2023-06-01 23.59.59'),
    ('2', 'Microogle', true, '2020-01-13 23.59.59');

INSERT INTO userjob_info (id, user_id, company_id,  is_activity, created)
VALUES
    ('1', '1', '1', true, '2023-06-03 23.59.59'),
    ('2', '1', '2', false, '2023-06-04 23.59.59');