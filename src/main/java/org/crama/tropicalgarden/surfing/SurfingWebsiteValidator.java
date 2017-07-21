package org.crama.tropicalgarden.surfing;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class SurfingWebsiteValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return SurfingWebsite.class.equals(clazz);
	
	}

	@Override
	public void validate(Object target, Errors e) {
		
		ValidationUtils.rejectIfEmpty(e, "siteName", "error.website.name.empty");
        ValidationUtils.rejectIfEmpty(e, "siteUrl", "error.website.url.empty");
        ValidationUtils.rejectIfEmpty(e, "viewTime", "error.website.viewTime.empty");
        ValidationUtils.rejectIfEmpty(e, "pointsForView", "error.website.pointsForView.empty");
        
        SurfingWebsite website = (SurfingWebsite) target;
		
        UrlValidator urlValidator = new UrlValidator();
        if (!urlValidator.isValid(website.getSiteUrl())) {
        	e.rejectValue("siteUrl", "error.website.url.invalid");
        }
        
        int[] possibleViewTime = new int[] {20, 40, 60};
        if (!ArrayUtils.contains(possibleViewTime, website.getViewTime())) {
        	e.rejectValue("viewTime", "error.website.viewTime.invalid");
        }
        
        
        
	}

}
