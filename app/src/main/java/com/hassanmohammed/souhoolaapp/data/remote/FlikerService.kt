package com.hassanmohammed.souhoolaapp.data.remote

import com.hassanmohammed.souhoolaapp.BuildConfig
import com.hassanmohammed.souhoolaapp.domain.models.FlikerPhotos
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlikerService {
    @GET("rest")
    suspend fun getPhotos(
        @Query("method") method: String = "fliker.photos.search",
        @Query("format") format: String = "json",
        @Query("nojsoncallback") noJsonCallback: Int = 50,
        @Query("text") text: String = "Color",
        @Query("page") page: Int = 1,
        @Query("per_page") per_page: Int = 20,
        @Query("api_key") apiKey: String = BuildConfig.FLIKER_API_KEY
    ) : Response<FlikerPhotos>
}