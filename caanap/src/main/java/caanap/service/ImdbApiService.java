package caanap.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import caanap.model.Country;
import caanap.model.ExtCountryMovie;
import caanap.model.Genre;
import caanap.model.Movie;
import caanap.repository.CountryRepository;
import caanap.repository.ExtCountryMovieRepository;
import caanap.repository.GenreRepository;
import caanap.repository.MovieRepository;

@Component
public class ImdbApiService {
	
	private static final String apiMovieKey = "8200b8ad19d3461ad3751588359763ae";
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ExtCountryMovieRepository extCountryMovieRepository;
	
	public Movie getJsonImdb(Long imdbId) throws UnirestException, ParseException {
		
		JSONObject myObj = setHttpSent("fr-FR", imdbId);
		
		Movie movie = new Movie();
		constructMovie(movie, myObj);
		movie.setMovieCountry(setCountryMovie(imdbId, myObj, movie));
		movie.setMovieGenre(setGenreMovie(imdbId, myObj, movie));
		saveMovie(movie);
		
		return movie;
	}
	
	public JSONObject setHttpSent(String lang, Long imdbId) throws UnirestException {
		HttpResponse<JsonNode> jsonResponseEn
	      = Unirest.get("https://api.themoviedb.org/3/movie/"+imdbId)
	      .header("accept", "application/json")
	      .queryString("api_key", ImdbApiService.apiMovieKey)
	      .queryString("language", lang)
	      .asJson();
		System.out.println(jsonResponseEn.getBody().toString());
		JSONObject myObj = jsonResponseEn.getBody().getObject();
		return myObj;
	}
	
	public void constructMovie(Movie movie,JSONObject vfObj) throws ParseException {
		movie.setId(vfObj.getInt(("id")));
		movie.setMovie_full_name_fr(vfObj.getString(("title")));
		movie.setMovie_imdb_id(vfObj.getString("imdb_id"));
		movie.setMovie_poster_url("http://image.tmdb.org/t/p/w185/"+vfObj.getString("poster_path"));
		movie.setMovie_Description(vfObj.getString(("overview")));
		String release_date_string = vfObj.getString("release_date");
		Date release_date=new SimpleDateFormat("yyyy-MM-dd").parse(release_date_string);  
		movie.setMovie_release_date(release_date);
		movie.setMovieGenre(new HashSet<Genre>());
		movie.setMovieCountry(new HashSet<Country>());
		movie.setMovie_updated_at(new Date());
		movie.setMovie_created_at(new Date());
	}
		
	
	public Set<Genre> setGenreMovie(Long imdb, JSONObject myObj, Movie movie) {
		Set<Genre> hashGenre = new HashSet<Genre>();
		for (int i = 0; i < myObj.getJSONArray("genres").length(); i++) {
			Long genreId = myObj.getJSONArray("genres").getJSONObject(i).getLong("id");
			if(!genreRepository.findById(genreId).isPresent()) {
				Genre genreObj = new Genre();
				genreObj.setGenre_id(myObj.getJSONArray("genres").getJSONObject(i).getLong("id"));
				genreObj.setGenreName(myObj.getJSONArray("genres").getJSONObject(i).getString("name"));
				genreObj.setGenre_created_at(new Date());
				genreObj.setGenre_updated_at(new Date());
				genreRepository.save(genreObj);
			}
			Genre genre = new Genre();
			genre = genreRepository.findById(genreId).orElse(null);
			movie.getMovieGenre().add(genre);
			genreRepository.save(genre);
			hashGenre.add(genre);
		}
		return hashGenre;
	}
	
	public Set<Country> setCountryMovie(Long imdb, JSONObject myObj, Movie movie) {
		Set<Country> hashCountry = new HashSet<Country>();
		for (int i = 0; i < myObj.getJSONArray("production_countries").length(); i++) {
			String countryName = myObj.getJSONArray("production_countries").getJSONObject(i).getString("name");
			if(countryRepository.findByCountryName(countryName).isEmpty()) {
				Country country = new Country();
				country.setCountryName(myObj.getJSONArray("production_countries").getJSONObject(i).getString("name"));
				country.setCountry_created_at(new Date());
				country.setCountry_updated_at(new Date());
				countryRepository.save(country);
			}
			Country country = new Country();
			country = countryRepository.findByCountryName(countryName).get(0);
			movie.getCountry().add(country);
			countryRepository.save(country);
			hashCountry.add(country);
		}
		return hashCountry;
	}
	
	public void saveMovie (Movie movie) {
		movieRepository.save(movie);
	}
}
