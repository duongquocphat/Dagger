package com.shakutara.daggersample.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.shakutara.daggersample.di.viewmodel.ViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var moviesListViewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.shakutara.daggersample.R.layout.activity_main)

        AndroidInjection.inject(this)

        moviesListViewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
        moviesListViewModel.getMoviesLiveData().observe(this, Observer { resource ->
            if (resource?.isNotEmpty() == true)
                Toast.makeText(this, resource.toString(), Toast.LENGTH_SHORT).show()
        })

        /* Fetch movies list  */
        moviesListViewModel.loadMoreMovies()
    }
}
