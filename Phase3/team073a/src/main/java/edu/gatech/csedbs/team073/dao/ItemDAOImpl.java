package edu.gatech.csedbs.team073.dao;

/**
 * Created by Taylor on 4/6/2017.
 */

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.*;
import edu.gatech.csedbs.team073.model.MealCount;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import edu.gatech.csedbs.team073.model.Site;
import edu.gatech.csedbs.team073.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.*;

public class ItemDAOImpl implements ItemDAO {

    private static final Logger logger = LoggerFactory.getLogger(SiteDAOImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbc;

    public ItemDAOImpl(DataSource dataSource) {
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
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
        /* (non-Javadoc)
         * @see edu.gatech.csedbs.team073.dao.ClientDAO#addClient(Client inClient)
         */
        @Override
        public int addItem(final Item inItem) {
		final String sql = "INSERT INTO cs6400_sp17_team073.Item (item_name, number_of_units, storage_type,item_type,food_category,supply_category,expiration_date,food_bank_id) SELECT * FROM ((SELECT ?, ?, storage_type, item_type, food_category,supply_category,DATE_ADD(NOW(), INTERVAL ? DAY),food_bank_id FROM cs6400_sp17_team073.Item_storage_type_enum LEFT OUTER JOIN cs6400_sp17_team073.Item_type_enum on 1=1 LEFT OUTER JOIN cs6400_sp17_team073.Item_food_category_enum on 1=1 LEFT OUTER JOIN cs6400_sp17_team073.Item_supply_category_enum on 1=1 LEFT OUTER JOIN cs6400_sp17_team073.Site on 1=1 INNER JOIN cs6400_sp17_team073.Provide WHERE UPPER(Storage_type_name)=UPPER(?) AND UPPER(Item_type_name)=UPPER(?) AND UPPER(Food_category_name)=UPPER(?) AND UPPER(Supply_category_name)=UPPER(?) AND food_bank_id=? limit 1)) as tmp";
/*                final String sql = "INSERT INTO cs6400_sp17_team073.Item" +
                                 "(item_name, number_of_units, storage_type,item_type,food_category,supply_category,expiration_date,food_bank_id) "+
                                 "SELECT ?, ?, storage_type, item_type, food_category,supply_category,DATE_ADD(NOW(), INTERVAL ? DAY),food_bank_id FROM "+
                                 "cs6400_sp17_team073.Storage_type_enum OUTER JOIN cs6400_sp17_team073.Item_type_enum OUTER JOIN cs6400_sp17_team073.Food_category_enum OUTER JOIN cs6400_sp17_team073.Supply_category_enum OUTER JOIN cs6400_sp17_team073.Site INNER JOIN cs6400_sp17_team073.Provides WHERE UPPER(Storage_type_name)=UPPER(?) AND UPPER(Item_type_name)=UPPER(?) AND UPPER(Food_category_name)=UPPER(?) AND UPPER(Supply_category_name)=UPPER(?) AND UPPER(short_name)=UPPER(?) limit 1";
*/

                PreparedStatementCreator psc = new PreparedStatementCreator() {

                        @Override
                        public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                                ps.setString(1, inItem.itemName);
                                ps.setInt(2, inItem.numberOfUnits);
                                ps.setInt(3, Integer.parseInt(inItem.expirationDate));
                                ps.setString(4, inItem.storageType);
                                ps.setString(5, inItem.itemType);
                                ps.setString(6, inItem.foodCategory);
                                ps.setString(7, inItem.supplyCategory);
                                ps.setString(8, inItem.foodBank);
                                return ps;

                        }

                };
                GeneratedKeyHolder holder = new GeneratedKeyHolder();
                jdbcTemplate.update(psc, holder);

                return 1;
        }



    @Override
    public List<Item> searchItemByName(String name) {
        //String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";

        String inName = name;

        if (StringUtils.isNotBlank(name)) {
            inName = name + "%";
        }

        String	sql = "SELECT * FROM cs6400_sp17_team073.Item WHERE item_name LIKE ?";
        String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Item WHERE item_name LIKE ?";
        int count = jdbcTemplate.queryForObject(countSql, new String[]{inName}, Integer.class);

        if (count > 300) {
            throw new RuntimeException(" Count is > 300 for search. Please modify query to return fewer results");
        }




        List<Item> items = jdbcTemplate.query(sql, new String[]{inName}, new ResultSetExtractor<List<Item>>() {

            @Override
            public List< Item > extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Item> rsItems = new ArrayList<Item>();
                Item rsItem = null;

                while (rs.next()) {
                    rsItem = new Item();

                    rsItem.setItemName(rs.getString("item_name"));
                    rsItem.setNumberOfUnits(rs.getInt("number_of_units"));
                    rsItem.setStorageType(rs.getString("storage_type"));
                    rsItem.setItemType(rs.getString("item_type"));

                    rsItem.setFoodCategory(rs.getString("food_category"));
                    rsItem.setSupplyCategory(rs.getString("supply_category"));
                    rsItem.setExpirationDate(rs.getString("expiration_date"));
                    rsItem.setFoodBank(rs.getString("food_bank_id"));


                    rsItems.add(rsItem);


                }

                if (rsItems == null) {
                    rsItems = Collections.emptyList();
                }

                return rsItems;
            }

        });



        return items;
    }




