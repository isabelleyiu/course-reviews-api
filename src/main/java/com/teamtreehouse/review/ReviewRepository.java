package com.teamtreehouse.review;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {
  @Override
  // checking if authorized user is the reviewer
  // ? to prevent throwing error when null is returned
  @PreAuthorize("hasRole('ROLE_ADMIN') or @reviewRepository.findOne(#id)?.reviewer.?username == authentication.name")
  // extract id from param
  void deleteById(@Param("id") Long id);

  @Override
  @PreAuthorize("hasRole('ROLE_ADMIN') or #review.reviewer?.username == authentication.name")
  void delete(@Param("review") Review entity);
}
