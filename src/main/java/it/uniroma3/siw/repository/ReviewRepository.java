package it.uniroma3.siw.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.model.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{
	//public List<Movie> findByYear(int year);
	//public boolean existsByTitleAndYear(String title, int year);
	
	@Query(value="select * "
			+ "from review "
			+ "where movie_id = :movieId "
			+ "order by rating DESC ", nativeQuery=true)
	public List<Review> orderReviewsByRating(@Param("movieId") Long id);
}
