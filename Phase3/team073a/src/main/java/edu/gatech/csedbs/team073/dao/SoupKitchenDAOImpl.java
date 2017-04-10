package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.SoupKitchen;
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
@Component("SoupKitchenDAO")
public class SoupKitchenDAOImpl implements SoupKitchenDAO{


    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }


    public SoupKitchen getSoupKitchen(int id) {
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

}
