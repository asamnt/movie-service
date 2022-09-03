package io.javabrains.moviecatalogservice.models;

import java.util.List;

public class UserRating {

    private String userId;
    private List<Rating> rating;

    public List<Rating> getRating() {
        return rating;
    }

    public void setRating(List<Rating> rating) {
        this.rating = rating;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
