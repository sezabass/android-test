package com.cesar.androidtest.recentposts.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class RecentPost {
    var data: Data? = null

    fun imageUrl() = this.data?.preview?.images?.get(0)?.source?.url
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
    var after: String? = null
    var name: String? = null
    var id: String? = null
    var children: Array<RecentPost>? = null
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