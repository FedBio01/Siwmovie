package it.uniroma3.siw.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	private String name;
	private String surname;
	
	@Column(nullable = true, length = 64)
    private String photo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfBirth;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateOfDeath;
	
	
	@ManyToMany(mappedBy="actors")
	private Set<Movie> starredMovies;
	
	@OneToMany(mappedBy="director")
	private List<Movie> directedMovies;
	
	public Artist(){
		this.starredMovies = new HashSet<>();
		this.directedMovies = new LinkedList<>();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	public Set<Movie> getActorOf() {
		return starredMovies;
	}

	public void setActorOf(Set<Movie> starredMovies) {
		this.starredMovies = starredMovies;
	}

	public List<Movie> getDirectorOf() {
		return directedMovies;
	}

	public void setDirectorOf(List<Movie> directedMovies) {
		this.directedMovies = directedMovies;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Artist other = (Artist) obj;
		return Objects.equals(name, other.name) && Objects.equals(surname, other.surname);
	}
	
	@Transient
    public String getPhotosImagePath() {
        if (photo == null || id == null) return null;
         
        return "/artist-photos/" + id + "/" + photo;
    }

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Set<Movie> getStarredMovies() {
		return starredMovies;
	}

	public void setStarredMovies(Set<Movie> starredMovies) {
		this.starredMovies = starredMovies;
	}

	public List<Movie> getDirectedMovies() {
		return directedMovies;
	}

	public void setDirectedMovies(List<Movie> directedMovies) {
		this.directedMovies = directedMovies;
	}

	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

}
