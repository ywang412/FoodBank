DROP DATABASE IF EXISTS `cs6400_sp17_team073`; 


SET default_storage_engine=InnoDB;


CREATE DATABASE IF NOT EXISTS cs6400_sp17_team073 DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE cs6400_sp17_team073;

-- Tables 

CREATE TABLE User (
  username varchar(250) NOT NULL,
  user_email varchar(250) NOT NULL,
  password varchar(50) NOT NULL,
  full_name varchar(250) NOT NULL,
  site_id int(16)unsigned,
  PRIMARY KEY (username)
);

CREATE TABLE Site (
  site_id int(16) unsigned NOT NULL AUTO_INCREMENT,
  short_name varchar(250) NOT NULL,
  street_address varchar(250) NOT NULL,
  city varchar(250) NOT NULL,
  state varchar(50) NOT NULL,
  full_name varchar(250) NOT NULL,
  zip int(16) unsigned NOT NULL,
  contact_number varchar(50) NOT NULL, 
  PRIMARY KEY (site_id)
);


CREATE TABLE Provide (
  site_id int(16) unsigned NOT NULL,
  food_bank_id int(16) unsigned,
  food_pantry_id int(16) unsigned,
  soup_kitchen_id int(16) unsigned,
  shelter_id int(16) unsigned,  
  PRIMARY KEY (site_id)
);

CREATE TABLE Item (
  item_name varchar(250) NOT NULL,
  number_of_units int(16) unsigned,
  storage_type int(16) unsigned NOT NULL,
  item_type int(16) unsigned NOT NULL,
  food_category int(16) unsigned NOT NULL,
  supply_category int(16) unsigned NOT NULL,
  expiration_date DATETIME NOT NULL, 
  food_bank_id int(16) unsigned,
  PRIMARY KEY (item_name, food_bank_id)
);


CREATE TABLE Request (
  username varchar(250) NOT NULL,
  item_name varchar(250) NOT NULL,
  request_status int(16) unsigned NOT NULL,
  units_requested int(16) unsigned NOT NULL,
  units_fulfilled int(16) unsigned,
  request_date DATETIME,
  PRIMARY KEY (username,item_name,request_date)
);

CREATE TABLE Food_Pantry (
  food_pantry_id int(16) unsigned NOT NULL AUTO_INCREMENT,
  description_string varchar(250) NOT NULL,
  hours varchar(50) NOT NULL,
  conditions_for_use varchar(250) NOT NULL,
  PRIMARY KEY (food_pantry_id)
);


CREATE TABLE Food_Bank (
  food_bank_id int(16) unsigned NOT NULL AUTO_INCREMENT,
  description_string varchar(250) NOT NULL,
  PRIMARY KEY (food_bank_id)
);

CREATE TABLE Soup_Kitchen (
  soup_kitchen_id int(16) unsigned NOT NULL AUTO_INCREMENT,
  description_string varchar(250) NOT NULL,
  hours varchar(50) NOT NULL,
  conditions_for_use varchar(250) NOT NULL,
  available_seats int(16),
  seats_limit int(16),
  PRIMARY KEY (soup_kitchen_id)
);

CREATE TABLE Shelter (
  shelter_id int(16) unsigned NOT NULL AUTO_INCREMENT,
  description_string varchar(250) NOT NULL,
  hours varchar(50) NOT NULL,
  conditions_for_use varchar(250) NOT NULL,
  available_bunks int(16),  
  available_rooms int(16),
  PRIMARY KEY (shelter_id)
);

CREATE TABLE Room (
  room_number int(16) unsigned NOT NULL AUTO_INCREMENT,
  shelter_id int(16) unsigned NOT NULL,
  occupied boolean DEFAULT FALSE,
  PRIMARY KEY (room_number,shelter_id)
);

CREATE TABLE Bunk (
  bunk_number int(16) unsigned NOT NULL AUTO_INCREMENT ,
  bunk_type int(16) unsigned NOT NULL,
  shelter_id int(16) unsigned NOT NULL,
  occupied boolean DEFAULT FALSE,
  PRIMARY KEY (bunk_number)
);

CREATE TABLE Client (
  client_id int(16) unsigned NOT NULL AUTO_INCREMENT,
  full_name varchar(250) NOT NULL,  
  description_string varchar(250) NOT NULL,
  head_of_household boolean,
  phone_number char(12),
  PRIMARY KEY (client_id)
);

