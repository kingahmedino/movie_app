package com.app.movieapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BackendAPI {
    companion object {
        const val API_KEY = "476318916d21defd745fef668feaaffe"
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"

        operator fun invoke() : BackendAPI{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BackendAPI::class.java)
        }
    }

    @GET("popular")
    suspend fun searchPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") key: String = API_KEY

    ): MovieResponse

    @GET("{movie_id}/credits")
    suspend fun movieCast(
        @Path("movie_id") movie_id: Int,
        @Query("api_key") key: String = API_KEY
    ): ActorResponse
}