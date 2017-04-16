package edu.gatech.csedbs.team073.dao;

import edu.gatech.csedbs.team073.model.*;

import java.util.List;

/**
 * Created by swengineer on 4/6/17.
 */
public interface SiteInfoDAO {

    public SiteInfo getSiteInfo(int id);

    public List<SiteInfo> getAllSiteInfo();

}