    @Override
    public List<Item> searchItemByItemType(String itype) {
        //String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";

        String inName = itype;

        if (StringUtils.isNotBlank(itype)) {
            inName = itype + "%";
        }

        String	sql = "SELECT * FROM cs6400_sp17_team073.Item WHERE item_type LIKE ?";
        String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Item WHERE item_type LIKE ?";
        int count = jdbcTemplate.queryForObject(countSql, new String[]{inName}, Integer.class);

        if (count > 300) {
            throw new RuntimeException(" Count is > 300 for search. Please modify query to return fewer results");
        }




        List<Item> items = jdbcTemplate.query(sql, new String[]{inName}, new ResultSetExtractor<List<Item>>() {

            @Override
            public List< Item > extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Item> rsItems = new ArrayList<Item>();
                Item rsItem = null;

                while (rs.next()) {
                    rsItem = new Item();

                    rsItem.setItemName(rs.getString("item_name"));
                    rsItem.setNumberOfUnits(rs.getInt("number_of_units"));
                    rsItem.setStorageType(rs.getString("storage_type"));
                    rsItem.setItemType(rs.getString("item_type"));

                    rsItem.setFoodCategory(rs.getString("food_category"));
                    rsItem.setSupplyCategory(rs.getString("supply_category"));
                    rsItem.setExpirationDate(rs.getString("expiration_date"));
                    rsItem.setFoodBank(rs.getString("food_bank_id"));


                    rsItems.add(rsItem);


                }

                if (rsItems == null) {
                    rsItems = Collections.emptyList();
                }

                return rsItems;
            }

        });



