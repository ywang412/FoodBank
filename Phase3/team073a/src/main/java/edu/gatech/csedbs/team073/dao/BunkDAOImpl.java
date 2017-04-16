package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Bunk;
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

@Component("BunkDAO")
public class BunkDAOImpl implements BunkDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }



    public class BunkMapper implements RowMapper<Bunk> {
        public Bunk mapRow(ResultSet row, int rowNum) throws SQLException {
            Bunk bunk = new Bunk();

            bunk.setBunkNumber( (int)(row.getInt("bunk_number"))  );
            bunk.setBunkType( (int)(row.getInt("bunk_type"))  );
            bunk.setShelterId( (int)(row.getInt("shelter_id"))  );
            bunk.setOccupied( (boolean)(row.getBoolean("occupied"))  );
            return bunk;
        }
    }


    public Bunk getBunk(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        String sql = "SELECT Bunk.bunk_number, Bunk.bunk_type,Bunk.shelter_id,  Bunk.occupied FROM cs6400_sp17_team073.Bunk WHERE Bunk.shelter_id=:id";


        Bunk bunk = jdbc.queryForObject(sql,params,new BunkMapper());

        return bunk;
    }


    public int   getBunkCountByShelterIdAndTypeAndOccupancy(int shelterId, int type, boolean occupied) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);
        params.addValue("bunk_type", type);
        params.addValue("occupied", occupied);

        String sql = "SELECT COUNT(*) FROM cs6400_sp17_team073.Bunk WHERE  Bunk.shelter_id=:id AND Bunk.bunk_type=:bunk_type AND  Bunk.occupied =:occupied ";

        Integer bunkcount = jdbc.queryForObject(sql,params,Integer.class);


        return bunkcount;
    }

    public Integer claimNextAvailableBunk(int shelterId, int bunkType) {
        Integer bunk_number=0;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);
        params.addValue("bunk_type", bunkType);
        params.addValue("occupied", false);


        //get the first bunk number that is aviailable for that type
        //this would be using an 'order by'
        String sql = "SELECT cs6400_sp17_team073.Bunk.bunk_number FROM cs6400_sp17_team073.Bunk WHERE  Bunk.shelter_id=:id AND Bunk.bunk_type=:bunk_type AND  Bunk.occupied IS FALSE ORDER BY Bunk.bunk_number ASC LIMIT 1";

        bunk_number = jdbc.queryForObject(sql,params,Integer.class);

        //then update it to be 'occupied'
        params.addValue("occupied", true);
        params.addValue("bunk_number", bunk_number);
        String sql2 = "UPDATE cs6400_sp17_team073.Bunk SET Bunk.occupied = TRUE " +
                "WHERE Bunk.bunk_number=:bunk_number";

        //TODO make series of queries atomic - put a IN and subquery so that it all happens in the database
        jdbc.update(sql2,params);

        return bunk_number;
    }


    public Integer releaseBunk(int shelterId, int bunkType) {
        Integer bunk_number=0;

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", shelterId);
        params.addValue("bunk_type", bunkType);
        params.addValue("occupied", true);


        //get the first bunk number that is occupied for that type

        String sql = "SELECT cs6400_sp17_team073.Bunk.bunk_number FROM cs6400_sp17_team073.Bunk WHERE  Bunk.shelter_id=:id AND Bunk.bunk_type=:bunk_type AND  Bunk.occupied IS TRUE ORDER BY Bunk.bunk_number ASC LIMIT 1 ";

        bunk_number = jdbc.queryForObject(sql,params,Integer.class);

        //then update it to be 'occupied'
        params.addValue("occupied", false);
        params.addValue("bunk_number", bunk_number);
        String sql2 = "UPDATE cs6400_sp17_team073.Bunk SET Bunk.occupied = FALSE " +
                "WHERE  Bunk.bunk_number=:bunk_number";

        //TODO make series of queries atomic - put a IN and subquery so that it all happens in the database
        jdbc.update(sql2,params);

        return bunk_number;
    }

}
