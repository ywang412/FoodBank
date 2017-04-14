package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.SoupKitchen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */
@Component("SoupKitchenDAO")
public class SoupKitchenDAOImpl implements SoupKitchenDAO{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {

        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }


    public SoupKitchen getSoupKitchen(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("\n" +
                        "SELECT Soup_Kitchen.soup_kitchen_id, Soup_Kitchen.description_string, Soup_Kitchen.hours, Soup_Kitchen.conditions_for_use, Soup_Kitchen.available_seats FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Soup_Kitchen on Soup_Kitchen.soup_kitchen_id=Provide.soup_kitchen_id WHERE Soup_Kitchen.soup_kitchen_id=:id", params,
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



    public SoupKitchen getSoupKitchenbysiteID(int id) {
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


    //return the total count of food pantries
    public int getSoupKitchenCount() {

        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.soup_kitchen";

        Integer skcount = jdbc.queryForObject(sql,params,Integer.class);

        return skcount;
    }

    public class SkitchenMapper implements RowMapper<SoupKitchen> {
        public SoupKitchen mapRow(ResultSet row, int rowNum) throws SQLException {
            SoupKitchen skitchen = new SoupKitchen();

            skitchen.setSoupKitchenId( (int)(row.getInt("soup_kitchen_id"))  ); ;
            skitchen.setDescriptionString( (String) row.getString("description_string")  ) ;
            skitchen.setHours( (String) row.getString("hours")  ) ;
            skitchen.setConditionsForUse( (String) row.getString("conditions_for_use")  ) ;

            skitchen.setAvailableSeats((int)(row.getInt("available_seats"))  );
            return skitchen;
        }
    }


    //return all food pantry objects in an array
    public List  GetSoupKitchenTable() {

        //ArrayList<FoodPantry>  fpantries = new ArrayList<FoodPantry>();


        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.soup_kitchen";

        List<SoupKitchen> skitchens = jdbcTemplate.query(sql, new SkitchenMapper());

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

        return skitchens;
    }


}
