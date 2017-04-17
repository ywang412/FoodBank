package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.ItemSupplyCategory;
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
@Component("ItemSupplyCategoryDAO")
public class ItemSupplyCategoryDAOImpl implements ItemSupplyCategoryDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }


    public class ItemSupplyCategoryMapper implements RowMapper<ItemSupplyCategory> {
        public ItemSupplyCategory mapRow(ResultSet row, int rowNum) throws SQLException {
            ItemSupplyCategory supplycategory = new ItemSupplyCategory();

            supplycategory.setSupply_category( (int)(row.getInt("supply_category"))  ); ;


            supplycategory.setSupply_category_name( (String) row.getString("supply_category_name")  ); ;


            return supplycategory;
        }
    }

    public List GetAllSupplyCategories() {

        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Item_supply_category_enum";

        List <ItemSupplyCategory> ItemSupplyCategoryList = jdbcTemplate.query(sql, new ItemSupplyCategoryMapper());




        return ItemSupplyCategoryList;
    }




}
