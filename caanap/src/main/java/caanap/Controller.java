package caanap;

import org.springframework.web.bind.annotation.RestController;

import com.mashape.unirest.http.exceptions.UnirestException;

import caanap.model.Movie;
import caanap.repository.MovieRepository;
import caanap.service.ImdbApiService;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class Controller {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ImdbApiService imdbApi;
	
    //Créer un film    
    @RequestMapping("/movie/new/{id}")
    public ResponseEntity<Movie> newMovie(@PathVariable(value = "id") Long imdbId) throws UnirestException, ParseException {
    	if(movieRepository.findById(imdbId).isPresent()) {
    		return ResponseEntity.ok().body(null);
    	} else {
    		Movie movie = this.imdbApi.getJsonImdb(imdbId);
    		ResponseEntity.ok(movie);
    		return ResponseEntity.ok().body(movie);
    	}
    }
    

	//Lister tous les films
	@GetMapping("/movies")
	public List<Movie> getAllMovies(){
		System.out.println("Salut");
		return movieRepository.findAll();
	}
	
	//Trouve un film
	@GetMapping("/movie/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable(value = "id") Long movieId){
		if(movieRepository.findById(movieId).isPresent()) {
			Movie movie = movieRepository.findById(movieId).orElse(null);
			return ResponseEntity.ok().body(movie);
		} else {
			return null;
		}
	}
	
    @RequestMapping("/movie/{id}/rate/{rate}")
    public ResponseEntity<Movie> rateMovie(@PathVariable(value = "id") Long movieId,@PathVariable(value = "rate") int rate) {
    	if(movieRepository.findById(movieId).isPresent()) {
    		float rating = 0;
    		if (rate < 0 || rate > 10) {
    			rating = 0;
    		} else {
    			rating = rate;
    		}
    		Movie movie = movieRepository.findById(movieId).orElseGet(null);
    		movie.setMovie_rating(rating);
    		movie.setMovie_updated_at(new Date());
    		movie.setMovie_rating_date(new Date());
    		final Movie updateMovie = movieRepository.save(movie);
    		ResponseEntity.ok(updateMovie);
    		Movie movieFinal = movieRepository.findById(movieId).orElse(null);
    		return ResponseEntity.ok().body(movieFinal);
    	} else {
    		return ResponseEntity.ok().body(null);
    	}
    }
	
//	@PostMapping("/movie")
//	public Movie createMovie(@Valid @RequestBody Movie movie) {
//		return movieRepository.save(movie);
//	}
	
//	@PutMapping("/movie/update/{id}")
//	public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movieId, @Valid @RequestBody Movie movieDetails) {
//		Movie movie = movieRepository.findById(movieId).orElseGet(null);
//		movie.setMovie_full_name_en(movieDetails.getMovie_full_name_en());
//		movie.setMovie_full_name_fr(movieDetails.getMovie_full_name_fr());
//		movie.setMovie_imdb_id(movieDetails.getMovie_imdb_id());
//		movie.setMovie_rating(movieDetails.getMovie_rating());
//		movie.setMovie_release_date(movieDetails.getMovie_release_date());
//		movie.setMovie_rating_date(movieDetails.getMovie_rating_date());
//		movie.setMovie_poster_url(movieDetails.getMovie_poster_url());
//		movie.setMovie_updated_at(new Date());
//		final Movie updateMovie = movieRepository.save(movie);
//		return ResponseEntity.ok(updateMovie);
//	}
	

//	  @DeleteMapping("/movie/delete/{id}")
//	  public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long movieId) throws Exception {
//	    Movie movie =
//	        movieRepository
//	            .findById(movieId)
//	            .orElseGet(null);
//	    movieRepository.delete(movie);
//	    Map<String, Boolean> response = new HashMap<>();
//	    response.put("deleted", Boolean.TRUE);
//	    return response;
//	  }
    
}
