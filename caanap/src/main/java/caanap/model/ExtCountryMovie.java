package caanap.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ext_country_movie")
@EntityListeners(AuditingEntityListener.class)
public class ExtCountryMovie {
	
	@Id
	@GeneratedValue
	@Column(name = "ext_country_movie_id")
	private long ext_country_movie_id;
	
	@Column(name = "ext_country_movie_pk_country")
	private Integer ext_country_movie_pk_country;
	
	@Column(name = "ext_country_movie_pk_movie")
	private Integer ext_country_movie_pk_movie;
	
	public long getExt_country_movie_id() {
		return ext_country_movie_id;
	}

	public void setExt_country_movie_id(long ext_country_movie_id) {
		this.ext_country_movie_id = ext_country_movie_id;
	}

	public Integer getExt_country_movie_pk_country() {
		return ext_country_movie_pk_country;
	}

	public void setExt_country_movie_pk_country(Integer ext_country_movie_pk_country) {
		this.ext_country_movie_pk_country = ext_country_movie_pk_country;
	}

	public Integer getExt_country_movie_pk_movie() {
		return ext_country_movie_pk_movie;
	}

	public void setExt_country_movie_pk_movie(Integer ext_country_movie_pk_movie) {
		this.ext_country_movie_pk_movie = ext_country_movie_pk_movie;
	}
	
}
