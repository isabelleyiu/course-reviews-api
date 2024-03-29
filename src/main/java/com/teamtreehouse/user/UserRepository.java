package com.teamtreehouse.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// protect user api being browse by the rest of world
@RepositoryRestResource(exported = false)
public interface UserRepository extends CrudRepository<User, Long> {
  User findByUsername(String username);
}
