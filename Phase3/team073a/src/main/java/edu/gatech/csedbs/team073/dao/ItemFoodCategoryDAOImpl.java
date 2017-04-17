package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.ItemFoodCategory;
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
 * Created by Phil on 4/16/2017.
 */

@Component("ItemFoodCategoryDAO")
public class ItemFoodCategoryDAOImpl implements ItemFoodCategoryDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }


    public class ItemFoodCategoryMapper implements RowMapper<ItemFoodCategory> {
        public ItemFoodCategory mapRow(ResultSet row, int rowNum) throws SQLException {
            ItemFoodCategory foodcategory = new ItemFoodCategory();

            foodcategory.setFood_category( (int)(row.getInt("food_category"))  ); ;


            foodcategory.setFood_category_name( (String) row.getString("food_category_name")  ); ;


            return foodcategory;
        }
    }

    public List GetAllFoodCategories() {

        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Item_food_category_enum";

        List <ItemFoodCategory> ItemFoodCategoryList = jdbcTemplate.query(sql, new ItemFoodCategoryMapper());




        return ItemFoodCategoryList;
    }
}
