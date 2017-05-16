package com.greenfoxacademy.reka.reddit.model;

import com.greenfoxacademy.reka.reddit.repository.PostRepository;
import java.io.Serializable;
import org.springframework.beans.factory.annotation.Autowired;
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

  public void addAll() {
    posts = repository.findAll();
    System.out.println(posts);
  }


}
