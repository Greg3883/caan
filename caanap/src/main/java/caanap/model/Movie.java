package caanap.model;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "movie")
@EntityListeners(AuditingEntityListener.class)

public class Movie {
	
	@Id
	@Column(name = "movie_id")
	private long movie_id;
	
	@Column(name = "movie_full_name_fr")
	private String movie_full_name_fr;
	
	@Column(name = "movie_description")
	private String movie_description;
	
	@Column(name = "movie_imdb_id")
	private String movie_imdb_id;
	
	@Column(name = "movie_rating")
	private float movie_rating;
	
	@Column(name = "movie_release_date")
	private Date movie_release_date;
	
	@Column(name = "movie_rating_date")
	private Date movie_rating_date;
	
	@Column(name = "movie_poster_url")
	private String movie_poster_url;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "movie")
    private Set<Genre> genres;
	
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "movie")
    private Set<Country> country;

	@Column(name = "movie_created_at")
	@CreatedDate
	private Date movie_created_at;
	
	@Column(name = "movie_updated_at")
	@LastModifiedDate
	private Date movie_updated_at;
	
	public long getId() {
		return movie_id;
	}

	public void setId(long id) {
		this.movie_id = id;
	}

	
	public String getMovie_Description() {
		return movie_description;
	}

	public void setMovie_Description(String movie_description) {
		this.movie_description = movie_description;
	}

	public String getMovie_full_name_fr() {
		return movie_full_name_fr;
	}

	public void setMovie_full_name_fr(String movie_full_name_fr) {
		this.movie_full_name_fr = movie_full_name_fr;
	}

	public String getMovie_imdb_id() {
		return movie_imdb_id;
	}

	public void setMovie_imdb_id(String movie_imdb_id) {
		this.movie_imdb_id = movie_imdb_id;
	}

	public float getMovie_rating() {
		return movie_rating;
	}

	public void setMovie_rating(float movie_rating) {
		this.movie_rating = movie_rating;
	}

	public Date getMovie_release_date() {
		return movie_release_date;
	}

	public void setMovie_release_date(Date movie_release_date) {
		this.movie_release_date = movie_release_date;
	}

	public Date getMovie_rating_date() {
		return movie_rating_date;
	}

	public void setMovie_rating_date(Date movie_rating_date) {
		this.movie_rating_date = movie_rating_date;
	}

	public String getMovie_poster_url() {
		return movie_poster_url;
	}

	public void setMovie_poster_url(String movie_poster_url) {
		this.movie_poster_url = movie_poster_url;
	}

	public Date getMovie_created_at() {
		return movie_created_at;
	}

	public void setMovie_created_at(Date movie_created_at) {
		this.movie_created_at = movie_created_at;
	}

	public Date getMovie_updated_at() {
		return movie_updated_at;
	}

	public void setMovie_updated_at(Date movie_updated_at) {
		this.movie_updated_at = movie_updated_at;
	}
	
	public Set<Genre> getMovieGenre() {
		return genres;
	}

	public void setMovieGenre(Set<Genre> movieGenre) {
		this.genres = movieGenre;
	}
	

	public Set<Country> getCountry() {
		return country;
	}

	public void setMovieCountry(Set<Country> country) {
		this.country = country;
	}
		
}
