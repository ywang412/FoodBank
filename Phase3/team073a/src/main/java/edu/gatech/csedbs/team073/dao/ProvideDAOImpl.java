package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.Provide;
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
 * Created by Phil on 4/12/2017.
 */


@Component("ProvideDAO")
public class ProvideDAOImpl implements ProvideDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;


    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }



    public Provide getProvide(int site_id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("site_id", site_id);

        return jdbc.queryForObject("select Provide.site_id,Provide.food_bank_id, Provide.food_pantry_id, Provide.soup_kitchen_id,  Provide.shelter_id FROM Provide WHERE Provide.site_id=:site_id", params,
                new RowMapper<Provide>() {

                    public Provide mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        Provide provides = new Provide();

                        provides.setSite_id(rs.getInt("site_id"));
                        provides.setFood_bank_id(rs.getInt("food_bank_id"));
                        provides.setFood_pantry_id(rs.getInt("food_pantry_id"));
                        provides.setSoup_kitchen_id(rs.getInt("soup_kitchen_id"));
                        provides.setShelter_id(rs.getInt("shelter_id"));

                        return provides;
                    }

                });


    }
}
