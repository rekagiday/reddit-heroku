package com.greenfoxacademy.reka.reddit.model;

import com.greenfoxacademy.reka.reddit.repository.PostRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PostList implements Serializable {

  Iterable<Post> posts;

  @Autowired
  PostRepository repository;

  public PostList() {}

  public Iterable<Post> getPosts() {
    return posts;
  }

  public void setPosts(Iterable<Post> posts) {
    this.posts = posts;
  }

  public void addAll() {                                                                            //Pageable pageable
    posts = repository.findAllByOrderByScoreDesc();                                                 // pageable
    System.out.println(posts);
  }


}
