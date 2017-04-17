package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.ItemType;
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
@Component("ItemTypeDAO")
public class ItemTypeDAOImpl implements ItemTypeDAO {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }


    public class ItemTypeMapper implements RowMapper<ItemType> {
        public ItemType mapRow(ResultSet row, int rowNum) throws SQLException {
            ItemType itemType = new ItemType();

            itemType.setItem_type( (int)(row.getInt("item_type"))  ); ;


            itemType.setItem_type_name( (String) row.getString("item_type_name")  ); ;


            return itemType;
        }
    }

    public List GetAllItemTypes() {

        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Item_type_enum";

        List <ItemType> ItemTypeList = jdbcTemplate.query(sql, new ItemTypeMapper());




        return ItemTypeList;
    }



}
