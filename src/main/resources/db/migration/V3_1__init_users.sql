
insert into users (user_id,user_name,password,active)
values(9997,'admin','$2a$12$MbCHRTXm58fQnfkkBl1GnOCyRoTx99j4LS/akrQW.0r/cVN.mrhh6',true);

insert into users (user_id,user_name,password,active)
values(9998,'competitor','$2a$12$MbCHRTXm58fQnfkkBl1GnOCyRoTx99j4LS/akrQW.0r/cVN.mrhh6',true);

insert into users (user_id,user_name,password,active)
values(9999,'organizer','$2a$12$MbCHRTXm58fQnfkkBl1GnOCyRoTx99j4LS/akrQW.0r/cVN.mrhh6',true);


insert into person (person_id, name, surname, date_of_birth, email,phone,sex,address_id,user_id)
            values(9999,'admin','admin','26-08-2023','steprojet83@gmail.com','+48 500 600 700','admin',0,9997);

insert into person(person_id,name,surname,date_of_birth,email,phone,sex,address_id,user_id)
values(9998,'competitor','competitor','0000','steprojet83@gmail.com','+48 500 600 700','competitor',0,9998);

insert into person (person_id, name, surname, date_of_birth, email,phone,sex,address_id,user_id)
            values(9997,'organizer','organizer','0000','steprojet83@gmail.com','+48 500 600 700','organizer',0,9999);

insert into role (role_id,role) values(1,'ADMIN'),(2,'COMPETITOR'),(3,'ORGANIZER');

insert into user_role(user_id,role_id) values (9997,1),(9997,2),(9997,3);
insert into user_role(user_id,role_id) values (9998,2);
insert into user_role(user_id,role_id) values (9999,3);

insert into ORGANIZER (organizer_id,name_of_organizer,email,phone,address_id,user_id)
values
(55,'Gmina Bardo','bardo@bardo.com','556228663',26,9999),
(56,'Gmina Swidnica','swidnica@swinica.com','448663552',25,9999),
(57,'Gmina Dzierzoniow','dzierzoniow@dzierzoniow.com','123654789',27,9999);

insert into TOURNAMENT (name_of_tournament,number_of_start_plates,
sport_category,start_of_tournament,end_of_tournament,distance,buy_in,organizer_id)
values
('Bardzka piateczka', 100,'RUNNING','12-08-2023','12-08-2023',25,75,55),
('Tour de Swidnica', 100,'CYCLING','11-08-2023','11-08-2023',25,80,56),
('Z gorki na pazurki', 50,'SKIING','13-08-2023','13-08-2023',25,35,57);