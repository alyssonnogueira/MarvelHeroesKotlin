package com.example.alysson.marvelcomicskotiln.services

import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface HeroService {

    //?ts={ts}&apiKey={apiKey}&hash={hash}&offset={offset}
    @GET("/v1/public/characters")
    fun getAll(
            @Query("ts") ts: Long,
            @Query("apikey") apiKey: String,
            @Query("hash") hash: String,
            @Query("offset") offset: Int): Observable<JsonObject>
}