CREATE TABLE Waitlist (
  position int(16) unsigned NOT NULL,
  room_number int(16) unsigned NOT NULL,
  shelter_id int(16) unsigned NOT NULL,
  client_id int(16) unsigned NOT NULL,
  PRIMARY KEY (position,room_number,client_id,shelter_id)
);

CREATE TABLE Log_Entry (
  log_id int(16) unsigned NOT NULL AUTO_INCREMENT,
  log_entry_string varchar(250) NOT NULL,  
  timestamp datetime NOT NULL, 
  log_usage varchar(250) NOT NULL,
  client_id int(16) unsigned NOT NULL,  
  PRIMARY KEY (log_id)
);

CREATE TABLE Item_type_enum (
  item_type int(16) unsigned NOT NULL AUTO_INCREMENT,
  item_type_name varchar(250) NOT NULL,
  PRIMARY KEY (item_type)
);

CREATE TABLE Item_food_category_enum (
  food_category int(16) unsigned NOT NULL AUTO_INCREMENT,
  food_category_name varchar(250) NOT NULL,
  PRIMARY KEY (food_category)
);

CREATE TABLE Item_supply_category_enum (
  supply_category int(16) unsigned NOT NULL AUTO_INCREMENT,
  supply_category_name varchar(250) NOT NULL,
  PRIMARY KEY (supply_category)
);

CREATE TABLE Item_storage_type_enum (
  storage_type int(16) unsigned NOT NULL AUTO_INCREMENT,
  storage_type_name varchar(250) NOT NULL,
  PRIMARY KEY (storage_type)
);

CREATE TABLE Bunk_type_enum (
  bunk_type int(16) unsigned NOT NULL AUTO_INCREMENT,
  bunk_type_name varchar(250) NOT NULL,
  PRIMARY KEY (bunk_type)
);

CREATE TABLE Request_status_enum(
  request_status int(16) unsigned NOT NULL AUTO_INCREMENT,
  request_status_name varchar(250) NOT NULL,
  PRIMARY KEY (request_status)
);

--  Table Constraints 

ALTER TABLE `User`
  ADD CONSTRAINT User_ibfk_1 FOREIGN KEY (site_id) REFERENCES `Site` (site_id);
  
ALTER TABLE `Provide`
  ADD CONSTRAINT Provide_ibfk_1 FOREIGN KEY (site_id) REFERENCES `Site` (site_id),
  ADD CONSTRAINT Provide_ibfk_2 FOREIGN KEY (food_bank_id) REFERENCES `Food_Bank` (food_bank_id) ON DELETE SET NULL,
  ADD CONSTRAINT Provide_ibfk_3 FOREIGN KEY (food_pantry_id) REFERENCES `Food_Pantry` (food_pantry_id) ON DELETE SET NULL, 
  ADD CONSTRAINT Provide_ibfk_4 FOREIGN KEY (soup_kitchen_id) REFERENCES `Soup_Kitchen` (soup_kitchen_id) ON DELETE SET NULL,
  ADD CONSTRAINT Provide_ibfk_5 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE SET NULL,
  ADD CONSTRAINT Provide_ibfk_6 UNIQUE ( food_bank_id),
  ADD CONSTRAINT Provide_ibfk_7 UNIQUE ( soup_kitchen_id ),
  ADD CONSTRAINT Provide_ibfk_8 UNIQUE ( shelter_id),
  ADD CONSTRAINT Provide_ibfk_9 UNIQUE (food_pantry_id);

ALTER TABLE `Item`
  ADD CONSTRAINT Item_ibfk_1 FOREIGN KEY (food_bank_id) REFERENCES `Food_Bank` (food_bank_id),
  ADD CONSTRAINT Item_ibfk_2 FOREIGN KEY (storage_type) REFERENCES `Item_storage_type_enum` (storage_type),
  ADD CONSTRAINT Item_ibfk_3 FOREIGN KEY (item_type) REFERENCES `Item_type_enum` (item_type),
  ADD CONSTRAINT Item_ibfk_4 FOREIGN KEY (food_category) REFERENCES `Item_food_category_enum` (food_category),
  ADD CONSTRAINT Item_ibfk_5 FOREIGN KEY (supply_category) REFERENCES `Item_supply_category_enum` (supply_category);

