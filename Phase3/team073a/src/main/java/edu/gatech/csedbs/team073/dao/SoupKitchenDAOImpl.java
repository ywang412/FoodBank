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
                        "SELECT Soup_Kitchen.soup_kitchen_id, Soup_Kitchen.description_string, Soup_Kitchen.hours, Soup_Kitchen.conditions_for_use, Soup_Kitchen.available_seats, Soup_Kitchen.seats_limit FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Soup_Kitchen on Soup_Kitchen.soup_kitchen_id=Provide.soup_kitchen_id WHERE Soup_Kitchen.soup_kitchen_id=:id", params,
                new RowMapper<SoupKitchen>() {

                    public SoupKitchen mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        SoupKitchen soupKitchen = new SoupKitchen();

                        soupKitchen.setDescriptionString(rs.getString("description_string"));
                        soupKitchen.setSoupKitchenId(rs.getInt("soup_kitchen_id"));
                        soupKitchen.setHours(rs.getString("hours"));
                        soupKitchen.setConditionsForUse(rs.getString("conditions_for_use"));
                        soupKitchen.setAvailableSeats(rs.getInt("available_seats"));
                        soupKitchen.setSeatLimit(rs.getInt("seats_limit"));
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



        return skitchens;
    }


    public boolean updateSoupKitchen(int id, String description_string, String hours, String conditions_for_use, int available_seats, int seats_limit) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", id);
        params.addValue("description_string", description_string);
        params.addValue("hours", hours);
        params.addValue("conditions_for_use", conditions_for_use);
        params.addValue("available_seats", available_seats);
        params.addValue("seats_limit", seats_limit);
        //if this doesn't work or gets exceptin then it needs to return an error

        String sql = "UPDATE cs6400_sp17_team073.soup_kitchen SET description_string = :description_string, " +
                "hours=:hours, conditions_for_use = :conditions_for_use, available_seats = :available_seats , seats_limit = :seats_limit" +
                " WHERE soup_kitchen_id=:id";



        jdbc.update(sql,params);

        return true;
    }


    public boolean decrementSoupKitchenSeats(int id) {

        //get current number of seats
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        //here we want to get total from food pantry table
        String sql = "SELECT Soup_Kitchen.available_seats FROM cs6400_sp17_team073.Soup_Kitchen WHERE soup_kitchen_id=:id";


        Integer available_seats = jdbc.queryForObject(sql,params,Integer.class);

        if (available_seats > 0) --available_seats;

        params.addValue("available_seats", available_seats);
        //now update that number but subtracted by 1
        String sql2 = "UPDATE cs6400_sp17_team073.soup_kitchen SET " +
                "available_seats = :available_seats " +
                "WHERE soup_kitchen_id=:id";


        //todo deal with error handling here
        jdbc.update(sql2,params);

        return true;
    }


    //TODO add in a constraint in the database for incrementing and decrementing availables seats

    //constraint to be > 0
    //contraint to be < = seat limit

    public boolean incrementSoupKitchenSeats(int id) {

        //get current number of seats
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        //here we want to get total from food pantry table
        String sql = "SELECT Soup_Kitchen.available_seats FROM cs6400_sp17_team073.Soup_Kitchen WHERE soup_kitchen_id=:id";


        Integer available_seats = jdbc.queryForObject(sql,params,Integer.class);

        String sql3 = "SELECT Soup_Kitchen.seats_limit FROM cs6400_sp17_team073.Soup_Kitchen WHERE soup_kitchen_id=:id";

        Integer seats_limit = jdbc.queryForObject(sql3,params,Integer.class);

        if (available_seats < seats_limit) ++available_seats;

        params.addValue("available_seats", available_seats);
        //now update that number but subtracted by 1
        String sql2 = "UPDATE cs6400_sp17_team073.soup_kitchen SET " +
                "available_seats = :available_seats " +
                "WHERE soup_kitchen_id=:id";


        //todo deal with error handling here
        jdbc.update(sql2,params);

        return true;
    }


}
