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
  site_id int(16) unsigned NOT NULL AUTO_INCREMENT,
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
  ADD CONSTRAINT Item_ibfk_1 FOREIGN KEY (food_bank_id) REFERENCES `Food_Bank` (food_bank_id) ON DELETE CASCADE,
  ADD CONSTRAINT Item_ibfk_2 FOREIGN KEY (storage_type) REFERENCES `Item_storage_type_enum` (storage_type),
  ADD CONSTRAINT Item_ibfk_3 FOREIGN KEY (item_type) REFERENCES `Item_type_enum` (item_type),
  ADD CONSTRAINT Item_ibfk_4 FOREIGN KEY (food_category) REFERENCES `Item_food_category_enum` (food_category),
  ADD CONSTRAINT Item_ibfk_5 FOREIGN KEY (supply_category) REFERENCES `Item_supply_category_enum` (supply_category);

ALTER TABLE `Request`
  ADD CONSTRAINT Request_ibfk_1 FOREIGN KEY (username) REFERENCES `User` (username),
  ADD CONSTRAINT Request_ibfk_2 FOREIGN KEY (item_name) REFERENCES `Item` (item_name) ON DELETE CASCADE;
    

ALTER TABLE `Room`
  ADD CONSTRAINT Room_ibfk_1 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE;
      
ALTER TABLE `Bunk`
  ADD CONSTRAINT Bunk_ibfk_1 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE;
        
ALTER TABLE `Waitlist`
  ADD CONSTRAINT Waitlist_ibfk_1 FOREIGN KEY (room_number) REFERENCES `Room` (room_number),
  ADD CONSTRAINT Waitlist_ibfk_2 FOREIGN KEY (client_id) REFERENCES `Client` (client_id),     
  ADD CONSTRAINT Waitlist_ibfk_3 FOREIGN KEY (shelter_id) REFERENCES `Shelter` (shelter_id) ON DELETE CASCADE;    
  
ALTER TABLE `Log_Entry`
  ADD CONSTRAINT Log_Entry_ibfk_1 FOREIGN KEY (client_id) REFERENCES `Client` (client_id);      
