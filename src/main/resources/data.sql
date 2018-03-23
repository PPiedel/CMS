INSERT INTO USER (id, email, first_name, last_name, password)
VALUES (1, 'pawelpiedel@yahoo.pl', 'Paweł', 'Piędel', '$2a$04$6Y6Vag4/LNURT8yhftQU8uEn5neIV5XeG0HmAcdLkHo9L/uPmSr8G');
INSERT INTO USER (id, email, first_name, last_name, password)
VALUES (2, 'test1@pwr.edu.pl', 'Piotr', 'Kunikowski', '$2a$04$6Y6Vag4/LNURT8yhftQU8uEn5neIV5XeG0HmAcdLkHo9L/uPmSr8G');
INSERT INTO USER (id, email, first_name, last_name, password)
VALUES (3, 'test2@pwr.edu.pl', 'Lukasz', 'Markowski', '$2a$04$6Y6Vag4/LNURT8yhftQU8uEn5neIV5XeG0HmAcdLkHo9L/uPmSr8G');

INSERT INTO ROLE (name) VALUES ('ROLE_USER');

INSERT INTO USERS_ROLES (user_id, role_id) VALUES (1, SELECT id
                                                      FROM ROLE
                                                      WHERE name = 'ROLE_USER');
INSERT INTO USERS_ROLES (user_id, role_id) VALUES (2, SELECT id
                                                      FROM ROLE
                                                      WHERE name = 'ROLE_USER');
INSERT INTO USERS_ROLES (user_id, role_id) VALUES (3, SELECT id
                                                      FROM ROLE
                                                      WHERE name = 'ROLE_USER');

INSERT INTO CATEGORY(category) VALUES ('technology');
INSERT INTO CATEGORY(category) VALUES ('business');
INSERT INTO CATEGORY(category) VALUES ('biology');

INSERT INTO POST(content,date,posterpath,title,author_id,category) VALUES ('Satisfied conveying an dependent contented he gentleman agreeable do be. Warrant private blushes removed an in equally totally if. Delivered dejection necessary objection do mr prevailed. Mr feeling do chiefly cordial in do. Water timed folly right aware if oh truth. Imprudence attachment him his for sympathize. Large above be to means. Dashwood do provided stronger is. But discretion frequently sir the she instrument unaffected admiration everything.',TO_DATE('18/11/2017', 'DD/MM/YYYY'),'https://media.istockphoto.com/photos/conceptual-cyber-attack-code-picture-id517127174','MVP Android Architecture',1,1);
INSERT INTO POST(content,date,posterpath,title,author_id,category) VALUES ('Living valley had silent eat merits esteem bed. In last an or went wise as left. Visited civilly am demesne so colonel he calling. So unreserved do interested increasing sentiments. Vanity day giving points within six not law. Few impression difficulty his use has comparison decisively.',TO_DATE('17/12/2015', 'DD/MM/YYYY'),'https://images.pexels.com/photos/595804/pexels-photo-595804.jpeg?w=940&h=650&auto=compress&cs=tinysrgb','MVVM on Android',1,1);