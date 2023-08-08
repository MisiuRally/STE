insert into ADDRESS (country,city,postal_code,street,address_id)
values
('Poland','DSW','58-100','Lipowaaaa',25),
('Poland','DZA','57-256','Grunwaldzka',26),
('Poland','DDZ','57-300','Pieszycka',27);

--insert into PERSON (name,surname,date_of_birth,email,phone,sex,address_id)
--values
--('Misiu', 'Rally', '8.8.2007','misiu@gmail.com','554665113','MALE',1);

insert into ORGANIZER (organizer_id,name_of_organizer,email,phone,address_id)
values
(55,'Gmina Bardo','bardo@bardo.com','556228663',26),
(56,'Gmina Swidnica','swidnica@swinica.com','448663552',25),
(57,'Gmina Dzierzoniow','dzierzoniow@dzierzoniow.com','123654789',27);

insert into TOURNAMENT (name_of_tournament,number_of_start_plates,
sport_category,start_of_tournament,end_of_tournament,organizer_id)
values
('Bardzka piateczka', 100,'RUNNING','10-10-2025','11-10-2025',55),
('Tour de Swidnica', 100,'CYCLING','11-12-2025','11-12-2025',56),
('Z gorki na pazurki', 50,'SKIING','13-08-2026','13-08-2026',57);
