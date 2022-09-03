package io.javabrains.movieinfoservice.resources;

import io.javabrains.movieinfoservice.models.Movie;
import io.javabrains.movieinfoservice.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")
public class MovieResource {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    //https://api.themoviedb.org/3/movie/550?api_key=bafb4f7e0beaea25675d939fa6c143b3
    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId){

        MovieSummary movieSummary = restTemplate.getForObject(
          "https://api.themoviedb.org/3/movie/"+ movieId +"?api_key="+apiKey,MovieSummary.class);

        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());

    }
}
