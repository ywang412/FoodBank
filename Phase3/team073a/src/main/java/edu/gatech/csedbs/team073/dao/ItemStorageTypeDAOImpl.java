package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.ItemStorageType;
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

@Component("ItemStorageTypeDAO")
public class ItemStorageTypeDAOImpl implements ItemStorageTypeDAO {


    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
        this.jdbcTemplate = new JdbcTemplate(jdbc);
    }



    public class ItemStorageTypeMapper implements RowMapper<ItemStorageType> {
        public ItemStorageType mapRow(ResultSet row, int rowNum) throws SQLException {
            ItemStorageType itemstoragetype = new ItemStorageType();

            itemstoragetype.setStorage_type( (int)(row.getInt("storage_type"))  ); ;


            itemstoragetype.setStorage_type_name( (String) row.getString("storage_type_name")  ); ;


            return itemstoragetype;
        }
    }

    public List GetAllStorageTypes() {

        //here we want to to a SELECT * FROM food_pantry  table
        MapSqlParameterSource params = new MapSqlParameterSource();
        //here we want to get total from food pantry table
        String sql = "SELECT * FROM cs6400_sp17_team073.Item_storage_type_enum";

        List <ItemStorageType> ItemStorageTypeList = jdbcTemplate.query(sql, new ItemStorageTypeMapper());




        return ItemStorageTypeList;
    }


}
