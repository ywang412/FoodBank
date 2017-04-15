package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
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

@Component("RoomDAO")
public class RoomDAOImpl implements RoomDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }

    public class RoomMapper implements RowMapper<Room> {
        public Room mapRow(ResultSet row, int rowNum) throws SQLException {
            Room room = new Room();

            room.setRoomNumber( (int)(row.getInt("room_number"))  );
            room.setShelterId( (int)(row.getInt("shelter_id"))  );
            room.setClientId( (int)(row.getInt("client_id"))  );
            return room;
        }
    }


    public Room getRoom(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String sql = "SELECT Room.room_number, Room.shelter_id, Room.client_id FROM cs6400_sp17_team073.Room WHERE Room.shelter_id=:id";


        Room room = jdbc.queryForObject(sql,params,new RoomMapper());

        return room;
    }

    public int   getRoomCountByShelterIdAndOccupancy(int shelterId, boolean occupied) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);
        String sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id =:id AND Room.client_id IS NULL";


        if (occupied) {
            sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id =:id AND Room.client_id IS NOT NULL";
        }



        Integer roomcount = jdbc.queryForObject(sql,params,Integer.class);


        return roomcount;
    }



    public Integer findNextAvailableRoom(int shelterId) {
        Integer room_number=0;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);



        //get the first bunk number that is aviailable for that type
        //this would be using an 'order by'
        String sql = "SELECT cs6400_sp17_team073.Room.room_number FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id=:id AND Room.client_id IS NULL ORDER BY Room.room_number ASC LIMIT 1";

        room_number = jdbc.queryForObject(sql,params,Integer.class);



        return room_number;
    }


}
