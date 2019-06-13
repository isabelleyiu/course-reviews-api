package com.teamtreehouse.core;

import com.teamtreehouse.Course.Course;
import com.teamtreehouse.Course.CourseRepository;
import com.teamtreehouse.review.Review;
import com.teamtreehouse.user.User;
import com.teamtreehouse.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
  private final CourseRepository courses;
  private final UserRepository users;

  @Autowired
  public DatabaseLoader(CourseRepository courses, UserRepository users) {
    this.courses = courses;
    this.users = users;
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {
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

    List<User> students = Arrays.asList(
            new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
            new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
            new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
            new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
            new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
            new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
            new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
            new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
            new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
            new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
            new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
            new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})
    );
    users.saveAll(students);
    users.save(new User("isabelleyiu", "Isabelle",  "Yiu", "password", new String[] {"ROLE_USER", "ROLE_ADMIN"}));

    List<Course> seedCourses = new ArrayList<>();
    IntStream.range(0, 100)
            .forEach(i -> {
              String template = templates[i % templates.length];
              String buzzword = buzzwords[i % buzzwords.length];
              String title = String.format(template, buzzword);

              Course c = new Course(title, "http://www.example.com");
              Review review = new Review((i % 5) + 1, String.format("Learning %s", buzzword));
              review.setReviewer(students.get(i % students.size()));
              c.addReviews(review);
              seedCourses.add(c);
            });
    courses.saveAll(seedCourses);
  }
}
