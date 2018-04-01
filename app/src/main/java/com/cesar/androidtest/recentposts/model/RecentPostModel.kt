package com.cesar.androidtest.recentposts.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class RecentPostModel {
    var data: Data? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
    var after: String? = null
    var name: String? = null
    var children: Array<RecentPostModel>? = null
    var title: String? = null
    var url: String? = null
    var author: String? = null
    var preview: Preview? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Preview {
    val images: Array<Images>? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Images {
    val source: Source? = null
    val resolutions: Array<Resolutions>? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Source {
    val url: String? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Resolutions {
    val url: String? = null
    val width: Number? = null
    val height: Number? = null
}