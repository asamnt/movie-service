package io.javabrains.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import io.javabrains.moviecatalogservice.service.MovieInfo;
import io.javabrains.moviecatalogservice.service.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        //get me all the ratings for a user
        //user id is passed in the path varibale
        //call the ratings api with the user id parameter
        //it returns a userratings object => list of (movie id, rating)
        UserRating ratings = userRatingInfo.getUserRating(userId);

        //this is using java streams
        //we return a list of catalog items
        //(catalog items is the list of movies with their ratings, desc that the user has rated)
        return ratings.getRating()
                .stream()
                //map is essentially a function to be applied to the stream
                //so for each of the rating object, we call the movie api to get the movie details - name and desc
                .map(rating -> movieInfo.getCatalogItem(rating)).collect(Collectors.toList());//and then collect all of them into a list

    }



}

//The below is for async requests
//            Movie movie = webClientBuilder.build()
//                    .get()
//                    .uri("http://localhost:8082/movies/"+ rating.getMovieid())
//                    .retrieve()
//                    .bodyToMono(Movie.class)
//                    .block();
