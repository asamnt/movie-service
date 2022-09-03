package io.javabrains.ratingsdataservice.resources;

import io.javabrains.ratingsdataservice.models.Rating;
import io.javabrains.ratingsdataservice.models.UserRating;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

        @RequestMapping("/{movieId}")
        public Rating getRating(@PathVariable("movieId") String movieId){
            return new Rating(movieId, 1);

        }

        @RequestMapping("/user/{userId}")
        public UserRating getRatings(@PathVariable("userId") String userId){
//            return new Rating(movieId, 1);
            //There is a list of ratings
            //these could be coming from db but we have a hard coded list here
            List<Rating> ratings = Arrays.asList(
                    new Rating("100", 4),
                    new Rating("550", 3)
            );

            //we create a userrating object and add the ratings list to the object
            //because we do not want to send a list
            //sending objects is the right way to do things
            //because tomorrow you can send other attributes, other than the list
            UserRating userRating = new UserRating();
            userRating.setRating(ratings);
            return userRating;

        }

}
