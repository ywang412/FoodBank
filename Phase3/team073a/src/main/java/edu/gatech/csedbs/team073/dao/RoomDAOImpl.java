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
            room.setOccupied( (boolean) (row.getBoolean("occupied"))  );
            return room;
        }
    }


    public Room getRoom(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String sql = "SELECT Room.room_number, Room.shelter_id, Room.occupied FROM cs6400_sp17_team073.Room WHERE Room.shelter_id=:id";


        Room room = jdbc.queryForObject(sql,params,new RoomMapper());

        return room;
    }

    public int   getRoomCountByShelterIdAndOccupancy(int shelterId, boolean occupied) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);
        String sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id =:id AND Room.occupied = FALSE";


        if (occupied) {
            sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id =:id AND Room.occupied = TRUE";
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
        String sql = "SELECT cs6400_sp17_team073.Room.room_number FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id=:id AND Room.occupied = FALSE ORDER BY Room.room_number ASC LIMIT 1";

        room_number = jdbc.queryForObject(sql,params,Integer.class);



        return room_number;
    }

    public Integer claimNextAvailableRoom(int shelterId) {
        Integer room_number=0;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);



        //get the first bunk number that is aviailable for that type
        //this would be using an 'order by'
        String sql = "SELECT cs6400_sp17_team073.Room.room_number FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id=:id AND Room.occupied = FALSE ORDER BY Room.room_number ASC LIMIT 1";

        room_number = jdbc.queryForObject(sql,params,Integer.class);


        //then update it to be 'occupied'
        params.addValue("occupied", true);
        params.addValue("room_number", room_number);
        String sql2 = "UPDATE cs6400_sp17_team073.Room SET Room.occupied = TRUE " +
                "WHERE Room.room_number=:room_number";

        //TODO make series of queries atomic - put a IN and subquery so that it all happens in the database
        jdbc.update(sql2,params);


        return room_number;
    }


    public Integer releaseRoom(int shelterId) {
        Integer room_number=0;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);
        params.addValue("occupied", true);


        //get the first bunk number that is occupied for that type

        String sql = "SELECT cs6400_sp17_team073.Room.room_number FROM cs6400_sp17_team073.Room WHERE  Room.shelter_id=:id AND Room.occupied = TRUE ORDER BY Room.room_number ASC LIMIT 1 ";

        room_number = jdbc.queryForObject(sql,params,Integer.class);

        //then update it to be 'occupied'
        params.addValue("occupied", false);
        params.addValue("room_number", room_number);
        String sql2 = "UPDATE cs6400_sp17_team073.Room SET Room.occupied = FALSE " +
                "WHERE  Room.room_number=:room_number";

        //TODO make series of queries atomic - put a IN and subquery so that it all happens in the database
        jdbc.update(sql2,params);

        return room_number;
    }


}
