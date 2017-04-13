package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Shelter;
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

@Component("ShelterDAO")
public class ShelterDAOImpl implements ShelterDAO {



    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }


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
