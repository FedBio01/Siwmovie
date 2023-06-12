package it.uniroma3.siw.service;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Movie;
import it.uniroma3.siw.repository.ArtistRepository;

@Service
public class ArtistService {
	@Autowired
	ArtistRepository artistRepository;
	
	@Transactional
	public Iterable<Artist> findAllArtist() {
		return this.artistRepository.findAll();
	}
	
	public Artist findArtistById(Long id) {
		return this.artistRepository.findById(id).get();
	}
	
	public boolean existsByNameAndSurname(String name, String surname) {
		return this.artistRepository.existsByNameAndSurname(name, surname);
	}
	
	@Transactional
	public void saveArtist(Artist artist) {
		this.artistRepository.save(artist);
	}
	
	@Transactional
	public void deleteArtist(Long artistId) {
		Artist artist = this.findArtistById(artistId);
		Set<Movie> movies = artist.getStarredMovies();
		for(Movie movie : movies) {
			movie.getActors().remove(artist);
		}
		this.artistRepository.delete(artist);
	}
	
	@Transactional
	public Artist updateArtist(Artist oldArtist, Artist newArtist) {
		oldArtist.setName(newArtist.getName());
		oldArtist.setSurname(newArtist.getSurname());
		oldArtist.setDateOfBirth(newArtist.getDateOfBirth());
		oldArtist.setDateOfDeath(newArtist.getDateOfDeath());
		this.artistRepository.save(oldArtist);
		return oldArtist;
	}
	
	
}