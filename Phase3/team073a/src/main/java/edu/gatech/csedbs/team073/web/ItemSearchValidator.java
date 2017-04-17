package edu.gatech.csedbs.team073.web;

import edu.gatech.csedbs.team073.model.SearchItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Phil on 4/17/2017.
 */
public class ItemSearchValidator implements Validator {

    @Override
    public boolean supports(Class clazz) {

        return SearchItem.class.isAssignableFrom(clazz);
    }


    @Override
    public void validate(Object target, Errors errors) {
        int counter=0;
        SearchItem searchItem = (SearchItem) target;


       /*
        if (StringUtils.isBlank(searchItem.getSearchParms()) &&
                StringUtils.isBlank(searchItem.getSearchItemType())
                && StringUtils.isBlank(searchItem.getSearchFoodCategory())
                && StringUtils.isBlank(searchItem.getSearchSupplyCategory())
                && StringUtils.isBlank(searchItem.getSearchExpired())
                && StringUtils.isBlank(searchItem.getSearchLocationId())

                ) {
            errors.rejectValue("searchParms", "error.searchclient.onefieldrequired", "Both search fields cannot be blank");
            //  		errors.rejectValue("searchDescription", "error.searchclient.onefieldrequired", "Both search fields cannot be blank");
        }
        */

        if (StringUtils.isNotBlank(searchItem.getSearchParms() ) ) {
            counter++;
        }

        if (StringUtils.isNotBlank(searchItem.getSearchItemType()) ) {
            counter++;
        }

        if (StringUtils.isNotBlank(searchItem.getSearchFoodCategory()) ) {
            counter++;
        }

        if (StringUtils.isNotBlank(searchItem.getSearchSupplyCategory() ) ) {
            counter++;
        }

        if (StringUtils.isNotBlank(searchItem.getSearchExpired() ) ) {
            counter++;
        }
        if (StringUtils.isNotBlank(searchItem.getSearchLocationId() ) ) {
            counter++;
        }

        if (counter > 1) {
            errors.rejectValue("searchParms", "error.searchclient.toomanyfields", "Both search fields cannot be filled");
            //  		errors.rejectValue("searchDescription", "error.searchclient.onefieldrequired", "Both search fields cannot be blank");
        }

    }
}