ALTER TABLE `Request`
  ADD CONSTRAINT Request_ibfk_1 FOREIGN KEY (username) REFERENCES `User` (username),
  ADD CONSTRAINT Request_ibfk_2 FOREIGN KEY (request_status) REFERENCES `Request_status_enum` (request_status),
  ADD CONSTRAINT Request_ibfk_3 FOREIGN KEY (item_name) REFERENCES `Item` (item_name);
    

ALTER TABLE `Room`
  ADD CONSTRAINT Room_ibfk_1 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE;


ALTER TABLE `Bunk`
  ADD CONSTRAINT Bunk_ibfk_1 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE,
  ADD CONSTRAINT Bunk_ibfk_2 FOREIGN KEY (bunk_type) REFERENCES `Bunk_type_enum` (bunk_type);
        
ALTER TABLE `Waitlist`
  ADD CONSTRAINT Waitlist_ibfk_1 FOREIGN KEY (room_number) REFERENCES `Room` (room_number),
  ADD CONSTRAINT Waitlist_ibfk_2 FOREIGN KEY (client_id) REFERENCES `Client` (client_id),     
  ADD CONSTRAINT Waitlist_ibfk_3 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE;    
  
ALTER TABLE `Log_Entry`
  ADD CONSTRAINT Log_Entry_ibfk_1 FOREIGN KEY (client_id) REFERENCES `Client` (client_id);      

--  Not exactly test data, we need to populate enums

INSERT INTO `cs6400_sp17_team073`.`Item_type_enum` (`item_type_name`) VALUES ('none');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum` (`food_category_name`) VALUES ('none');
INSERT INTO `cs6400_sp17_team073`.`Item_supply_category_enum` (`supply_category_name`) VALUES ('none');
INSERT INTO `cs6400_sp17_team073`.`Item_supply_category_enum` (`supply_category_name`) VALUES ('personal hygiene');
INSERT INTO `cs6400_sp17_team073`.`Item_supply_category_enum` (`supply_category_name`) VALUES ('clothing');
INSERT INTO `cs6400_sp17_team073`.`Item_supply_category_enum` (`supply_category_name`) VALUES ('shelter');
INSERT INTO `cs6400_sp17_team073`.`Item_supply_category_enum` (`supply_category_name`) VALUES ('other');
INSERT INTO `cs6400_sp17_team073`.`Item_storage_type_enum` (`storage_type_name`) VALUES ('none');
INSERT INTO `cs6400_sp17_team073`.`Item_storage_type_enum` (`storage_type_name`) VALUES ('refrigerated');
INSERT INTO `cs6400_sp17_team073`.`Item_storage_type_enum` (`storage_type_name`) VALUES ('drygoods');
INSERT INTO `cs6400_sp17_team073`.`Item_storage_type_enum` (`storage_type_name`) VALUES ('frozen');

INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum` (`food_category_name`) VALUES ('vegetables');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('beans');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('nuts');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('grains');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('meat');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('seafood');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('dairy');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('condiments');
INSERT INTO `cs6400_sp17_team073`.`Item_food_category_enum`  (`food_category_name`) VALUES ('drinks');

INSERT INTO `cs6400_sp17_team073`.`Bunk_type_enum`  (`bunk_type_name`) VALUES ('male');
INSERT INTO `cs6400_sp17_team073`.`Bunk_type_enum`  (`bunk_type_name`) VALUES ('female');
INSERT INTO `cs6400_sp17_team073`.`Bunk_type_enum`  (`bunk_type_name`) VALUES ('mixed');

INSERT INTO `cs6400_sp17_team073`.`Request_status_enum` (`request_status_name`) VALUES ('filled');
INSERT INTO `cs6400_sp17_team073`.`Request_status_enum` (`request_status_name`) VALUES ('pending');
INSERT INTO `cs6400_sp17_team073`.`Request_status_enum` (`request_status_name`) VALUES ('rejected');

