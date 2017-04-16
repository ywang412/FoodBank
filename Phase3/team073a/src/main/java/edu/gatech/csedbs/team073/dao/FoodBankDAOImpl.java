package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.FoodBank;
import edu.gatech.csedbs.team073.model.Item;
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


@Component("FoodBankDAO")

public  class FoodBankDAOImpl  implements FoodBankDAO{



    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
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


    public class ItemMapper implements RowMapper<Item> {
        public Item mapRow(ResultSet row, int rowNum) throws SQLException {
            Item i = new Item();
            i.itemName=row.getString("item_name");
            i.numberOfUnits=row.getInt("number_of_units");
            i.storageType=row.getString("storage_type_name");
            i.itemType=row.getString("item_type_name");
            i.foodCategory=row.getString("food_category_name");
            i.supplyCategory=row.getString("supply_category_name");
            i.expirationDate=row.getString("expiration_date");
            i.foodBank=row.getString("short_name");

            return i;
        }
    }


    public List GetItemsInFoodBank(int id) {
        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_type_enum NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum NATURAL JOIN cs6400_sp17_team073.Item_supply_category_enum NATURAL JOIN cs6400_sp17_team073.Item_storage_type_enum NATURAL JOIN cs6400_sp17_team073.Provide NATURAL JOIN cs6400_sp17_team073.Site";

        List <Item> items = jdbc.query(sql, params, new ItemMapper());
        return items;
    }


}
