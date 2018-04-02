package com.cesar.androidtest.postdetails.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class CommentModel {
    var comments: Array<Comment>? = null
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
    var replies: Array<Comment>? = null
}