package com.teamtreehouse.Course;

import com.teamtreehouse.core.BaseEntity;
import com.teamtreehouse.review.Review;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Course extends BaseEntity {
  @NotNull
  @Size(min = 1, max = 140)
  private String title;
  private String url;
  @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
  private List<Review> reviews;


  protected Course() {
    super();
    reviews = new ArrayList<>();
  }

  public Course(String title, String url) {
    // calling the protected Course() constructor
    this();
    this.title = title;
    this.url = url;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void addReviews(Review review) {
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
