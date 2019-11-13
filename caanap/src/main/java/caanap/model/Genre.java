package caanap.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "genre")
@EntityListeners(AuditingEntityListener.class)
public class Genre {
	
	@Id
	@Column(name = "genre_id")
	private long genre_id;
	
	@Column(name = "genre_name")
	private String genre_name;
	
	@ManyToOne(targetEntity = Movie.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "movie_id",insertable=false, updatable=false)
	private List<Movie> movie;
	
	@Column(name = "genre_created_at")
	@CreatedDate
	private Date genre_created_at;
	
	@Column(name = "genre_updated_at")
	@LastModifiedDate
	private Date genre_updated_at;
	
	public long getGenre_id() {
		return genre_id;
	}

	public void setGenre_id(long genre_id) {
		this.genre_id = genre_id;
	}

	public String getGenreName() {
		return genre_name;
	}

	public void setGenreName(String genre_name) {
		this.genre_name = genre_name;
	}

	public Date getGenre_created_at() {
		return genre_created_at;
	}

	public void setGenre_created_at(Date genre_created_at) {
		this.genre_created_at = genre_created_at;
	}

	public Date getGenre_updated_at() {
		return genre_updated_at;
	}

	public void setGenre_updated_at(Date genre_updated_at) {
		this.genre_updated_at = genre_updated_at;
	}
}
