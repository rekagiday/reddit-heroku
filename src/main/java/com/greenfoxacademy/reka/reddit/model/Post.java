package com.greenfoxacademy.reka.reddit.model;

import java.sql.Date;
import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long id;
  String title;
  String href;
  Timestamp timestamp =  new Timestamp(System.currentTimeMillis());
  int score;

  public Post(String title, String href, Timestamp timestamp, int score) {
    this.title = title;
    this.href = href;
    this.timestamp = timestamp;
    this.score = score;
  }

  public Post() {
  }

  public Post(String title, String href) {
    this.title = title;
    this.href = href;
  }

  public void downVote() {
    score--;
  }

  public void upVote() {
    score++;
  }

}
