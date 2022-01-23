package com.anagabriela.ec03k

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface API{
    @GET
    suspend fun getMovieby(@Url url:String):Response<MovieResponse>
}