package com.cesar.androidtest.recentposts.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

//data class RecentPostModel(val data: Data)
@JsonIgnoreProperties(ignoreUnknown = true)
class RecentPostModel {
    val data: Data? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Child {
    val data: Data? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Data {
    val children: Array<Child>? = null
    val title: String? = null
    val url: String? = null
    val preview: Preview? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Preview {
    val images: Array<Images>? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Images {
    val resolutions: Array<Resolutions>? = null
}

@JsonIgnoreProperties(ignoreUnknown = true)
class Resolutions {
    val url: String? = null
    val width: Number? = null
    val height: Number? = null
}