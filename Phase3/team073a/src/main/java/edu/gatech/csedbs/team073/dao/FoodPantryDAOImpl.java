package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodPantry;
import edu.gatech.csedbs.team073.model.Request;
import edu.gatech.csedbs.team073.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

/**
 * Created by swengineer on 4/9/17.
 */

@Component("FoodPantryDAO")

public class FoodPantryDAOImpl implements FoodPantryDAO{

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }

    public FoodPantry getFoodPantry(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select Food_Pantry.food_pantry_id, Food_Pantry.description_string, Food_Pantry.hours, Food_Pantry.conditions_for_use FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Food_Pantry on Food_Pantry.food_pantry_id=Provide.food_pantry_id WHERE Food_Pantry.food_pantry_id=:id", params,
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



    public FoodPantry getFoodPantrybysiteID(int id) {
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



    //return the total count of food pantries
    public int getFoodPantryCount() {

        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.Food_pantry";

        Integer fcount = jdbc.queryForObject(sql,params,Integer.class);

        return fcount;
    }



    public class PantryMapper implements RowMapper<FoodPantry> {
        public FoodPantry mapRow(ResultSet row, int rowNum) throws SQLException {
            FoodPantry pantry = new FoodPantry();

            pantry.setFoodPantryId( (int)(row.getInt("food_pantry_id"))  ) ;
            pantry.setDescriptionString( (String) row.getString("description_string")  ) ;
            pantry.setHours( (String) row.getString("hours")  ) ;
            pantry.setConditionsForUse( (String) row.getString("conditions_for_use")  ) ;

            return pantry;
        }
    }

    public List  GetItemTable() {
        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_type_enum NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum NATURAL JOIN cs6400_sp17_team073.Item_supply_category_enum NATURAL JOIN cs6400_sp17_team073.Item_storage_type_enum NATURAL JOIN cs6400_sp17_team073.Provide NATURAL JOIN cs6400_sp17_team073.Site";

        List <Item> items = jdbcTemplate.query(sql, new ItemMapper());
        return items;
    }
    public List  GetRequestTable() {
        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Request NATURAL JOIN cs6400_sp17_team073.Request_status_enum";

        List <Request> requests = jdbcTemplate.query(sql, new RequestMapper());
        return requests;
    }
    //return all food pantry objects in an array
    public List  GetFoodPantryTable() {

        //ArrayList<FoodPantry>  fpantries = new ArrayList<FoodPantry>();


        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Food_pantry";

        List <FoodPantry> fpantries = jdbcTemplate.query(sql, new PantryMapper());



        return fpantries;
    }


    public boolean updateFoodPantry(int id, String description_string, String hours, String conditions_for_use) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", id);
        params.addValue("description_string", description_string);
        params.addValue("hours", hours);
        params.addValue("conditions_for_use", conditions_for_use);
        //if this doesn't work or gets exceptin then it needs to return an error

        String sql = "UPDATE cs6400_sp17_team073.Food_pantry SET description_string = :description_string, " +
                "hours=:hours, conditions_for_use = :conditions_for_use " +
                "WHERE food_pantry_id=:id";



        jdbc.update(sql,params);

        return true;
    }
    public class ItemMapper implements RowMapper<Item> {
        public Item mapRow(ResultSet row, int rowNum) throws SQLException {
            Item i = new Item();
            i.itemName=row.getString("item_name");
            i.numberOfUnits=row.getInt("number_of_units");
            i.storageType=row.getString("storage_type_name");
            i.itemType=row.getString("item_type_name");
            i.foodCategory=row.getString("food_category_name");
            i.supplyCategory=row.getString("supply_category_name");
            i.expirationDate=row.getString("expiration_date");
            i.foodBank=row.getString("short_name");

            return i;
        }
    }
    public class RequestMapper implements RowMapper<Request> {
        public Request mapRow(ResultSet row, int rowNum) throws SQLException {
            Request r = new Request();
            r.username=row.getString("username");
            r.item_name=row.getString("item_name");
            r.request_date=row.getString("request_date");
            r.request_status=row.getString("request_status_name");
            r.units_requested=row.getInt("units_requested");
            r.units_fulfilled=row.getInt("units_fulfilled");

            return r;
        }
    }



}
