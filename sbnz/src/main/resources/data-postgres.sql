insert into authority (role) values ('ROLE_REGISTERED_USER');
insert into authority (role) values ('ROLE_ADMIN');


insert into users (type, active, email, first_name, last_name, last_password_reset_date, password, verified, date_of_birth, location_lat, location_lon, profession)
values ('registered_user', true, 'jana@gmail.com', 'Jana', 'Mara', '2020-02-08 23:40:37', '$2a$10$RVzuprKddsjdq6P8QWmqF.sCj2uYPIUlbFVB.b7tJ9RdFNOOBNoXO', true, '1995-02-08 23:40:37' , 44.25, 21.56, 'pizza seller');

insert into users_authority (user_id, authority_id) values (1, 1);
