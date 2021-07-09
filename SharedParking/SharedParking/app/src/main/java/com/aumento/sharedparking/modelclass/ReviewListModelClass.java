package com.aumento.sharedparking.modelclass;

public class ReviewListModelClass {

    String id;
    String rating;
    String review;

    public ReviewListModelClass(String id, String rating, String review) {
        this.id = id;
        this.rating = rating;
        this.review = review;
    }

    public String getId() {
        return id;
    }

    public String getRating() {
        return rating;
    }

    public String getReview() {
        return review;
    }
}
