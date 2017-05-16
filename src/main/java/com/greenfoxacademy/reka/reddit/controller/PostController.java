package com.greenfoxacademy.reka.reddit.controller;

import com.greenfoxacademy.reka.reddit.model.Post;
import com.greenfoxacademy.reka.reddit.model.PostList;
import com.greenfoxacademy.reka.reddit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  @Autowired
  PostRepository repository;
  @Autowired
  PostList posts;


  @GetMapping(value = "/posts")
  public PostList listPosts() {
    posts.addAll();
    return posts;
  }

  @PostMapping(value = "/posts")
  public Post addPost(@RequestBody Post post) {
    return repository.save(post);
  }


  @PutMapping(value = "/posts/{id}/downvote")
  public void downPost(@PathVariable(value = "id") long id) {
    Post post = repository.findOne(id);
    post.downVote();
    repository.save(post);
  }

  @RequestMapping(value = "/posts/{id}/upvote", method = RequestMethod.PUT)
  public void upPost(@PathVariable(value = "id") long id) {
    Post post = repository.findOne(id);
    post.upVote();
    repository.save(post);
  }


}

