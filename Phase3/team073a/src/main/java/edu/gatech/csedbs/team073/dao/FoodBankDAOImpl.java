package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodBank;
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


@Component("FoodBankDAO")

public  class FoodBankDAOImpl  implements FoodBankDAO{



    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }


    public FoodBank getFoodBank(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select Food_Bank.food_bank_id, Food_Bank.description_string FROM Site LEFT JOIN Provide on Provide.site_id=Site.site_id LEFT JOIN Food_Bank on Food_Bank.food_bank_id=Provide.food_bank_id WHERE Site.site_id=:id", params,
                new RowMapper<FoodBank>() {

                    public FoodBank mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        FoodBank foodBank = new FoodBank();

                        foodBank.setDescriptionString(rs.getString("description_string"));
                        foodBank.setFoodBankId(rs.getInt("food_bank_id"));

                        return foodBank;
                    }

                });
    }

}
