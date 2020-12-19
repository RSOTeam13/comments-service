![Albify](https://raw.githubusercontent.com/RSOTeam13/documentation/main/resources/logo-dark.png)
# Albify - Comments service

![Travis-Build](https://travis-ci.com/RSOTeam13/comments-service.svg?branch=master)
 
Micro service for operations on images in Albify application. You can find project documentation [here](https://github.com/RSOTeam13/documentation).

#### Config server
Get consul and run it (locally) with `./consul agent -dev`

Values are first loaded when they are used, so you also need to call `localhost:8081/v1/comments/config` (new entry should be visible in logs).

Then either connect to `localhost:8500` or use some other method to set `environments/dev/services/comment-service-service/1.0.0/config/rest-config/maintenance-mode` to a boolean (preferably `false` to start).

Setting the variable anew should provide new log entries with the changed value. 

Queries:
```
query getPaginatedCommentsImage1 {
  allComments(id: "5fc6ac268ba9f3367f9cb50c", offset: 0, limit: 10) {
    id
    createdAt
    text
    ownerId
    imageId
  }
}

query getPaginatedCommentsImage2 {
  allComments(id: "5fc6ac258ba9f3367f9cb50b", offset: 0, limit: 10) {
    id
    createdAt
    text
    ownerId
    imageId
  }
}


mutation addCommentImage1Comment1 {
  addComment(comment: {
    ownerId: "5fa7c59bed5d03648d5d5a05",
    imageId: "5fc6ac268ba9f3367f9cb50c",
    text: "Comment 1"
  }) {
    id,
    ownerId,
    imageId,
    text,
    createdAt
  }
}

mutation addCommentImage1Comment2 {
  addComment(comment: {
    ownerId: "5fa7c59bed5d03648d5d5a05",
    imageId: "5fc6ac268ba9f3367f9cb50c",
    text: "Comment 2"
  }) {
    id,
    ownerId,
    imageId,
    text,
    createdAt
  }
}

mutation addCommentImage2Comment1 {
  addComment(comment: {
    ownerId: "5fa7c59bed5d03648d5d5a05",
    imageId: "5fc6ac258ba9f3367f9cb50b",
    text: "Comment 3"
  }) {
    id,
    ownerId,
    imageId,
    text,
    createdAt
  }
}


```
