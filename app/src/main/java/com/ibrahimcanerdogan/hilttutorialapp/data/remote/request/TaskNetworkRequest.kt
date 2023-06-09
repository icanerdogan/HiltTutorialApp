package com.ibrahimcanerdogan.hilttutorialapp.data.remote.request

import com.google.gson.annotations.SerializedName

data class TaskNetworkRequest(
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("note")
    val note: String,
    @SerializedName("status")
    val status: String
)