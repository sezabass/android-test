package com.cesar.androidtest.postdetails.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CommentModel @JsonCreator constructor(comments: Array<Comment>) {
    var comments: Array<Comment>? = comments
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Comment {
    var data: Data? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
    var author: String? = null
    var body: String? = null
    var children: Array<Comment>? = null
    var replies: Comment? = null
}