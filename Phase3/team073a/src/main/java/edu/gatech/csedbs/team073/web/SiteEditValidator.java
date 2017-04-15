package edu.gatech.csedbs.team073.web;
import edu.gatech.csedbs.team073.model.Site;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 * Created by Phil on 4/14/2017.
 */
public class SiteEditValidator implements Validator {


    @Override
    public boolean supports(Class temp) {

        return Site.class.isAssignableFrom(temp);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortName",
                "required.shortName", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "descriptionString",
                "required.descriptionString", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "conditionsForUse",
                "required.conditionsForUse", "Field name is required.");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "hours",
                "required.hours", "Field name is required.");

    }

}
