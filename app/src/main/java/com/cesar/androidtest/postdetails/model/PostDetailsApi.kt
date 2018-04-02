package com.cesar.androidtest.postdetails.model

interface PostDetailsApi {
    fun list(id:String, callback: ResultListener?)

    interface ResultListener {
        fun onResponseSuccessful(response: List<Comment>)
        fun onFailure(errorMessage: String)
    }
}