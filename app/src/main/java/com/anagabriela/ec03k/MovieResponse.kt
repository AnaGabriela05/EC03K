package com.anagabriela.ec03k

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("status") var status: String,
    @SerializedName("message") var image: List<String>)