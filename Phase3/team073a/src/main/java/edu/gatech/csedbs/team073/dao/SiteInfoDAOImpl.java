package edu.gatech.csedbs.team073.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import edu.gatech.csedbs.team073.model.*;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by swengineer on 4/6/17.
 */

@Component("SiteInfoDAO")
public class SiteInfoDAOImpl implements SiteInfoDAO {


    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public SiteInfo getSiteInfo(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from Site where site_id=:id", params,
                new RowMapper<SiteInfo>() {

                    public SiteInfo mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        SiteInfo siteInfo = new SiteInfo();

                        siteInfo.setCity(rs.getString("site_id"));
                        siteInfo.setContactNumber(rs.getString("contact_number"));
                        siteInfo.setFullName(rs.getString("full_name"));
                        siteInfo.setShortName(rs.getString("short_name"));
                        siteInfo.setState(rs.getString("state"));
                        siteInfo.setZip(rs.getInt("zip"));
                        siteInfo.setStreetAddress(rs.getString("street_address"));
                        siteInfo.setSiteId(rs.getInt("site_id"));

                        return siteInfo;
                    }

                });
    }




}
