package edu.gatech.csedbs.team073.dao;

/**
 * Created by Phil on 4/6/2017.
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.gatech.csedbs.team073.model.Site;
import edu.gatech.csedbs.team073.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class SiteDAOImpl implements SiteDAO {

    private static final Logger logger = LoggerFactory.getLogger(SiteDAOImpl.class);

    private JdbcTemplate jdbcTemplate;

    public SiteDAOImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    public Site getSite(Integer siteId) {
        String sql = "SELECT * FROM cs6400_sp17_team073.Site WHERE site_id = ?";


        Site site = jdbcTemplate.query(sql, new Integer[]{siteId}, new ResultSetExtractor<Site>() {

            @Override
            public Site extractData(ResultSet rs) throws SQLException, DataAccessException {
                Site rsSite = null;
                //final String rsUserName = userName;
                while (rs.next()) {
                    rsSite = new Site();

                    rsSite.setSiteId(rs.getInt("site_id"));
                    rsSite.setShortName(rs.getString("short_name"));
                    rsSite.setStreetAddress(rs.getString("street_address"));
                    rsSite.setCity(rs.getString("city"));
                    rsSite.setState(rs.getString("state"));
                    rsSite.setZipcode(rs.getInt("zip"));
                    rsSite.setContactNumber(rs.getString("contact_number"));
                }
                return rsSite;
            }

        });


        return site;
    }


}
