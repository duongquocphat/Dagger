package com.shakutara.daggersample.ui

import android.arch.lifecycle.MutableLiveData
import com.shakutara.daggersample.api.MovieApiService
import com.shakutara.daggersample.api.MovieRepository
import com.shakutara.daggersample.entity.MovieEntity
import javax.inject.Inject

class MovieListViewModel @Inject constructor(movieApiService: MovieApiService) :
    BaseViewModel() {

    private val movieRepository: MovieRepository = MovieRepository(movieApiService)
    private val moviesLiveData = MutableLiveData<List<MovieEntity>>()

    fun loadMoreMovies() {
        movieRepository
            .loadMoviesByType()
            .doOnSubscribe { disposable -> addToDisposable(disposable) }
            .subscribe { resource -> getMoviesLiveData().postValue(resource) }
    }

    fun getMoviesLiveData() = moviesLiveData
}