--  INSERT INTO `cs6400_sp17_team073`.`Site`
--  3 Sites: (short name, address, phone) use short name: ('site1', 'site2', …, 'site3')
INSERT INTO `cs6400_sp17_team073`.`Site`  (`short_name`, `street_address`, `city`, `state`, `full_name`, `zip`, `contact_number`) VALUES ('site1', '10 Downing street', 'London', 'UK', 'Ministers office', 00001, '1 111 111 1111');
INSERT INTO `cs6400_sp17_team073`.`Site`  (`short_name`, `street_address`, `city`, `state`, `full_name`, `zip`, `contact_number`) VALUES ('site2', '12 Downing street', 'London', 'UK', 'Ministers office', 00002, '2 222 222 2222');
INSERT INTO `cs6400_sp17_team073`.`Site`  (`short_name`, `street_address`, `city`, `state`, `full_name`, `zip`, `contact_number`) VALUES ('site3', '13 Downing street', 'London', 'UK', 'Ministers office', 00003, '3 333 333 3333');

--  3 Employee Users username: 'emp#' firstname: 'Site#' lastname: 'Employee#' password value="gatech123" 
INSERT INTO `cs6400_sp17_team073`.`User` (username, user_email, password, full_name, site_id) VALUES ('emp1', 'Site1Employee1@acacs.org','gatech123','Site1 Employee1', 1);
INSERT INTO `cs6400_sp17_team073`.`User` (username, user_email, password, full_name, site_id) VALUES ('emp2', 'Site2Employee2@acacs.org','gatech123','Site2 Employee2', 2);
INSERT INTO `cs6400_sp17_team073`.`User` (username, user_email, password, full_name, site_id) VALUES ('emp3', 'Site3Employee3@acacs.org','gatech123','Site3 Employee3', 3);
--  3 Volunteer Users username: 'vol#' firstname: Demo lastname: Volunteer# password value="gatech123" 
INSERT INTO `cs6400_sp17_team073`.`User` (username, user_email, password, full_name, site_id) VALUES ('vol1', 'DemoVolunteer1@acacs.org','gatech123','Demo Volunteer1', 1);
INSERT INTO `cs6400_sp17_team073`.`User` (username, user_email, password, full_name, site_id) VALUES ('vol2', 'DemoVolunteer2@acacs.org','gatech123','Demo Volunteer2', 2);
INSERT INTO `cs6400_sp17_team073`.`User` (username, user_email, password, full_name, site_id) VALUES ('vol3', 'DemoVolunteer3@acacs.org','gatech123','Demo Volunteer3', 3);

--  12 Clients ('client1', 'client2', … 'client12') firstname: 'Joe' or ‘Jane’ lastname: 'client#'
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Joe client1', 'Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Jane client2', 'Not Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Joe client3', 'Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Jane client4', 'Not Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Joe client4', 'Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Jane client5', 'Not Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Joe client5', 'Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Jane client6', 'Not Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Joe client7', 'Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Jane client8', 'Not Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Joe client9', 'Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Jane client10', 'Not Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Joe client11', 'Beardy', TRUE);
INSERT INTO `cs6400_sp17_team073`.`Client` (full_name, description_string, head_of_household) VALUES ('Jane client12', 'Not Beardy', TRUE);

