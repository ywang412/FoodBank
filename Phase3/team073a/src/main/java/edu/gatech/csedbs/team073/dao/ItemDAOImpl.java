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
import edu.gatech.csedbs.team073.model.MealCount;
import edu.gatech.csedbs.team073.model.Site;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class ItemDAOImpl implements ItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(SiteDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    public ItemDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public MealCount getMealCount() {
        String sql = "SELECT counts.count AS low, counts.type AS type FROM (SELECT 'vegetable' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum WHERE food_category_name = 'vegetables' UNION SELECT 'mineral' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum WHERE food_category_name = 'beans' OR food_category_name = 'nuts' OR food_category_name = 'grains' UNION SELECT 'animal' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum WHERE food_category_name = 'meat' OR food_category_name = 'seafood' OR food_category_name = 'dairy') AS counts order by counts.count asc limit 1";
//        String sql = "SELECT counts.count AS low, counts.type AS type FROM (SELECT 'vegetable' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'vegetables' UNION SELECT 'mineral' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'beans' OR food_category = 'nuts' OR food_category = 'grains' UNION SELECT 'animal' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'meat' OR food_category = 'seafood' OR food_category = 'dairy') AS counts order by counts.count asc limit 1";


        MealCount m = jdbcTemplate.query(sql, new ResultSetExtractor<MealCount>() {

            @Override
            public MealCount extractData(ResultSet rs) throws SQLException, DataAccessException {
                //final String rsUserName = userName;
                if (rs.next()) {
                    MealCount m = new MealCount();
	            m.count = rs.getInt("low");
                    m.itemMin = rs.getString("type");
                    return m;
                }
                return null;
            }

        });
	//Java needs to implement a pair or allow multiple returns
        return m;
    }
    public Site getFoodPantry(String username) {
	String sql = "SELECT site.short_name AS name , site.site_id AS id from cs6400_sp17_team073.User as user  NATURAL JOIN cs6400_sp17_team073.Provide NATURAL JOIN cs6400_sp17_team073.Food_Pantry INNER JOIN cs6400_sp17_team073.Site as site on user.site_id = site.site_id where user.username=?";
//        String sql = "SELECT counts.count AS low, counts.type AS type FROM (SELECT 'vegetable' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum WHERE food_category_name = 'vegetables' UNION SELECT 'mineral' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum WHERE food_category_name = 'beans' OR food_category_name = 'nuts' OR food_category_name = 'grains' UNION SELECT 'animal' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item NATURAL JOIN cs6400_sp17_team073.Item_food_category_enum WHERE food_category_name = 'meat' OR food_category_name = 'seafood' OR food_category_name = 'dairy') AS counts order by counts.count asc limit 1";
//        String sql = "SELECT counts.count AS low, counts.type AS type FROM (SELECT 'vegetable' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'vegetables' UNION SELECT 'mineral' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'beans' OR food_category = 'nuts' OR food_category = 'grains' UNION SELECT 'animal' AS type, sum(number_of_units) AS count FROM cs6400_sp17_team073.Item WHERE food_category = 'meat' OR food_category = 'seafood' OR food_category = 'dairy') AS counts order by counts.count asc limit 1";


        Site s = jdbcTemplate.query(sql, new String[]{username}, new ResultSetExtractor<Site>() {

            @Override
            public Site extractData(ResultSet rs) throws SQLException, DataAccessException {
                //final String rsUserName = userName;
                if (rs.next()) {
                    Site s = new Site();
	            s.setShortName(rs.getString("name"));
                    s.setSiteId(rs.getInt("id"));
                    return s;
                }
                return null;
            }

        });
	//Java needs to implement a pair or allow multiple returns
        return s;
    }


}
