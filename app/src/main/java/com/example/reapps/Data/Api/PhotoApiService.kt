package com.example.reapps.Data.Api

import com.example.reapps.Data.Model.PhotoModel
import retrofit2.http.GET

interface PhotoApiService {
    @GET("list")
    suspend fun getPhotos(): List<PhotoModel>
}