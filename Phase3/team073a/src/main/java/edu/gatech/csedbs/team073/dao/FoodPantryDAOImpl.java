package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodPantry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by swengineer on 4/9/17.
 */

@Component("FoodPantryDAO")

public class FoodPantryDAOImpl implements FoodPantryDAO{


    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
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




}