--  9 Services:
--  o 2 Food Pantries (hours of operation, conditions) use short names: ('pantry1', 'pantry2', etc.)
INSERT INTO `cs6400_sp17_team073`.`Food_Pantry` (description_string, hours, conditions_for_use) VALUES ('pantry1', '6:00 pm - 10:00 pm','Non-violent');
INSERT INTO `cs6400_sp17_team073`.`Food_Pantry` (description_string, hours, conditions_for_use) VALUES ('pantry3', '6:00 pm - 10:00 pm','Non-violent');
--  o 2 Soup Kitchens (hours of operation, conditions, seats_avail) use short names: ('soup3', etc.)
INSERT INTO `cs6400_sp17_team073`.`Soup_Kitchen` (description_string, hours, conditions_for_use, available_seats,seats_limit) VALUES('soup2', '5:00 pm - 8:00 pm', 'Sober', 10,10);
INSERT INTO `cs6400_sp17_team073`.`Soup_Kitchen` (description_string, hours, conditions_for_use, available_seats,seats_limit) VALUES('soup3', '5:00 pm - 8:00 pm', 'Sober', 10,10);
--  o 2 Shelters (hours of operation, conditions) use short names: ('shelter2', 'shelter3', etc.)
INSERT INTO `cs6400_sp17_team073`.`Shelter` (description_string, hours, conditions_for_use, available_bunks, available_rooms) VALUES('shelter2','9:00 pm - 8:00 am', 'female or under 18', 12, 10);
INSERT INTO `cs6400_sp17_team073`.`Shelter` (description_string, hours, conditions_for_use, available_bunks, available_rooms) VALUES('shelter3','9:00 pm - 8:00 am', 'female or under 18', 12, 10);
--  o 3 Food Banks (use short names: (‘bank1’, ‘bank2’, ‘bank3’)
INSERT INTO `cs6400_sp17_team073`.`Food_Bank`(description_string) VALUES('bank1');
INSERT INTO `cs6400_sp17_team073`.`Food_Bank`(description_string) VALUES('bank2');
INSERT INTO `cs6400_sp17_team073`.`Food_Bank`(description_string) VALUES('bank3');
--  o Assign 'pantry2' service to 'site2' (Note: generally service names match site number)
INSERT INTO `cs6400_sp17_team073`.`Provide`(site_id, food_bank_id, food_pantry_id, soup_kitchen_id, shelter_id) VALUES(1,1, 1, null, null);
INSERT INTO `cs6400_sp17_team073`.`Provide`(site_id, food_bank_id, food_pantry_id, soup_kitchen_id, shelter_id) VALUES(2,2, 2, 1, 1);
INSERT INTO `cs6400_sp17_team073`.`Provide`(site_id, food_bank_id, food_pantry_id, soup_kitchen_id, shelter_id) VALUES(3,3, null, 2, 2);

--  Each Shelter: 2 bunks each type: men, woman, mixed (6 per shelter)
--  o 1 Male only room with 2 bunks
--  o 1 Female only room with 2 bunks
--  o 1 Mixed room with 2 bunks

INSERT INTO `cs6400_sp17_team073`.`Room`(shelter_id) VALUES(1);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(1,1,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(1,1,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(2,1,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(2,1,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(3,1,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(3,1,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(1,2,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(1,2,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(2,2,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(2,2,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(3,2,FALSE);
INSERT INTO `cs6400_sp17_team073`.`Bunk`(bunk_type,shelter_id,occupied) VALUES(3,2,FALSE);

--  Fill waitlist of shelter 1
INSERT INTO `cs6400_sp17_team073`.`Waitlist`(position,room_number,shelter_id,client_id) VALUES(1,1,1,1);
INSERT INTO `cs6400_sp17_team073`.`Waitlist`(position,room_number,shelter_id,client_id) VALUES(2,1,1,2);
INSERT INTO `cs6400_sp17_team073`.`Waitlist`(position,room_number,shelter_id,client_id) VALUES(3,1,1,3);
INSERT INTO `cs6400_sp17_team073`.`Waitlist`(position,room_number,shelter_id,client_id) VALUES(4,1,1,4);

--  Example: client1_log1 = “profile created…” , timestamp, etc.
--  client1_log2 = “meal provided by…” site, timestamp, etc.
--  client1_log3 = “profile edited/updated…”, timestamp, etc.
--  …
--  client12_log3 = “check-in to room” … etc.
--  o 'client1' … client4' visit 'pantry1' for 'site1' (clients 1-4 are ‘pantry’ clients)
--  o 'client5' … client8' visit 'soup2' for 'site2' (clients 5-8 are ‘soup’ clients)
--  o 'client1' … ‘client12' visit 'shelter2' for 'site2' (clients 1-12 are ‘shelter’ clients)
--  o 'client1' … 'client4' visit 'pantry3' for 'site3'
--  o 'client5' … 'client8' visit 'soup3' for 'site3'
--  o 'client1' … 'client12' visit 'shelter3' for 'site3

INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 1);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 1);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food again', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 1);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 2);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 2);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food again', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 2);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 3);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 3);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food again', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 3);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 4);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 4);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Got Food again', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 4);

INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 5);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup2', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 5);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup3', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 5);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 6);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup2', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 6);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup3', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 6);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 7);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup2', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 7);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup3', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 7);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 8);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup2', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 8);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit soup3', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 8);

INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 9);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter1', DATE_SUB(NOW(), INTERVAL 2 DAY), "", 9);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter2', DATE_SUB(NOW(), INTERVAL 3 DAY), "", 9);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 10);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter1', DATE_SUB(NOW(), INTERVAL 2 DAY), "", 10);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter2', DATE_SUB(NOW(), INTERVAL 3 DAY), "", 10);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 11);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter1', DATE_SUB(NOW(), INTERVAL 2 DAY), "", 11);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter2', DATE_SUB(NOW(), INTERVAL 3 DAY), "", 11);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Profile created', DATE_SUB(NOW(), INTERVAL 1 DAY), "", 12);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter1', DATE_SUB(NOW(), INTERVAL 2 DAY), "", 12);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visited shelter2', DATE_SUB(NOW(), INTERVAL 3 DAY), "", 12);


INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 1);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 2);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 3);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 4);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 5);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 6);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 7);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 8);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 9);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 10);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 11);
INSERT INTO `cs6400_sp17_team073`.`Log_Entry`(log_entry_string,timestamp,log_usage,client_id) VALUES('Visit shelter3', DATE_SUB(NOW(), INTERVAL 1 HOUR), "", 12);

--  10 Food Items: (storage_type= refrigerated, food_catogory=vegetables)  (only insert leafy vegetables into this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('spinach', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('lettuce', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('kale', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mustard greens', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('brocolli', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('celary', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('dill', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pea', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('poke', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chard', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--  10 Food Items: (storage_type=drygoods, food_catogory=nuts/grains/beans) (only insert nuts products into this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('almond', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('cashew', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('peanut', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pistacio', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('brazil', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('walnut', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pecan', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('hazelnut', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('macadamia nut', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chestnut', 1, 3,1,4,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--  o 10 Food Items: (storage_type=drygoods, food_catogory=sauce/condiments)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('ketchup', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mustard', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('sirracha', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mayo', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('nutella', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('peanut butter', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('jelly', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('honey', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('soy sauce', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('special sauce', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--  o 10 Food Items: (storage_type=refrigerated, food_catogory= juice/drink) (only insert soda/pop drinks items in this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('coke', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('diet coke', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('coke zero', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chery coke', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('cherry vanilla coke', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('cherry coke zero', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('cherry vanilla coke zero', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mexican coke', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('caffeine free coke', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('caffeine free diet coke', 9, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--  o 10 Food Items: (storage_type=frozen, food_catogory=meat/seafood) (only insert red-meat only items in this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mabburder', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('ribs', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('steak', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pork chop', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pork shoulder', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pork butt', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pork loin', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('T bone', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('bacon', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Pork belly', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--  o 10 Food Items: (storage_type=refrigerated, food_catogory=dairy/eggs) (only insert cheese products in this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('american cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pepper jack cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('munster cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('cheddar cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mexican cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('parmesean cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('blue cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('goat cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('dariy-free cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mozzerella cheese', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--  o 5 Supply Items: (type: personal hygiene) examples: toothbrush, toothpaste, shampoo, deodorant, soap/detergent, baby wipes, etc.
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('deoderant', 1, 1,1,1,2,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('toothbrush', 1, 1,1,1,2,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('toothpaste', 1, 1,1,1,2,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('shampoo', 1, 1,1,1,2,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('soap', 1, 1,1,1,2,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--  o 5 Supply Items: (type: clothing) examples: shirts, pants, underwear, etc.
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('shirts', 1, 1,1,1,3,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('shoes', 1, 1,1,1,3,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pants', 1, 1,1,1,3,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('underwear', 1, 1,1,1,3,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('ties', 1, 1,1,1,3,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
--   Food Bank 2 (bankID=2) Notice: bankID=2 only has grains, fruit-juices, and seafood where the other banks do not.
--  o 10 Food Items: (storage_type= refrigerated, food_catogory=vegetables) (only insert root veggies into this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('carrot', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('turnip', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('parsnip', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('rutabega', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('radish', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('celary', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('beet', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('asparagus', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('scallion', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('wasabi', 1, 2,1,2,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--  o 10 Food Items: (storage_type=drygoods, food_catogory= nuts/grains/beans) (only insert grains only items in this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('wheat', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('rye', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('barley', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('bran', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('bulgar', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Couscous', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('orzo', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('flour', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('oats', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('corn', 1, 3,1,5,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--  o 10 Food Items: (storage_type=drygoods, food_catogory=sauce/condiments)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('ketchup', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mustard', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('sirracha', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mayo', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('nutella', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('peanut butter', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('jelly', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('honey', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('soy sauce', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('special sauce', 1, 3,1,9,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--  o 10 Food Items: (storage_type=refrigerated, food_catogory= juice/drink) (only insert fruit juice only items in this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('apple juice', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('orange juice', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('grape juice', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('cranberry juice', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('mixed druit juice', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('fruit punch', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('juice of the barley', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('capri sun', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('capri sun Xtreme', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('capri sun mild', 1, 2,1,10,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--  o 10 Food Items: (storage_type=frozen, food_catogory= meat/seafood) (only insert seafood only items in this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Tuna', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Haddock', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Trout', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Crab', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Swordfish', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Shark', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Catfish', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Herring', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Caviar', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Octopus', 1, 4,1,6,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--  o 10 Food Items: (storage_type=refrigerated, food_catogory= dairy/eggs) (only insert egg containing products in this bank)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Cream pie', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Custard', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Eggnog', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Eggroll', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Egg white', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Meatloaf', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Meatballs', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Pasta', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Tartar sauce', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Egg substitute', 1, 2,1,8,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--  o 5 Supply Items: (type: shelter) examples: tent, sleeping bags, blankets, winter jackets, rain coat, etc.
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('tent', 1, 1,1,1,4,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('sleeping bag', 1, 1,1,1,4,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Blanket', 1, 1,1,1,4,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Rain coat', 1, 1,1,1,4,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Umbrella', 1, 1,1,1,4,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--  o 5 Supply Items: (type: other) examples: paper products, toilet paper, pet food, batteries, etc.
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('paper towel', 1, 1,1,1,5,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('toilet paper', 1, 1,1,1,5,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('pet food', 1, 1,1,1,5,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('battery', 1, 1,1,1,5,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('Dish soap', 1, 1,1,1,5,DATE_ADD(NOW(), INTERVAL 1 DAY), 2);
--   Food Bank 3 all expired products: 10 days older than current date (bankID=3)
--  o 6 Food Items: (storage_type=refrigerated, food_catogory= meat/seafood) (only insert expired chicken products only)
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chicken breast', 1, 4,1,6,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chicken thigh', 1, 4,1,6,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chicken leg', 1, 4,1,6,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chicken cutlet', 1, 4,1,6,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chicken finger', 1, 4,1,6,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chicken wing', 1, 4,1,6,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
--  o 6 Food Items: (storage_type=refrigerated, food_catogory= dairy/eggs) (only insert expired milk (non-cheese) products only) 
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('milk', 1, 2,1,8,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('yogurt', 1, 2,1,8,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('nonfat milk', 1, 2,1,8,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('lactose-free milk', 1, 2,1,8,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('chocolate milk', 1, 2,1,8,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);
INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('strawberry-milk', 1, 2,1,8,1,DATE_SUB(NOW(), INTERVAL 10 DAY), 3);

--   Requests: of 3 Employee Users above, each will have:
--  o Pending Requests from Employee Users (for 'site1', 'site2', and 'site3')
--  o 'emp1' (bankID=1) 10 requests from non-associated bankID=2 to 'site1' (bankID=2 specific food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'carrot', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'turnip', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'rutabega', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'radish', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'celary', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'beet', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'asparagus', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'scallion', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'wasabi', 2, 2,0,NOW());
--  o 'emp1' 4 request from non-associated bankID=3 to 'site1' (bankID=3 expired food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'nonfat milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'lactose-free milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'chocolate milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'strawberry-milk', 2, 2,0,NOW());
--  o 'emp2' (bankID=2) 10 requests from non-associated bankID=1 to 'site2' (bankID=1 specific food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'chard', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'poke', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'pea', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'dill', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'celary', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'brocolli', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'mustard greens', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'kale', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'lettuce', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'spinach', 2, 2,0,NOW());
--  o 'emp2' 4 request from non-associated bankID=3 to 'site2' (bankID=3 expired food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'nonfat milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'lactose-free milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'chocolate milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'strawberry-milk', 2, 2,0,NOW());
--  o 'emp2' 7 requests from non-associated bankID=1 to 'site2' (bankID=1 supply items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'deoderant', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'toothbrush', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'toothpaste', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'shampoo', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'soap', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'shirts', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'shoes', 2, 2,0,NOW());
--  o 'emp3' (bankID=3) 5 requests from non-associated bankID=1 to 'site3' (bankID=1 specific food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'american cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'pepper jack cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'munster cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'cheddar cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'mexican cheese', 2, 2,0,NOW());
--  o 'emp3' 5 requests from non-associated bankID=2 to 'site3' (bankID=2 specific food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Tuna', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Haddock', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Trout', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Crab', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Swordfish', 2, 2,0,NOW());
--  o 'emp3' 7 requests from non-associated bankID=1 (bankID=1 specific supply items only) to 'site3'
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'deoderant', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'toothbrush', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'toothpaste', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'shampoo', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'soap', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'shirts', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'shoes', 2, 2,0,NOW());
--  o 'emp3' 7 requests from non-associated bankID=2 (bankID=2 specific supply items only) to 'site3'
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'tent', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'sleeping bag', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Blanket', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Rain coat', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Umbrella', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'paper towel', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'toilet paper', 2, 2,0,NOW());
--  Note: 'site3' does not have expired food products by design.
--  o 4 Closed Requests per Employee User
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 20 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 21 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 22 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 23 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 20 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 21 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 22 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 23 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 20 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 21 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 22 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('emp3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 23 DAY));
--   Add prior usage data (Same as above in the past anytime past two months)
--   Requests: Of 3 Volunteer Users above, each will have:
--  o Pending Requests for Volunteer Users
--  o **Same as Employee requests, just change 'emp#' to 'vol#'**
--  o 4 Closed Requests per Volunteer Users
--   Add prior usage data (Same as above in the past anytime past two months)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'carrot', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'turnip', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'rutabega', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'radish', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'celary', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'beet', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'asparagus', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'scallion', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'wasabi', 2, 2,0,NOW());
--  o 'vol1' 4 request from non-associated bankID=3 to 'site1' (bankID=3 expired food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'nonfat milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'lactose-free milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'chocolate milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'strawberry-milk', 2, 2,0,NOW());
--  o 'vol2' (bankID=2) 10 requests from non-associated bankID=1 to 'site2' (bankID=1 specific food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'chard', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'poke', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'pea', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'dill', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'celary', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'brocolli', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'mustard greens', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'kale', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'lettuce', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'spinach', 2, 2,0,NOW());
--  o 'vol2' 4 request from non-associated bankID=3 to 'site2' (bankID=3 expired food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'nonfat milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'lactose-free milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'chocolate milk', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'strawberry-milk', 2, 2,0,NOW());
--  o 'vol2' 7 requests from non-associated bankID=1 to 'site2' (bankID=1 supply items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'deoderant', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'toothbrush', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'toothpaste', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'shampoo', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'soap', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'shirts', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'shoes', 2, 2,0,NOW());
--  o 'vol3' (bankID=3) 5 requests from non-associated bankID=1 to 'site3' (bankID=1 specific food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'american cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'pepper jack cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'munster cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'cheddar cheese', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'mexican cheese', 2, 2,0,NOW());
--  o 'vol3' 5 requests from non-associated bankID=2 to 'site3' (bankID=2 specific food items only)
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Tuna', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Haddock', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Trout', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Crab', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Swordfish', 2, 2,0,NOW());
--  o 'vol3' 7 requests from non-associated bankID=1 (bankID=1 specific supply items only) to 'site3'
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'deoderant', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'toothbrush', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'toothpaste', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'shampoo', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'soap', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'shirts', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'shoes', 2, 2,0,NOW());
--  o 'vol3' 7 requests from non-associated bankID=2 (bankID=2 specific supply items only) to 'site3'
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'tent', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'sleeping bag', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Blanket', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Rain coat', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Umbrella', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'paper towel', 2, 2,0,NOW());
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'toilet paper', 2, 2,0,NOW());
--  Note: 'site3' does not have expired food products by design.
--  o 4 Closed Requests per Employee User
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 20 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 21 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 22 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol1', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 23 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 20 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 21 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 22 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol2', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 23 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 20 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 21 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 22 DAY));
INSERT INTO `Request`(username, item_name,request_status,units_requested,units_fulfilled,request_date) VALUES ('vol3', 'Rain coat', 3, 2,0,DATE_SUB(NOW(), INTERVAL 23 DAY));
