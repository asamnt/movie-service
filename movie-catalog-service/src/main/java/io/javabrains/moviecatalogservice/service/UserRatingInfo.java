package io.javabrains.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.javabrains.moviecatalogservice.models.Rating;
import io.javabrains.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRatingInfo {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userId) {
        UserRating ratings = restTemplate.getForObject("http://movie-data-service/ratingsdata/user/foo"+ userId, UserRating.class);
        return ratings;
    }

    //Fallback Mechanism


    public UserRating getFallbackUserRating(String userId){
        UserRating userRating = new UserRating();
        userRating.setUserId(userId);
        userRating.setRating(Arrays.asList(new Rating("0",0)));
        return userRating;
        //return Arrays.asList((new CatalogItem("No movie","",0)));
    }

}
