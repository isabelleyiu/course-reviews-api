package com.teamtreehouse.Course;

import org.springframework.data.repository.CrudRepository;

// extends built-in CRUD actions
public interface CourseRepository extends CrudRepository<Course, Long> {
}
