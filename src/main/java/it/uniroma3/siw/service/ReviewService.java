package it.uniroma3.siw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.ReviewRepository;
import it.uniroma3.siw.repository.MovieRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieRepository movieRepository;

	@Transactional
	public Review createNewReview(Review review) {
		return this.reviewRepository.save(review);
	}
	
	public Review findReviewById(Long id) {
		return this.reviewRepository.findById(id).get();
	}
	
	public Review saveReviewToUser(Long userId, Long reviewId) {
		Review review = this.findReviewById(reviewId);
		User user = this.userService.getUser(userId);
		List<Review> reviews = user.getReviews();
		reviews.add(review);
		user.setReviews(reviews);
		review.setUser(user);
		return this.reviewRepository.save(review);
	}
	
	@Transactional
	public void deleteReview(Long reviewId) {
		Review review = this.findReviewById(reviewId);
		Movie movie = review.getMovie();
		review.getUser().getReviews().remove(review);
		review.getMovie().getReviews().remove(review);  
		this.movieRepository.save(movie);
		this.reviewRepository.delete(review);
	}
}
