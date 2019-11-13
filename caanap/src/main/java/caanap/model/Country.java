package caanap.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "country")
@EntityListeners(AuditingEntityListener.class)

public class Country {
	
	@Id
	@GeneratedValue
	@Column(name = "country_id")
	private long country_id;
	
	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "country_created_at")
	@CreatedDate
	private Date country_created_at;
	
	@Column(name = "country_updated_at")
	@LastModifiedDate
	private Date country_updated_at;
	
	@ManyToOne(targetEntity = Movie.class, fetch=FetchType.EAGER)
	@JoinColumn(name = "movie_id",insertable=false, updatable=false)
	private List<Movie> movie;
	
	
	public long getCountry_id() {
		return country_id;
	}

	public void setCountry_id(long country_id) {
		this.country_id = country_id;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Date getCountry_created_at() {
		return country_created_at;
	}

	public void setCountry_created_at(Date country_created_at) {
		this.country_created_at = country_created_at;
	}

	public Date getCountry_updated_at() {
		return country_updated_at;
	}

	public void setCountry_updated_at(Date country_updated_at) {
		this.country_updated_at = country_updated_at;
	}




}
