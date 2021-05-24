insert into authority (role) values ('ROLE_REGISTERED_USER');
insert into authority (role) values ('ROLE_ADMIN');


insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified, date_of_birth, location_lat, location_lon, profession)
values ('registered_user', true, 'jana@gmail.com', 'Jana', 'Mara', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true, '1995-02-08 23:40:37' , 44.25, 21.56, 'pizza seller');
insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified, date_of_birth, location_lat, location_lon, profession)
values ('registered_user', true, 'nedicteodora1@gmail.com', 'Teodora', 'Nedic', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true, '1998-06-01 23:40:37' , 44.25, 21.56, 'software engineer');
insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified, date_of_birth, location_lat, location_lon, profession)
values ('registered_user', true, 'natasamirkov43@gmail.com', 'Milica', 'Poparic', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true, '1998-11-05 23:40:37' , 44.25, 21.56, 'software engineer');
insert into users_authority (user_id, authority_id) values (1, 1);
insert into users_authority (user_id, authority_id) values (2, 1);
insert into users_authority (user_id, authority_id) values (3, 1);

insert into category (name, active) values ('shopping', true);
insert into category (name, active) values ('concerts', true);
insert into category (name, active) values ('museums', true);
insert into category (name, active) values ('mountains', true);
insert into category (name, active) values ('sea', true);
insert into category (name, active) values ('night life', true);
insert into category (name, active) values ('history', true);
insert into category (name, active) values ('spa', true);
insert into category (name, active) values ('sailing', true);
insert into category (name, active) values ('adventure', true);
insert into category (name, active) values ('hiking', true);
insert into category (name, active) values ('luna park', true);
insert into category (name, active) values ('city', true);

insert into destination (location_lat, location_lon, name, score, trending, active)
values (40.41, -3.70, 'Madrid', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (51.41, -0.12, 'London', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (48.41, 2.70, 'Paris', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (41.41, 12.70, 'Rome', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (52.52, 13.40, 'Berlin', 0, false, true);

insert into destination_categories (destination_id, categories_id) values (1, 1);
insert into destination_categories (destination_id, categories_id) values (1, 3);
insert into destination_categories (destination_id, categories_id) values (1, 13);

insert into destination_categories (destination_id, categories_id) values (2, 1);
insert into destination_categories (destination_id, categories_id) values (2, 2);
insert into destination_categories (destination_id, categories_id) values (2, 6);

insert into destination_categories (destination_id, categories_id) values (3, 1);
insert into destination_categories (destination_id, categories_id) values (3, 3);
insert into destination_categories (destination_id, categories_id) values (3, 13);

insert into destination_categories (destination_id, categories_id) values (4, 1);
insert into destination_categories (destination_id, categories_id) values (4, 7);
insert into destination_categories (destination_id, categories_id) values (4, 13);

insert into destination_categories (destination_id, categories_id) values (5, 1);
insert into destination_categories (destination_id, categories_id) values (5, 3);
insert into destination_categories (destination_id, categories_id) values (5, 12);


----- FOOD
insert into destination_local_food (destination_id, local_food) values (1, 5);
insert into destination_local_food (destination_id, local_food) values (2, 6);
insert into destination_local_food (destination_id, local_food) values (2, 1);
insert into destination_local_food (destination_id, local_food) values (3, 9);
insert into destination_local_food (destination_id, local_food) values (4, 2);
insert into destination_local_food (destination_id, local_food) values (5, 6);
insert into destination_local_food (destination_id, local_food) values (5, 0);

----- TRANSPORTATION
insert into destination_transportation (destination_id, transportation) values (1, 0);
insert into destination_transportation (destination_id, transportation) values (1, 2);
insert into destination_transportation (destination_id, transportation) values (1, 4);
insert into destination_transportation (destination_id, transportation) values (2, 0);
insert into destination_transportation (destination_id, transportation) values (2, 3);
insert into destination_transportation (destination_id, transportation) values (3, 0);
insert into destination_transportation (destination_id, transportation) values (3, 2);
insert into destination_transportation (destination_id, transportation) values (3, 4);
insert into destination_transportation (destination_id, transportation) values (4, 0);
insert into destination_transportation (destination_id, transportation) values (4, 3);
insert into destination_transportation (destination_id, transportation) values (4, 4);
insert into destination_transportation (destination_id, transportation) values (5, 0);
insert into destination_transportation (destination_id, transportation) values (5, 1);
insert into destination_transportation (destination_id, transportation) values (5, 2);
insert into destination_transportation (destination_id, transportation) values (5, 4);

----- HOTELS
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alta Mar', 4, true, 1, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Mandarin Oriental Ritz', 5, true, 1, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Four Seasons Madrid', 5, true, 1, false);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Britannia International', 2, true, 2, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Smart Hyde Park View', 3, true, 2, false);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Le Regent Monmartre', 5, true, 3, false);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Hotel Paris', 4, true, 3, false);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Shangri La', 5, true, 3, false);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Palazzo Manfredi', 4, true, 4, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Otivm Hotel', 2, true, 4, false);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 5, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 5, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 5, true);

---- USER INTERESTS
insert into users_interests (registered_user_id, interests_id) values (1, 1);
insert into users_interests (registered_user_id, interests_id) values (1, 3);
insert into users_interests (registered_user_id, interests_id) values (1, 13);

insert into users_interests (registered_user_id, interests_id) values (2, 1);
insert into users_interests (registered_user_id, interests_id) values (2, 7);
insert into users_interests (registered_user_id, interests_id) values (2, 12);

insert into users_interests (registered_user_id, interests_id) values (3, 3);
insert into users_interests (registered_user_id, interests_id) values (3, 7);
insert into users_interests (registered_user_id, interests_id) values (3, 9);








