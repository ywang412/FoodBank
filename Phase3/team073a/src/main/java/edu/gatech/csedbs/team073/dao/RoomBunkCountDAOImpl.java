package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.RoomBunkCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * Created by swengineer on 4/14/17.
 */

@Component("RoomBunkCountDAO")
public class RoomBunkCountDAOImpl implements RoomBunkCountDAO {


    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {

        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }


    @Override
    public RoomBunkCount getAvailableRoom(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try{
        return jdbc.queryForObject("select Site.site_id, Site.street_address, Site.full_name, Shelter.shelter_id, Site.full_name, Shelter.hours, Site.contact_number, Shelter.available_rooms, 0 as type FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Shelter on Shelter.shelter_id=Provide.shelter_id WHERE Site.site_id=:id", params,
                new RowMapper<RoomBunkCount>() {

                    public RoomBunkCount mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        RoomBunkCount roomBunkCount = new RoomBunkCount();

                        roomBunkCount.setRoomBunkType(rs.getInt("type"));
                        roomBunkCount.setRoomBunkCount(rs.getInt("available_rooms"));
                        roomBunkCount.setContactNumber(rs.getString("contact_number"));
                        roomBunkCount.setHours(rs.getString("hours"));
                        roomBunkCount.setShelterId(rs.getInt("shelter_id"));
                        roomBunkCount.setSiteId(rs.getInt("site_id"));
                        roomBunkCount.setAddress(rs.getString("street_address"));
                        roomBunkCount.setSiteName(rs.getString("full_name"));


                        return roomBunkCount;
                    }

                });

    } catch (EmptyResultDataAccessException e) {
        return null;
    }
    }

    public RoomBunkCount getAvailableBunk_Male(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            return jdbc.queryForObject("select Site.site_id, Site.street_address, Site.full_name, Shelter.shelter_id, Site.full_name, Shelter.hours, Site.contact_number, Shelter.available_rooms, Bunk.bunk_type, count(*) as bunk_count FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Shelter on Shelter.shelter_id=Provide.shelter_id  LEFT JOIN Bunk on Shelter.shelter_id=Bunk.shelter_id WHERE Site.site_id=:id and Bunk.occupied = 0 and bunk_type = 1  group by Bunk.bunk_type", params,
                    new RowMapper<RoomBunkCount>() {

                        public RoomBunkCount mapRow(ResultSet rs, int rowNum)
                                throws SQLException {
                            RoomBunkCount roomBunkCount = new RoomBunkCount();

                            roomBunkCount.setRoomBunkType(rs.getInt("bunk_type"));
                            roomBunkCount.setRoomBunkCount(rs.getInt("bunk_count"));
                            roomBunkCount.setContactNumber(rs.getString("contact_number"));
                            roomBunkCount.setHours(rs.getString("hours"));
                            roomBunkCount.setShelterId(rs.getInt("shelter_id"));
                            roomBunkCount.setSiteId(rs.getInt("site_id"));
                            roomBunkCount.setAddress(rs.getString("street_address"));
                            roomBunkCount.setSiteName(rs.getString("full_name"));


                            return roomBunkCount;
                        }

                    });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public RoomBunkCount getAvailableBunk_Female(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            return jdbc.queryForObject("select Site.site_id, Site.street_address, Site.full_name, Shelter.shelter_id, Site.full_name, Shelter.hours, Site.contact_number, Shelter.available_rooms, Bunk.bunk_type, count(*) as bunk_count FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Shelter on Shelter.shelter_id=Provide.shelter_id  LEFT JOIN Bunk on Shelter.shelter_id=Bunk.shelter_id WHERE Site.site_id=:id and Bunk.occupied = 0 and bunk_type = 2  group by Bunk.bunk_type", params,
                    new RowMapper<RoomBunkCount>() {

                        public RoomBunkCount mapRow(ResultSet rs, int rowNum)
                                throws SQLException {
                            RoomBunkCount roomBunkCount = new RoomBunkCount();

                            roomBunkCount.setRoomBunkType(rs.getInt("bunk_type"));
                            roomBunkCount.setRoomBunkCount(rs.getInt("bunk_count"));
                            roomBunkCount.setContactNumber(rs.getString("contact_number"));
                            roomBunkCount.setHours(rs.getString("hours"));
                            roomBunkCount.setShelterId(rs.getInt("shelter_id"));
                            roomBunkCount.setSiteId(rs.getInt("site_id"));
                            roomBunkCount.setAddress(rs.getString("street_address"));
                            roomBunkCount.setSiteName(rs.getString("full_name"));


                            return roomBunkCount;
                        }

                    });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public RoomBunkCount getAvailableBunk_Mixed(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            return jdbc.queryForObject("select Site.site_id, Site.street_address, Site.full_name, Shelter.shelter_id, Site.full_name, Shelter.hours, Site.contact_number, Shelter.available_rooms, Bunk.bunk_type, count(*) as bunk_count FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Shelter on Shelter.shelter_id=Provide.shelter_id  LEFT JOIN Bunk on Shelter.shelter_id=Bunk.shelter_id WHERE Site.site_id=:id and Bunk.occupied = 0 and bunk_type = 3  group by Bunk.bunk_type", params,
                    new RowMapper<RoomBunkCount>() {

                        public RoomBunkCount mapRow(ResultSet rs, int rowNum)
                                throws SQLException {
                            RoomBunkCount roomBunkCount = new RoomBunkCount();

                            roomBunkCount.setRoomBunkType(rs.getInt("bunk_type"));
                            roomBunkCount.setRoomBunkCount(rs.getInt("bunk_count"));
                            roomBunkCount.setContactNumber(rs.getString("contact_number"));
                            roomBunkCount.setHours(rs.getString("hours"));
                            roomBunkCount.setShelterId(rs.getInt("shelter_id"));
                            roomBunkCount.setSiteId(rs.getInt("site_id"));
                            roomBunkCount.setAddress(rs.getString("street_address"));
                            roomBunkCount.setSiteName(rs.getString("full_name"));


                            return roomBunkCount;
                        }

                    });
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}