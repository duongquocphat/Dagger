package com.shakutara.daggersample.api

import com.shakutara.daggersample.entity.MovieEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface MovieApiService {
    @GET("movies.json")
    fun getMovieList(): Observable<ArrayList<MovieEntity>>
}