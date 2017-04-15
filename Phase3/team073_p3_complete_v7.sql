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
  PRIMARY KEY (item_name)
);

CREATE TABLE Request (
  username varchar(250) NOT NULL,
  item_name varchar(250) NOT NULL,
  request_status int(16) unsigned NOT NULL,
  units_requested int(16) unsigned NOT NULL,
  units_fulfilled int(16) unsigned,
  PRIMARY KEY (username,item_name)
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
  total_seats int(16),  
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
  PRIMARY KEY (room_number,shelter_id)
);

CREATE TABLE Bunk (
  bunk_number int(16) unsigned NOT NULL AUTO_INCREMENT ,
  bunk_type int(16) unsigned NOT NULL,
  shelter_id int(16) unsigned NOT NULL,
  occupied boolean,  
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


--  Table Constraints 

ALTER TABLE `User`
  ADD CONSTRAINT User_ibfk_1 FOREIGN KEY (site_id) REFERENCES `Site` (site_id);
  
ALTER TABLE `Provide`
  ADD CONSTRAINT Provide_ibfk_1 FOREIGN KEY (site_id) REFERENCES `Site` (site_id),
  ADD CONSTRAINT Provide_ibfk_2 FOREIGN KEY (food_bank_id) REFERENCES `Food_Bank` (food_bank_id) ON DELETE SET NULL,
  ADD CONSTRAINT Provide_ibfk_3 FOREIGN KEY (food_pantry_id) REFERENCES `Food_Pantry` (food_pantry_id) ON DELETE SET NULL, 
  ADD CONSTRAINT Provide_ibfk_4 FOREIGN KEY (soup_kitchen_id) REFERENCES `Soup_Kitchen` (soup_kitchen_id) ON DELETE SET NULL,
  ADD CONSTRAINT Provide_ibfk_5 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE SET NULL;

ALTER TABLE `Item`
  ADD CONSTRAINT Item_ibfk_1 FOREIGN KEY (food_bank_id) REFERENCES `Food_Bank` (food_bank_id) ON DELETE SET NULL,
  ADD CONSTRAINT Item_ibfk_2 FOREIGN KEY (storage_type) REFERENCES `Item_storage_type_enum` (storage_type),
  ADD CONSTRAINT Item_ibfk_3 FOREIGN KEY (item_type) REFERENCES `Item_type_enum` (item_type),
  ADD CONSTRAINT Item_ibfk_4 FOREIGN KEY (food_category) REFERENCES `Item_food_category_enum` (food_category),
  ADD CONSTRAINT Item_ibfk_5 FOREIGN KEY (supply_category) REFERENCES `Item_supply_category_enum` (supply_category);

ALTER TABLE `Request`
  ADD CONSTRAINT Request_ibfk_1 FOREIGN KEY (username) REFERENCES `User` (username),
  ADD CONSTRAINT Request_ibfk_2 FOREIGN KEY (item_name) REFERENCES `Item` (item_name);
    

ALTER TABLE `Room`
  ADD CONSTRAINT Room_ibfk_1 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE;
      
ALTER TABLE `Bunk`
  ADD CONSTRAINT Bunk_ibfk_1 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE,
  ADD CONSTRAINT Bunk_ibfk_2 FOREIGN KEY (bunk_type) REFERENCES `Bunk_type_enum` (bunk_type);;
        
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

INSERT INTO `cs6400_sp17_team073`.`Bunk_type_enum`  (`bunk_type_name`) VALUES ('male');
INSERT INTO `cs6400_sp17_team073`.`Bunk_type_enum`  (`bunk_type_name`) VALUES ('female');
INSERT INTO `cs6400_sp17_team073`.`Bunk_type_enum`  (`bunk_type_name`) VALUES ('mixed');

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
INSERT INTO `cs6400_sp17_team073`.`Food_Pantry` (description_string, hours, conditions_for_use) VALUES ('pantry2', '6:00 pm - 10:00 pm','Non-violent');
--  o 2 Soup Kitchens (hours of operation, conditions, seats_avail) use short names: ('soup3', etc.)
INSERT INTO `cs6400_sp17_team073`.`Soup_Kitchen` (description_string, hours, conditions_for_use, available_seats, total_seats) VALUES('soup2', '5:00 pm - 8:00 pm', 'Sober', 10,10);
INSERT INTO `cs6400_sp17_team073`.`Soup_Kitchen` (description_string, hours, conditions_for_use, available_seats, total_seats) VALUES('soup3', '5:00 pm - 8:00 pm', 'Sober', 10,10);
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


--  INSERT INTO `cs6400_sp17_team073`.`Item`(item_name, number_of_units, storage_type, item_type, food_category, supply_category, expiration_date, food_bank_id) VALUES('leafy veggies', 10, 1,2,1,1,DATE_ADD(NOW(), INTERVAL 1 DAY), 1);
