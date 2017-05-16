// https://time-radish.glitch.me/posts
'use strict';

var url = 'https://localhost:8080/posts';

var score = document.querySelector('.score');
var upvote = document.querySelector('.upvote');
var downvote = document.querySelector('.downvote');
var ownerDiv = document.querySelector('.submit-line');
var orderbox = document.querySelector('.orderbox');
var clonesection  = document.querySelector('.section');
var main = document.querySelector('main');


function load(url, callback) {
  var xhr = new XMLHttpRequest();

  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      var responseFromServer = JSON.parse(xhr.response);  // IMPORTANT
      // console.log(responseFromServer);
      callback(responseFromServer);
    }
  };

  xhr.open('GET', url, true);
  xhr.send('');
}

load(url, function(datas){
  datas.posts.forEach(function(elem, idx) {
    // console.log(elem);

    var clone = clonesection.cloneNode(true);
    var score = clone.querySelector('.score');
    var posttitle = clone.querySelector('.post-title');
    var newLink = document.createElement('a');

    score.textContent = elem.score;
    newLink.href = elem.href;
    newLink.textContent = elem.title;
    posttitle.appendChild(newLink);
    // console.log(posttitle);

    clone.querySelector('.upvote').addEventListener('click', function(e) {
      // console.log(e.target);
      score.textContent = elem.score + 1;
    });

    clone.querySelector('.downvote').addEventListener('click', function(e) {
      // console.log(e.target);
      score.textContent = elem.score - 1;
    });
    main.appendChild(clone);

    owner(elem.owner);
    order(idx);
  });
  clonesection.style.display = 'none';
});


function owner(name) {
  ownerDiv.textContent = 'submitted 1 month ago by ' + name ;
}

function order(number) {
  orderbox.textContent = number;
}



// upvote.addEventListener('click', function(e) {
//   console.log(upvote);
//   // point += 1;
//   // score.textContent = point;
// });




// downvote.addEventListener('click', function(e) {
//     point -= 1;
//     score.textContent = point;
// });









// function newContent(arguments) {
//   var newDt = document.createElement('dt');
//   newDt.className = 'header' + (idx+1);
//   newDt.textContent = elem.headline.main;
//   document.querySelector('.section').appendChild(newDt);
// }