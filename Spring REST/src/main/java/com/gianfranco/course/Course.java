package com.gianfranco.course;

import com.gianfranco.core.BaseEntity;
import com.gianfranco.review.Review;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course extends BaseEntity {
    @NotNull
    private String title;

    @NotNull
    private String url;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Review> reviews;

    protected Course() {
        super();
        reviews = new ArrayList<>();
    }

    public Course(String tittle, String url) {
        this();
        this.title = tittle;
        this.url = url;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        review.setCourse(this);
        reviews.add(review);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
