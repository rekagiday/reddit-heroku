package com.greenfoxacademy.reka.reddit.repository;

import com.greenfoxacademy.reka.reddit.model.Post;
import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {

  List<Post> findAllByOrderByScoreDesc();
}
