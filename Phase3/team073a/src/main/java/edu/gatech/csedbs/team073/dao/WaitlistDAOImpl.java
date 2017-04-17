package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Position;
import edu.gatech.csedbs.team073.model.Waitlist;
import javafx.geometry.Pos;
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
import java.util.List;

/**
 * Created by swengineer on 4/9/17.
 */
@Component
public class WaitlistDAOImpl  implements WaitlistDAO {


    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {

        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }


    @Override
    public List<Waitlist> getAllWaitlist(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try{
            return jdbc.query("select *, Shelter.description_string as shelter_ds, Client.description_string as client_ds from Shelter LEFT JOIN Waitlist on Shelter.shelter_id = Waitlist.shelter_id  LEFT JOIN Client on Client.client_id = Waitlist.client_id WHERE Shelter.shelter_id =:id", params,
                    new RowMapper<Waitlist>() {

                        public Waitlist mapRow(ResultSet rs, int rowNum)
                                throws SQLException {

                            Waitlist waitlist = new Waitlist();
                            waitlist.setClientId(rs.getInt("client_id"));
                            waitlist.setDescription(rs.getString("shelter_ds"));
                            waitlist.setFullName(rs.getString("full_name"));
                            waitlist.setHeadOfHousehold(rs.getBoolean("head_of_household"));
                            waitlist.setPosition(rs.getInt("position"));
                            waitlist.setRoomNumber(rs.getInt("room_number"));
                            waitlist.setShelterID(rs.getInt("shelter_id"));

                            return waitlist;
                        }

                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Waitlist> getClientWaitlist(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try{
            return jdbc.query("select *, Shelter.description_string as shelter_ds, Client.description_string as client_ds from Shelter LEFT JOIN Waitlist on Shelter.shelter_id = Waitlist.shelter_id  LEFT JOIN Client on Client.client_id = Waitlist.client_id WHERE Client.client_id =:id", params,
                    new RowMapper<Waitlist>() {

                        public Waitlist mapRow(ResultSet rs, int rowNum)
                                throws SQLException {

                            Waitlist waitlist = new Waitlist();
                            waitlist.setClientId(rs.getInt("client_id"));
                            waitlist.setDescription(rs.getString("shelter_ds"));
                            waitlist.setFullName(rs.getString("full_name"));
                            waitlist.setHeadOfHousehold(rs.getBoolean("head_of_household"));
                            waitlist.setPosition(rs.getInt("position"));
                            waitlist.setRoomNumber(rs.getInt("room_number"));
                            waitlist.setShelterID(rs.getInt("shelter_id"));

                            return waitlist;
                        }

                    });

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    @Override
    public boolean removeClientWaitlist(int client_id, int shelter_id) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("client_id", client_id);
        params.addValue("shelter_id", shelter_id);

        Position pos = jdbc.queryForObject("select position from Waitlist where client_id =:client_id and shelter_id =:shelter_id", params,
                new RowMapper<Position>() {

                    public Position mapRow(ResultSet rs, int rowNum)
                            throws SQLException {

                        Position position = new Position();
                        position.setP(rs.getInt("position"));

                        return position;
                    }

                });

        params.addValue("clientpos", pos.getP());

        if (pos.getP()>0){
        //now remove from the soup kitchen table
        String sql2 = "delete from cs6400_sp17_team073.Waitlist WHERE client_id =:client_id and shelter_id =:shelter_id";

        jdbc.update(sql2,params);

        String sql ="Update cs6400_sp17_team073.Waitlist  Set position = position - 1  Where shelter_id = 1 and position >:clientpos";

        jdbc.update(sql,params);}

        return true;
    }


}
