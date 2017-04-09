INSERT INTO `cs6400_sp17_team073`.`Site` (`site_id`, `short_name`, `street_address`, `city`, `state`, `full_name`, `zip`, `contact_number`) VALUES ('101', 'TestAus1', '100 Main', 'Austin', 'TX', 'Austin Downtown', '78701', '5125551212');
INSERT INTO `cs6400_sp17_team073`.`Site` (`site_id`, `short_name`, `street_address`, `city`, `state`, `full_name`, `zip`, `contact_number`) VALUES ('102', 'TestSA1', '200 Side', 'San Antonio', 'TX', 'San Antonio Downtown 1', '787301', '2105551212');
INSERT INTO `cs6400_sp17_team073`.`User` (`username`, `user_email`, `password`, `full_name`, `site_id`) VALUES ('TestAus1Admin', 'testa1@gatech.edu', 'TestA1$', 'Test Austin Admin 1', '101');
INSERT INTO `cs6400_sp17_team073`.`User` (`username`, `user_email`, `password`, `full_name`, `site_id`) VALUES ('TestSAAdmin1', 'testb1@gatech.edu', 'TestB1$', 'Test SA Admin 1', '102');
INSERT INTO `cs6400_sp17_team073`.`User` (`username`, `user_email`, `password`, `full_name`, `site_id`) VALUES ('TestNoAdmin', 'testc1@gatech.edu', 'TestC1$', 'Test No Admin', null);
INSERT INTO `cs6400_sp17_team073`.`Provide` (`site_id`, `food_bank_id`, `food_pantry_id`, `soup_kitchen_id`, `shelter_id`) VALUES ('101', '201', '301', '401', '501');

INSERT INTO `cs6400_sp17_team073`.`Food_Bank` (`food_bank_id`, `description_string`) VALUES ('201', 'Roadside steakhouse');
INSERT INTO `cs6400_sp17_team073`.`Food_Pantry` (`food_pantry_id`, `description_string`, `hours`, `conditions_for_use`) VALUES ('301', 'Star taco', '8am-9pm', 'knock knock');
INSERT INTO `cs6400_sp17_team073`.`Soup_Kitchen` (`soup_kitchen_id`, `description_string`, `hours`, `conditions_for_use`, `available_seats`) VALUES ('401', 'Wendy\'s kitchen', '8am-6pm', 'kick kick',12);
INSERT INTO `cs6400_sp17_team073`.`Shelter` (`shelter_id`, `description_string`,  `hours`, `conditions_for_use`, `available_bunks`, `available_rooms`) VALUES ('501', 'Papas shelter', '0am-12pm', 'show me your id',12, 8);
INSERT INTO `cs6400_sp17_team073`.`Provide` (`site_id`, `food_bank_id`, `food_pantry_id`, `soup_kitchen_id`, `shelter_id`) VALUES ('101', '201', '301', '401', '501');

SELECT Food_Pantry.description_string, Food_Pantry.hours, Food_Pantry.conditions_for_use FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Food_Pantry on Food_Pantry.food_pantry_id=Provide.food_pantry_id WHERE Site.site_id=101;


SELECT Food_Bank.food_bank_id , Food_Bank.description_string FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Food_Bank on Food_Bank.food_bank_id=Provide.food_bank_id WHERE Site.site_id=101;

SELECT Soup_Kitchen.soup_kitchen_id, Soup_Kitchen.description_string, Soup_Kitchen.hours, Soup_Kitchen.conditions_for_use, Soup_Kitchen.available_seats FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Soup_Kitchen on Soup_Kitchen.soup_kitchen_id=Provide.soup_kitchen_id WHERE Site.site_id=101;



SELECT Shelter.shelter_id, Shelter.description_string, Shelter.hours, Shelter.conditions_for_use, Shelter.available_bunks, Shelter.available_rooms FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Shelter on Shelter.shelter_id=Provide.shelter_id WHERE Site.site_id=101;




