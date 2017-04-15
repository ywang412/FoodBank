package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Shelter;
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

@Component("ShelterDAO")
public class ShelterDAOImpl implements ShelterDAO {



    private NamedParameterJdbcTemplate jdbc;
    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }


    public Shelter getShelter(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("SELECT Shelter.shelter_id, Shelter.description_string, Shelter.hours, Shelter.conditions_for_use, Shelter.available_bunks, Shelter.available_rooms FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Shelter on Shelter.shelter_id=Provide.shelter_id WHERE Shelter.shelter_id=:id", params,
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




    public Shelter getShelterbysiteID(int id) {
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

    //return the total count of food pantries
    public int getShelterCount() {

        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.Shelter";

        Integer sheltercount = jdbc.queryForObject(sql,params,Integer.class);

        return sheltercount;
    }

    public class ShelterMapper implements RowMapper<Shelter> {
        public Shelter mapRow(ResultSet row, int rowNum) throws SQLException {
            Shelter shelter = new Shelter();

            shelter.setShelterId( (int)(row.getInt("shelter_id"))  ); ;
            shelter.setDescriptionString( (String) row.getString("description_string")  ) ;
            shelter.setHours( (String) row.getString("hours")  ) ;
            shelter.setConditionsForUse( (String) row.getString("conditions_for_use")  ) ;

            shelter.setAvailableBunks((int)(row.getInt("available_bunks"))  );
            shelter.setAvailableRooms((int)(row.getInt("available_rooms"))  );
            return shelter;
        }
    }


    //return all food pantry objects in an array
    public List GetShelterTable() {




        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Shelter";

        List<Shelter> shelters = jdbcTemplate.query(sql, new ShelterMapper());

        return shelters;
    }


    public boolean updateShelter(int id, String description_string, String hours, String conditions_for_use, int available_bunks,int available_rooms) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("id", id);
        params.addValue("description_string", description_string);
        params.addValue("hours", hours);
        params.addValue("conditions_for_use", conditions_for_use);
        params.addValue("available_bunks", available_bunks);
        params.addValue("available_rooms", available_rooms);
        //if this doesn't work or gets exceptin then it needs to return an error

        String sql = "UPDATE cs6400_sp17_team073.Shelter SET description_string = :description_string, " +
                "hours=:hours, conditions_for_use = :conditions_for_use, available_bunks = :available_bunks, available_rooms = :available_rooms " +
                "WHERE shelter_id=:id";



        jdbc.update(sql,params);

        return true;
    }

}
