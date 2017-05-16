package com.greenfoxacademy.reka.reddit.repository;

import com.greenfoxacademy.reka.reddit.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
