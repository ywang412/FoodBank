package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodPantry;
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
        String sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.food_pantry";

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

    //return all food pantry objects in an array
    public List  GetFoodPantryTable() {

        //ArrayList<FoodPantry>  fpantries = new ArrayList<FoodPantry>();


        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.food_pantry";

        List <FoodPantry> fpantries = jdbcTemplate.query(sql, new PantryMapper());

        /*
        List<Map> rows = jdbcTemplate.queryForList(sql);

        for (Map row : rows) {
            FoodPantry fpantry = new FoodPantry();
            fpantry.setFoodPantryId( (int)(row.get("food_pantry_id"))  ) ;
            fpantry.setDescriptionString( (String) row.get("description_string")  ) ;
            fpantry.setHours( (String) row.get("hours")  ) ;
            fpantry.setConditionsForUse( (String) row.get("conditions_for_use")  ) ;
            fpantries.add(fpantry);
        }
        */

        return fpantries;
}



}
