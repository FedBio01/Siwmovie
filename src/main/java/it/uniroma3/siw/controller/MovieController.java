package it.uniroma3.siw.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import it.uniroma3.siw.controller.validator.MovieValidator;
import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Credentials;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.model.Review;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.CredentialsService;
import it.uniroma3.siw.service.MovieService;
import it.uniroma3.siw.utils.FileUploadUtil;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MovieController { 
	@Autowired
	private MovieService movieService;

	@Autowired
	private ArtistService artistService;

	@Autowired
	private MovieValidator movieValidator;

	@Autowired
	private CredentialsService credentialsService;

	@GetMapping(value="/admin/formNewMovie")
	public String formNewMovie(Model model) {
		model.addAttribute("movie", new Movie());
		return "admin/formNewMovie.html";
	}

	@GetMapping(value="/admin/formUpdateMovie/{id}")
	public String formUpdateMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("movie", this.movieService.findMovieById(id));
		return "admin/formUpdateMovie.html";
	}

	@GetMapping(value="/admin/indexMovie")
	public String indexMovie() {
		return "admin/indexMovie.html";
	}
	@GetMapping(value="/admin/manageMovies")
	public String manageMovies(Model model) {
		model.addAttribute("movies", this.movieService.findAllMovies());
		return "admin/manageMovies.html";
	}

	@GetMapping(value="/admin/setDirectorToMovie/{directorId}/{movieId}")
	public String setDirectorToMovie(@PathVariable("directorId") Long directorId, @PathVariable("movieId") Long movieId, Model model) {
		Movie movie = this.movieService.saveDirectorToMovie(movieId, directorId);
		model.addAttribute("movie", movie);
		return "admin/formUpdateMovie.html";
	}

	@GetMapping(value="/admin/addDirector/{id}")
	public String addDirector(@PathVariable("id") Long id, Model model) {
		model.addAttribute("artists", artistService.findAllArtist());
		model.addAttribute("movie", movieService.findMovieById(id));

		return "admin/directorsToAdd.html";
	}


	@PostMapping("/admin/movie")
	public String newMovie(@Valid @ModelAttribute("movie") Movie movie, BindingResult bindingResult, Model model,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		this.movieValidator.validate(movie, bindingResult);
		if (!bindingResult.hasErrors()) {
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			movie.addPhotos(fileName);
			this.movieService.createNewMovie(movie);
			model.addAttribute("movie", movie);
			String uploadDir = "movie-photos/" + movie.getId();
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			return "movie.html";
		} else {
			return "admin/formNewMovie.html";
		}
	}
	@GetMapping("/movie/{id}")
	public String getMovie(@PathVariable("id") Long id, Model model) {
		model.addAttribute("movie", this.movieService.findMovieById(id));
		return "movie.html";
	}

	@PostMapping("/admin/addImage/{id}")
	public String addImage(@PathVariable("id") Long id, Model model,
			@RequestParam("image") MultipartFile multipartFile) throws IOException {
		Movie movie = this.movieService.findMovieById(id);
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		movie.addPhotos(fileName);
		this.movieService.saveMovie(movie);
		String uploadDir = "movie-photos/" + movie.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		model.addAttribute("movie", movie);
		return "admin/formUpdateMovie.html";
	}
	
	@GetMapping(value="/admin/removeActorFromMovie/{actorId}/{movieId}")
	public String removeActorFromMovie(@PathVariable("actorId") Long actorId, @PathVariable("movieId") Long movieId, Model model) {
	
		Movie movie = movieService.deleteActorFromMovie(movieId, actorId);
		List<Artist> actorsToAdd = this.movieService.findActorsNotInMovie(movieId);
	
		model.addAttribute("movie", movie);
		model.addAttribute("actorsToAdd", actorsToAdd);

		return "admin/actorsToAdd.html";
	}

	@GetMapping("/movie")
	public String getMovies(Model model) {
		model.addAttribute("movies", this.movieService.findAllMovies());
		return "movies.html";
	}
	@GetMapping("/formSearchMovies")
	public String formSearchMovies() {
		return "formSearchMovies.html";
	}
	@PostMapping("/searchMovies")
	public String searchMovies(Model model, @RequestParam int year) {
		model.addAttribute("movies", this.movieService.findByYear(year));
		return "foundMovies.html";
	}
	@GetMapping("/admin/updateActors/{id}")
	public String updateActors(@PathVariable("id") Long id, Model model) {
		List<Artist> actorsToAdd = this.movieService.findActorsNotInMovie(id);
		model.addAttribute("actorsToAdd", actorsToAdd);
		model.addAttribute("movie", this.movieService.findMovieById(id));
		return "admin/actorsToAdd.html";
	}
	@GetMapping(value="/admin/addActorToMovie/{actorId}/{movieId}")
	public String addActorToMovie(@PathVariable("actorId") Long actorId, @PathVariable("movieId") Long movieId, Model model) {
		Movie movie = this.movieService.saveActorToMovie(movieId,actorId);
		List<Artist> actorsToAdd = this.movieService.findActorsNotInMovie(movieId);
		model.addAttribute("movie", movie);
		model.addAttribute("actorsToAdd", actorsToAdd);
		return "admin/actorsToAdd.html";
	}
	
	

	@GetMapping(value="/registered/movieRegistered")
	public String moviesRegistered(Model model) {
		UserDetails user = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credentials credentials = credentialsService.getCredentials(user.getUsername());
		model.addAttribute("user", credentials.getUser());
		model.addAttribute("movies", this.movieService.findAllMovies());
		return "registered/moviesRegistered.html";
	}

	@GetMapping("/registered/addReview/{id}")
	public String addReview(@PathVariable("id") Long id, Model model) {
		Movie movie = this.movieService.findMovieById(id);
		model.addAttribute("movie", movie);
		model.addAttribute("review", new Review());
		return "registered/formNewReview.html";
	}
	
	@GetMapping("/movie/reviews/{id}")
	public String getReviews(@PathVariable("id") Long id, Model model) {
		Movie movie = this.movieService.findMovieById(id);
		List<Review> reviews = movie.getReviews();
		model.addAttribute("reviews", reviews);
		model.addAttribute("movie", movie);
		return "reviews.html";
	}
	
	@GetMapping("/admin/deleteMovie/{id}")
	public String deleteMovie(@PathVariable("id") Long id, Model model) {
		this.movieService.deleteMovie(id);
		model.addAttribute("movies", this.movieService.findAllMovies());
		return "admin/manageMovies.html";
	}
	
	@GetMapping("/admin/movie")
	public String adminMovies(Model model) {
		model.addAttribute("movies", this.movieService.findAllMovies());
		return "admin/moviesAdmin.html";
	}
	
	@GetMapping("/admin/movie/reviews/{id}")
	public String adminGetReviews(@PathVariable("id") Long movieId,Model model) {
		Movie movie = this.movieService.findMovieById(movieId);
		List<Review> reviews = movie.getReviews();
		model.addAttribute("reviews", reviews);
		model.addAttribute("movie", movie);
		return "admin/manageReviews.html";
	}
	
	@PostMapping("/admin/updateMovie/{id}")
	public String updateMovie(@ModelAttribute("movie") Movie newMovie, @PathVariable("id") Long id, Model model) {
		Movie oldMovie = this.movieService.findMovieById(id);
		Movie movie = this.movieService.updateMovie(oldMovie, newMovie);
		this.movieService.saveMovie(movie);
		model.addAttribute("movie", movie);
		return "admin/formUpdateMovie.html";
	}

}