        return items;
    }





    @Override
    public List<Item> searchItemByStorageType(String sttype) {
        //String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";

        String inName = sttype;

        if (StringUtils.isNotBlank(sttype)) {
            inName = sttype + "%";
        }

        String	sql = "SELECT * FROM cs6400_sp17_team073.Item WHERE storage_type LIKE ?";
        String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Item WHERE storage_type LIKE ?";
        int count = jdbcTemplate.queryForObject(countSql, new String[]{inName}, Integer.class);

        if (count > 300) {
            throw new RuntimeException(" Count is > 300 for search. Please modify query to return fewer results");
        }




        List<Item> items = jdbcTemplate.query(sql, new String[]{inName}, new ResultSetExtractor<List<Item>>() {

            @Override
            public List< Item > extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Item> rsItems = new ArrayList<Item>();
                Item rsItem = null;

                while (rs.next()) {
                    rsItem = new Item();

                    rsItem.setItemName(rs.getString("item_name"));
                    rsItem.setNumberOfUnits(rs.getInt("number_of_units"));
                    rsItem.setStorageType(rs.getString("storage_type"));
                    rsItem.setItemType(rs.getString("item_type"));

                    rsItem.setFoodCategory(rs.getString("food_category"));
                    rsItem.setSupplyCategory(rs.getString("supply_category"));
                    rsItem.setExpirationDate(rs.getString("expiration_date"));
                    rsItem.setFoodBank(rs.getString("food_bank_id"));


                    rsItems.add(rsItem);


                }

                if (rsItems == null) {
                    rsItems = Collections.emptyList();
                }

                return rsItems;
            }

        });



        return items;
    }




    @Override
    public List<Item> searchItemByFoodCategory(String fcat) {
        //String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";

        String inName = fcat;

        if (StringUtils.isNotBlank(fcat)) {
            inName = fcat + "%";
        }

        String	sql = "SELECT * FROM cs6400_sp17_team073.Item WHERE food_category LIKE ?";
        String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Item WHERE food_category LIKE ?";
        int count = jdbcTemplate.queryForObject(countSql, new String[]{inName}, Integer.class);

        if (count > 300) {
            throw new RuntimeException(" Count is > 300 for search. Please modify query to return fewer results");
        }




        List<Item> items = jdbcTemplate.query(sql, new String[]{inName}, new ResultSetExtractor<List<Item>>() {

            @Override
            public List< Item > extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Item> rsItems = new ArrayList<Item>();
                Item rsItem = null;

                while (rs.next()) {
                    rsItem = new Item();

                    rsItem.setItemName(rs.getString("item_name"));
                    rsItem.setNumberOfUnits(rs.getInt("number_of_units"));
                    rsItem.setStorageType(rs.getString("storage_type"));
                    rsItem.setItemType(rs.getString("item_type"));

                    rsItem.setFoodCategory(rs.getString("food_category"));
                    rsItem.setSupplyCategory(rs.getString("supply_category"));
                    rsItem.setExpirationDate(rs.getString("expiration_date"));
                    rsItem.setFoodBank(rs.getString("food_bank_id"));


                    rsItems.add(rsItem);


                }

                if (rsItems == null) {
                    rsItems = Collections.emptyList();
                }

                return rsItems;
            }

        });



        return items;
    }




    @Override
    public List<Item> searchItemBySupplyCategory(String scat) {
        //String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";

        String inName = scat;

        if (StringUtils.isNotBlank(scat)) {
            inName = scat + "%";
        }

        String	sql = "SELECT * FROM cs6400_sp17_team073.Item WHERE supply_category LIKE ?";
        String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Item WHERE supply_category LIKE ?";
        int count = jdbcTemplate.queryForObject(countSql, new String[]{inName}, Integer.class);

        if (count > 300) {
            throw new RuntimeException(" Count is > 300 for search. Please modify query to return fewer results");
        }




        List<Item> items = jdbcTemplate.query(sql, new String[]{inName}, new ResultSetExtractor<List<Item>>() {

            @Override
            public List< Item > extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Item> rsItems = new ArrayList<Item>();
                Item rsItem = null;

                while (rs.next()) {
                    rsItem = new Item();

                    rsItem.setItemName(rs.getString("item_name"));
                    rsItem.setNumberOfUnits(rs.getInt("number_of_units"));
                    rsItem.setStorageType(rs.getString("storage_type"));
                    rsItem.setItemType(rs.getString("item_type"));

                    rsItem.setFoodCategory(rs.getString("food_category"));
                    rsItem.setSupplyCategory(rs.getString("supply_category"));
                    rsItem.setExpirationDate(rs.getString("expiration_date"));
                    rsItem.setFoodBank(rs.getString("food_bank_id"));


                    rsItems.add(rsItem);


                }

                if (rsItems == null) {
                    rsItems = Collections.emptyList();
                }

                return rsItems;
            }

        });



        return items;
    }





    @Override
    public List<Item> searchItemByExpired(String exp) {
        //String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";

        String inName = exp;

        if (StringUtils.isNotBlank(exp)) {
            inName = exp + "%";
        }

        String	sql = "SELECT * FROM cs6400_sp17_team073.Item WHERE expiration_date LIKE ?";
        String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Item WHERE expiration_date LIKE ?";
        int count = jdbcTemplate.queryForObject(countSql, new String[]{inName}, Integer.class);

        if (count > 300) {
            throw new RuntimeException(" Count is > 300 for search. Please modify query to return fewer results");
        }




        List<Item> items = jdbcTemplate.query(sql, new String[]{inName}, new ResultSetExtractor<List<Item>>() {

            @Override
            public List< Item > extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Item> rsItems = new ArrayList<Item>();
                Item rsItem = null;

                while (rs.next()) {
                    rsItem = new Item();

                    rsItem.setItemName(rs.getString("item_name"));
                    rsItem.setNumberOfUnits(rs.getInt("number_of_units"));
                    rsItem.setStorageType(rs.getString("storage_type"));
                    rsItem.setItemType(rs.getString("item_type"));

                    rsItem.setFoodCategory(rs.getString("food_category"));
                    rsItem.setSupplyCategory(rs.getString("supply_category"));
                    rsItem.setExpirationDate(rs.getString("expiration_date"));
                    rsItem.setFoodBank(rs.getString("food_bank_id"));


                    rsItems.add(rsItem);


                }

                if (rsItems == null) {
                    rsItems = Collections.emptyList();
                }

                return rsItems;
            }

        });



        return items;
    }




    @Override
    public List<Item> searchItemByLocationId(int locid) {
        //String sql = "SELECT * FROM cs6400_sp17_team073.Client WHERE full_name = ?";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("food_bank_id", locid);
      //  String inName = exp;

     ///   if (StringUtils.isNotBlank(exp)) {
      //      inName = exp + "%";
       // }

        String	sql = "SELECT * FROM cs6400_sp17_team073.Item WHERE Food_Bank.food_bank_id=:food_bank_id";
       // String countSql = "SELECT count(*) FROM cs6400_sp17_team073.Item WHERE food_bank_id LIKE ?";
       // int count = jdbcTemplate.queryForObject(countSql, new String[]{inName}, Integer.class);

      //  if (count > 300) {
      //      throw new RuntimeException(" Count is > 300 for search. Please modify query to return fewer results");
      //  }




        List<Item> items = jdbc.query(sql, params, new ResultSetExtractor<List<Item>>() {

            @Override
            public List< Item > extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Item> rsItems = new ArrayList<Item>();
                Item rsItem = null;

                while (rs.next()) {
                    rsItem = new Item();

                    rsItem.setItemName(rs.getString("item_name"));
                    rsItem.setNumberOfUnits(rs.getInt("number_of_units"));
                    rsItem.setStorageType(rs.getString("storage_type"));
                    rsItem.setItemType(rs.getString("item_type"));

                    rsItem.setFoodCategory(rs.getString("food_category"));
                    rsItem.setSupplyCategory(rs.getString("supply_category"));
                    rsItem.setExpirationDate(rs.getString("expiration_date"));
                    rsItem.setFoodBank(rs.getString("food_bank_id"));


                    rsItems.add(rsItem);


                }

                if (rsItems == null) {
                    rsItems = Collections.emptyList();
                }

                return rsItems;
            }

        });



        return items;
    }

}
