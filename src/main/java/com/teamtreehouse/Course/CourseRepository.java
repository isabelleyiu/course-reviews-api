package com.teamtreehouse.Course;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

// extends built-in CRUD actions CrudRepository
// introduce paging with PagingAndSortingRepository
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

//  @RestResource(rel = "title-contains", path = "containsTitle")
  Page<Course> findByTitleContaining(@Param("title") String title, Pageable page);
}

