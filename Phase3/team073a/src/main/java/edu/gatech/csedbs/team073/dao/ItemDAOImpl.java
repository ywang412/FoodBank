package edu.gatech.csedbs.team073.dao;

/**
 * Created by Taylor on 4/6/2017.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ItemDAOImpl implements ItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(SiteDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    public ItemDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public int getMealCount() {
        String sql = "SELECT min(counts.count) AS low FROM (SELECT 'vegetable' AS type, count(item_name) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'vegetables' UNION SELECT 'mineral' AS type, count(item_name) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'beans' OR food_category = 'nuts' OR food_category = 'grains' UNION SELECT 'animal' AS type, count(item_name) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'meat' OR food_category = 'seafood' OR food_category = 'dairy') AS counts";


        Integer count = jdbcTemplate.query(sql, new ResultSetExtractor<Integer>() {

            @Override
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                //final String rsUserName = userName;
                if (rs.next()) {
                    return rs.getInt("low");
                }
                return -1;
            }

        });

        return count;
    }
    public String getCategoryWithCount(int count){
        return "TODO";
    }


}
