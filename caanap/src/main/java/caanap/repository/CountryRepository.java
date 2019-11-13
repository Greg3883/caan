package caanap.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import caanap.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{
	List<Country> findByCountryName(String country_name);
}
