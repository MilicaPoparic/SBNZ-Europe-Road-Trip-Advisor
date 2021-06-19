insert into authority (role) values ('ROLE_REGISTERED_USER');
insert into authority (role) values ('ROLE_ADMIN');

insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified)
values ('admin', true, 'admin@gmail.com', 'Admin', 'Admin', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true);


insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified, date_of_birth, location_lat, location_lon, profession)
values ('registered_user', true, 'milicapoparic@gmail.com', 'Jana', 'Mara', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true, '1995-02-08 23:40:37' , 44.25, 21.56, 'employed');
insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified, date_of_birth, location_lat, location_lon, profession)
values ('registered_user', true, 'nedicteodora1@gmail.com', 'Teodora', 'Nedic', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true, '1998-06-01 23:40:37' , 44.25, 21.56, 'employed');
insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified, date_of_birth, location_lat, location_lon, profession)
values ('registered_user', true, 'natasamirkov43@gmail.com', 'Milica', 'Poparic', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true, '1998-11-05 23:40:37' , 44.25, 21.56, 'unemployed');
insert into users_authority (user_id, authority_id) values (2, 1);
insert into users_authority (user_id, authority_id) values (3, 1);
insert into users_authority (user_id, authority_id) values (4, 1);
insert into users_authority (user_id, authority_id) values (1, 2);


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
------------------------------------------------
insert into destination (location_lat, location_lon, name, score, trending, active)
values (59.93, 30.30, 'St Petersburg', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (38.71, -9.13,'Lisbon', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (40.62, 22.94, 'Thessaloniki', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (39.46, -0.37, 'Valencia', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (43.67, 7.28, 'Nice', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (51.20, 3.22, 'Brugges', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (45.25, 19.83, 'Novi Sad', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (55.75, 37.61, 'Moscow', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (41.39, 2.15, 'Barcelona', 0, false, true);
insert into destination (location_lat, location_lon, name, score, trending, active)
values (48.21, 16.36, 'Vienna', 0, false, true);


insert into destination_images (destination_id, images) values (1, 'https://www.olympic.rs/wp-content/uploads/formidable/2/madridnaslovnafotografija.jpg');
insert into destination_images (destination_id, images) values (2, 'https://i1.wp.com/www.centarzaafirmacijuirazvoj.org/wp-content/uploads/2020/04/76709-640x360-houses-of-parliament-and-london-eye-on-thames-from-above-640.jpg?resize=640%2C300&ssl=1');
insert into destination_images (destination_id, images) values (3, 'https://media.tacdn.com/media/attractions-splice-spp-674x446/07/03/1c/9c.jpg');
insert into destination_images (destination_id, images) values (4, 'https://lordtravel.rs/wp-content/uploads/2019/09/rome.jpg');
insert into destination_images (destination_id, images) values (5, 'https://cdn.flixbus.de/city_description_images/berlin-culture-history.jpg');
----------------------------
insert into destination_images (destination_id, images) values (6, 'https://www.vivatravel.rs/photo/64932/p/16:10');
insert into destination_images (destination_id, images) values (7, 'https://mediterranean.observer/wp-content/uploads/2021/05/tram-lisbon-portugal-shutterstock_1222605877.jpg');
insert into destination_images (destination_id, images) values (8, 'https://www.goadventuremom.com/wp-content/uploads/2021/06/2-thessaloniki_and_the_white_tower_from_above-1.jpg');
insert into destination_images (destination_id, images) values (9, 'https://www.wep.travel/Uploads/Image/Image/4452/Spain-Valencia-02.jpg');
insert into destination_images (destination_id, images) values (10, 'https://lp-cms-production.imgix.net/2019-06/3cb45f6e59190e8213ce0a35394d0e11-nice.jpg?auto=compress&fit=crop&fm=auto&sharp=10&vib=20&w=1200&h=800');
insert into destination_images (destination_id, images) values (11, 'https://thenomadvisor.com/wp-content/uploads/2020/07/wheretostayinbruge.jpg');
insert into destination_images (destination_id, images) values (12, 'https://media.tacdn.com/media/attractions-splice-spp-674x446/0a/6d/5e/59.jpg');
insert into destination_images (destination_id, images) values (13, 'https://upload.wikimedia.org/wikipedia/commons/8/85/Saint_Basil%27s_Cathedral_and_the_Red_Square.jpg');
insert into destination_images (destination_id, images) values (14, 'https://cdn.vox-cdn.com/thumbor/9zHVj4OnM5pYeO8rCX-W4aL-lw0=/0x0:4428x1993/1200x800/filters:focal(1872x1198:2580x1906)/cdn.vox-cdn.com/uploads/chorus_image/image/63371518/shutterstock_785442694.0.jpg');
insert into destination_images (destination_id, images) values (15, 'https://i.guim.co.uk/img/media/44907381ba02489d60c01ed449fbbef4f82c9bd4/0_579_8688_5213/master/8688.jpg?width=1200&height=900&quality=85&auto=format&fit=crop&s=b09f64b5e34ef71053ad662a0e28a31a');



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
----------------------------
insert into destination_categories (destination_id, categories_id) values (6, 3);
insert into destination_categories (destination_id, categories_id) values (6, 9);
insert into destination_categories (destination_id, categories_id) values (6, 11);

insert into destination_categories (destination_id, categories_id) values (7, 2);
insert into destination_categories (destination_id, categories_id) values (7, 6);
insert into destination_categories (destination_id, categories_id) values (7, 4);

insert into destination_categories (destination_id, categories_id) values (8, 13);
insert into destination_categories (destination_id, categories_id) values (8, 12);
insert into destination_categories (destination_id, categories_id) values (8, 11);

insert into destination_categories (destination_id, categories_id) values (9, 10);
insert into destination_categories (destination_id, categories_id) values (9, 7);
insert into destination_categories (destination_id, categories_id) values (9, 5);

insert into destination_categories (destination_id, categories_id) values (10, 9);
insert into destination_categories (destination_id, categories_id) values (10, 7);
insert into destination_categories (destination_id, categories_id) values (10, 2);

insert into destination_categories (destination_id, categories_id) values (11, 8);

insert into destination_categories (destination_id, categories_id) values (12, 3);
insert into destination_categories (destination_id, categories_id) values (12, 4);
insert into destination_categories (destination_id, categories_id) values (12, 5);

insert into destination_categories (destination_id, categories_id) values (13, 9);
insert into destination_categories (destination_id, categories_id) values (13, 10);
insert into destination_categories (destination_id, categories_id) values (13, 11);

insert into destination_categories (destination_id, categories_id) values (14, 8);
insert into destination_categories (destination_id, categories_id) values (14, 12);
insert into destination_categories (destination_id, categories_id) values (14, 10);

insert into destination_categories (destination_id, categories_id) values (15, 4);
insert into destination_categories (destination_id, categories_id) values (15, 1);
insert into destination_categories (destination_id, categories_id) values (15, 12);


----- FOOD
insert into destination_local_food (destination_id, local_food) values (1, 5);
insert into destination_local_food (destination_id, local_food) values (2, 6);
insert into destination_local_food (destination_id, local_food) values (2, 1);
insert into destination_local_food (destination_id, local_food) values (3, 9);
insert into destination_local_food (destination_id, local_food) values (4, 2);
insert into destination_local_food (destination_id, local_food) values (5, 6);
insert into destination_local_food (destination_id, local_food) values (5, 0);
------------------------
insert into destination_local_food (destination_id, local_food) values (6, 5);
insert into destination_local_food (destination_id, local_food) values (6, 0);
insert into destination_local_food (destination_id, local_food) values (6, 2);
insert into destination_local_food (destination_id, local_food) values (7, 4);
insert into destination_local_food (destination_id, local_food) values (7, 1);
insert into destination_local_food (destination_id, local_food) values (8, 7);
insert into destination_local_food (destination_id, local_food) values (8, 3);
insert into destination_local_food (destination_id, local_food) values (8, 0);
insert into destination_local_food (destination_id, local_food) values (9, 5);
insert into destination_local_food (destination_id, local_food) values (9, 9);
insert into destination_local_food (destination_id, local_food) values (10, 5);
insert into destination_local_food (destination_id, local_food) values (10, 1);
insert into destination_local_food (destination_id, local_food) values (10, 0);
insert into destination_local_food (destination_id, local_food) values (11, 1);
insert into destination_local_food (destination_id, local_food) values (11, 9);
insert into destination_local_food (destination_id, local_food) values (11, 7);
insert into destination_local_food (destination_id, local_food) values (12, 2);
insert into destination_local_food (destination_id, local_food) values (12, 0);
insert into destination_local_food (destination_id, local_food) values (13, 2);
insert into destination_local_food (destination_id, local_food) values (14, 5);
insert into destination_local_food (destination_id, local_food) values (15, 8);
insert into destination_local_food (destination_id, local_food) values (15, 3);


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
-----------------------------
insert into destination_transportation (destination_id, transportation) values (6, 0);
insert into destination_transportation (destination_id, transportation) values (6, 1);
insert into destination_transportation (destination_id, transportation) values (6, 2);
insert into destination_transportation (destination_id, transportation) values (7, 4);
insert into destination_transportation (destination_id, transportation) values (7, 1);
insert into destination_transportation (destination_id, transportation) values (8, 3);
insert into destination_transportation (destination_id, transportation) values (8, 2);
insert into destination_transportation (destination_id, transportation) values (9, 0);
insert into destination_transportation (destination_id, transportation) values (9, 1);
insert into destination_transportation (destination_id, transportation) values (10, 4);
insert into destination_transportation (destination_id, transportation) values (10, 1);
insert into destination_transportation (destination_id, transportation) values (11, 0);
insert into destination_transportation (destination_id, transportation) values (11, 2);
insert into destination_transportation (destination_id, transportation) values (12, 4);
insert into destination_transportation (destination_id, transportation) values (13, 1);
insert into destination_transportation (destination_id, transportation) values (13, 2);
insert into destination_transportation (destination_id, transportation) values (13, 3);
insert into destination_transportation (destination_id, transportation) values (14, 3);
insert into destination_transportation (destination_id, transportation) values (15, 2);


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
-----------------------

insert into hotel (name, stars, active, destination_id, children_discount) values ('Red star', 5, true, 6, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 6, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 6, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 7, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 7, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 7, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 8, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 8, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 8, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 9, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 9, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 9, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 10, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 10, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 10, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 11, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 11, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 11, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 12, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 12, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 12, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 13, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 13, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 14, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 4, true, 14, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Adlon Hotel', 1, true, 14, true);

insert into hotel (name, stars, active, destination_id, children_discount) values ('Park Inn by Radisson', 5, true, 15, true);
insert into hotel (name, stars, active, destination_id, children_discount) values ('Alexanderplatz', 3, true, 15, true);

---- USER INTERESTS
insert into users_interests (registered_user_id, interests_id) values (2, 1);
insert into users_interests (registered_user_id, interests_id) values (2, 2);
insert into users_interests (registered_user_id, interests_id) values (2, 3);
insert into users_interests (registered_user_id, interests_id) values (2, 11);
insert into users_interests (registered_user_id, interests_id) values (2, 13);

insert into users_interests (registered_user_id, interests_id) values (3, 1);
insert into users_interests (registered_user_id, interests_id) values (3, 7);
insert into users_interests (registered_user_id, interests_id) values (3, 12);

insert into users_interests (registered_user_id, interests_id) values (4, 3);
insert into users_interests (registered_user_id, interests_id) values (4, 7);
insert into users_interests (registered_user_id, interests_id) values (4, 9);
