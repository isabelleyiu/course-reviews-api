package com.teamtreehouse.core;

import com.teamtreehouse.Course.Course;
import com.teamtreehouse.Course.CourseRepository;
import com.teamtreehouse.review.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
  private final CourseRepository courses;

  @Autowired
  public DatabaseLoader(CourseRepository courses) {
    this.courses = courses;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
    Course course = new Course("Java Basics", "https://teamtreehouse.com/library/java-basics");
    course.addReviews(new Review(5, "Excellent!"));
    courses.save(course);

    String[] templates = {
            "%s Basics",
            "Intermediate %s",
            "Advanced %s"
    };
    String[] buzzwords = {
            "Spring REST Date",
            "Java 9",
            "Scala",
            "Hibernate",
            "Groovy"
    };

    List<Course> seedCourses = new ArrayList<>();
    IntStream.range(0, 100)
            .forEach(i -> {
              String template = templates[i % templates.length];
              String buzzword = buzzwords[i % buzzwords.length];
              String title = String.format(template, buzzword);
              Course c = new Course(title, "http://www.example.com");
              c.addReviews(new Review(i % 5, String.format("Learning %s", buzzword)));
              seedCourses.add(c);
            });
    courses.saveAll(seedCourses);
  }
}
