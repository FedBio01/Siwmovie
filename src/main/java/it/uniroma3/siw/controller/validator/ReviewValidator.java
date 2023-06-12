package it.uniroma3.siw.controller.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.CredentialsService;

@Component
public class ReviewValidator implements Validator {
	@Autowired
	private CredentialsService credentialsService;

	@Override
	public void validate(Object o, Errors errors) {
		Review review = (Review)o;
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(user.getUsername());
		List<Review> reviews = review.getMovie().getReviews();
		for (Review r: reviews) {
			if (r.getUser().getId() == credentials.getUser().getId()) {
				errors.reject("review.duplicate");
			}
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return false;
	}

}
