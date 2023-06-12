package it.uniroma3.siw.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.controller.validator.ReviewValidator;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.repository.ReviewRepository;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.service.ReviewService;


@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private ReviewValidator reviewValidator;

	@Autowired
	private CredentialsService credentialsService;
	@Autowired
	private ReviewRepository reviewRepository;

	@PostMapping("/registered/review/{movie_id}")
	public String newReview(@Valid @ModelAttribute("review") Review review, Model model, @PathVariable("movie_id") Long movieId, BindingResult bindingResult) {
		Movie movie = this.movieService.findMovieById(movieId);
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(user.getUsername());
		review.setMovie(movie);
		this.reviewValidator.validate(review, bindingResult);
		if (!bindingResult.hasErrors()) {
			Review createdReview=this.reviewService.createNewReview(review);
			Movie savedMovie = this.movieService.saveReviewToMovie(movie.getId(), createdReview.getId());
			this.reviewService.saveReviewToUser(credentials.getUser().getId(), review.getId());
			model.addAttribute(savedMovie);
			model.addAttribute(createdReview);
			return "registered/reviewSuccessful.html";
		} else {
			model.addAttribute("movie", movie);
			return "registered/formNewReview.html";
		}
	}
	
	@GetMapping("/admin/deleteReview/{review_id}/{movie_id}")
	public String deleteReview(@PathVariable("review_id") Long reviewId,@PathVariable("movie_id") Long movieId, Model model) {
		this.reviewService.deleteReview(reviewId);
		Movie movie = this.movieService.findMovieById(movieId);
		model.addAttribute("movie", movie );
		model.addAttribute("reviews", movie.getReviews());
		return "admin/manageReviews.html";
	}
	
	@GetMapping("/reviews/reviewsOrdered/{id}")
	public String orderReviews(@PathVariable("id") Long id, Model model) {
		List<Review> reviews = this.reviewRepository.orderReviewsByRating(id);
		Movie movie = this.movieService.findMovieById(id);
		model.addAttribute("movie", movie);
		model.addAttribute("reviews", reviews);
		return "reviews.html";
	}
	
}
