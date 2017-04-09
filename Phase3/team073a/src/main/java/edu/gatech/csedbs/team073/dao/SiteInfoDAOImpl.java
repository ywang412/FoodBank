package edu.gatech.csedbs.team073.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.gatech.csedbs.team073.model.*;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by swengineer on 4/6/17.
 */

@Component("SiteInfoDAO")
public class SiteInfoDAOImpl implements SiteInfoDAO {


    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public SiteInfo getSiteInfo(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from Site where site_id=:id", params,
                new RowMapper<SiteInfo>() {

                    public SiteInfo mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        SiteInfo siteInfo = new SiteInfo();

                        siteInfo.setCity(rs.getString("site_id"));
                        siteInfo.setContactNumber(rs.getString("contact_number"));
                        siteInfo.setFullName(rs.getString("full_name"));
                        siteInfo.setShortName(rs.getString("short_name"));
                        siteInfo.setState(rs.getString("state"));
                        siteInfo.setZip(rs.getInt("zip"));
                        siteInfo.setStreetAddress(rs.getString("street_address"));
                        siteInfo.setSiteId(rs.getInt("site_id"));

                        return siteInfo;
                    }

                });
    }


    public FoodPantry getFoodPantry(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select Food_Pantry.food_pantry_id, Food_Pantry.description_string, Food_Pantry.hours, Food_Pantry.conditions_for_use FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Food_Pantry on Food_Pantry.food_pantry_id=Provide.food_pantry_id WHERE Site.site_id=:id", params,
                new RowMapper<FoodPantry>() {

                    public FoodPantry mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        FoodPantry foodPantry = new FoodPantry();

                        foodPantry.setDescriptionString(rs.getString("description_string"));
                        foodPantry.setFoodPantryId(rs.getInt("food_pantry_id"));
                        foodPantry.setHours(rs.getString("hours"));
                        foodPantry.setConditionsForUse(rs.getString("conditions_for_use"));

                        return foodPantry;
                    }

                });
    }





    @Override
    public FoodBank getFoodBank(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select Food_Bank.food_bank_id, Food_Bank.description_string FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Food_Bank on Food_Bank.food_bank_id=Provide.food_bank_id WHERE Site.site_id=:id", params,
                new RowMapper<FoodBank>() {

                    public FoodBank mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        FoodBank foodBank = new FoodBank();

                        foodBank.setDescriptionString(rs.getString("description_string"));
                        foodBank.setFoodBankId(rs.getInt("food_bank_id"));

                        return foodBank;
                    }

                });
    }

    @Override
    public SoupKitchen getSoupKitchen(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("\n" +
                        "SELECT Soup_Kitchen.soup_kitchen_id, Soup_Kitchen.description_string, Soup_Kitchen.hours, Soup_Kitchen.conditions_for_use, Soup_Kitchen.available_seats FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Soup_Kitchen on Soup_Kitchen.soup_kitchen_id=Provide.soup_kitchen_id WHERE Site.site_id=:id", params,
                new RowMapper<SoupKitchen>() {

                    public SoupKitchen mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        SoupKitchen soupKitchen = new SoupKitchen();

                        soupKitchen.setDescriptionString(rs.getString("description_string"));
                        soupKitchen.setSoupKitchenId(rs.getInt("soup_kitchen_id"));
                        soupKitchen.setHours(rs.getString("hours"));
                        soupKitchen.setConditionsForUse(rs.getString("conditions_for_use"));
                        soupKitchen.setAvailableSeats(rs.getInt("available_seats"));

                        return soupKitchen;
                    }

                });
    }



    @Override
    public Shelter getShelter(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("SELECT Shelter.shelter_id, Shelter.description_string, Shelter.hours, Shelter.conditions_for_use, Shelter.available_bunks, Shelter.available_rooms FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Shelter on Shelter.shelter_id=Provide.shelter_id WHERE Site.site_id=:id", params,
                new RowMapper<Shelter>() {

                    public Shelter mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Shelter shelter = new Shelter();

                        shelter.setConditionsForUse(rs.getString("conditions_for_use"));
                        shelter.setHours(rs.getString("hours"));
                        shelter.setDescriptionString(rs.getString("description_string"));
                        shelter.setAvailableBunks(rs.getInt("available_bunks"));
                        shelter.setAvailableRooms(rs.getInt("available_rooms"));
                        shelter.setShelterId(rs.getInt("shelter_id"));


                        return shelter;
                    }

                });
    }
}
