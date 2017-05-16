let server = "";

function ajax(method, endpoint, data, cb) {
  let httpRequest = new XMLHttpRequest();
  httpRequest.open(method, server+endpoint, true);
  httpRequest.setRequestHeader('Content-Type', 'application/json');
  httpRequest.send(JSON.stringify(data));
  httpRequest.onreadystatechange = function () {
    if(httpRequest.readyState == XMLHttpRequest.DONE && httpRequest.status == 200) {
      cb(JSON.parse(httpRequest.responseText));
    }
  };
}

function createElement(element) {
  var res = document.createElement(element.type);
  if('classes' in element && element.classes.length != 0) {
    res.classList.add(element.classes);
  }
  if('attributes' in element) {
    Object.keys(element.attributes).forEach(function (attr) {
      res.setAttribute(attr, element.attributes[attr]);
    });
  }
  if('inner' in element) {
    res.innerHTML = element.inner;
  }

  if('children' in element) {
    element.children.forEach(function (child) {
      res.appendChild(child);
    });
  }
  return res;
}

function createPost (post, index, voted = 0) {
  var article = createElement({
    type: "article",
    attributes: {
      id: "post-"+post["id"]
    },
    children: [
      createElement({
        type: "div",
        classes: "number",
        inner: index + 1
      }),
      createElement({
        type: "div",
        classes: "score",
        children: [
          createElement({
            type: "img",
            attributes: {
              "src": "img/upvote"+(voted == 1 ? "d":"")+".png",
              "alt":"upvote",
              "data-id": post["id"]
            }
          }),
          createElement({
            type: "p",
            inner: post["score"]
          }),
          createElement({
            type: "img",
            attributes: {
              "src": "img/downvote"+(voted == -1 ? "d":"")+".png",
              "alt":"downvote",
              "data-id": post["id"]
            }
          })
        ]
      }),
      createElement({
        type: "div",
        classes: "content",
        children: [
          createElement({
            type: "h2",
            children: [
              createElement({
                type: "a",
                attributes: {"href": post["href"]},
                inner: post["title"]
              })
            ]
          }),
          createElement({
            type: "p",
            inner: "submitted 1 year ago by <strong>"+("anonymous")+"</strong>"
          })
        ]
      })
    ]
  });
  return article;
}

function appendPost(article) {
  document.querySelector("main").appendChild(article);
  article.querySelectorAll("img").forEach(function (item) {
    item.onclick = handleVote;
  })
}

function handleVote(event) {
  var isUpvote = event.target.getAttribute("alt") == "upvote";
  ajax("PUT",
      "/posts/" + event.target.getAttribute("data-id") + "/" + (isUpvote ? "upvote" : "downvote"),
      {},
      function (data) {
        var oldChild = document.querySelector("#post-"+data["id"]);
        var number = oldChild.querySelector(".number").innerText;
        document.querySelector("main").replaceChild(
            createPost(data, parseInt(number)-1, (isUpvote ? 1 : -1)),
            oldChild);
      });
}

document.onload = ajax("GET", "/posts", null, function (data) {
  data["posts"].forEach(function (data, index) {
    appendPost(createPost(data, index));
  });

  document.querySelector("input[type='submit']").onclick = function (event) {
    event.preventDefault();
    ajax("POST", "/posts", {
      href: document.querySelector("#url").value,
      title: document.querySelector("#title").value
    }, function (data) {
      appendPost(createPost(data, document.querySelector("main").childElementCount - 1));
      document.querySelector("#new").classList.add("hidden");
    });
  };

  document.querySelector("#add_post").onclick = function (event) {
    event.preventDefault();
    document.querySelector("#new").classList.remove("hidden");
  }
});