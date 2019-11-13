package caanap.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import caanap.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{

}
