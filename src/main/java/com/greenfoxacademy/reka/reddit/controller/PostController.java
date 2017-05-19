package com.greenfoxacademy.reka.reddit.controller;

import com.greenfoxacademy.reka.reddit.model.Post;
import com.greenfoxacademy.reka.reddit.model.PostList;
import com.greenfoxacademy.reka.reddit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  @Autowired
  PostRepository repository;
  @Autowired
  PostList posts;


  @GetMapping(value = "/posts")
  public PostList listPosts() {                                   //    @PageableDefault (sort={"score"}, direction = Sort.Direction.DESC) Pageable pageable)
    posts.addAll();
    return posts;
  }

  @PostMapping(value = "/posts")
  public Post addPost(@RequestBody Post post) {
    return repository.save(post);
  }


  @PutMapping(value = "/posts/{id}/downvote")
  public Post downPost(@PathVariable(value = "id") long id) {
    Post post = repository.findOne(id);
    post.downVote();
    repository.save(post);
    return post;
  }

  @PutMapping(value = "/posts/{id}/upvote")
  public Post upPost(@PathVariable(value = "id") long id) {
    Post post = repository.findOne(id);
    post.upVote();
    repository.save(post);
    return post;
  }


}

