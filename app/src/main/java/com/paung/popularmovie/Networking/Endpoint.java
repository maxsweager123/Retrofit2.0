package com.paung.popularmovie.Networking;

import com.paung.popularmovie.Model.ModelMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by mkddr on 16/12/17.
 */

public interface Endpoint {
    int VERSION = 3;
    String API_KEY = "b3162b892dc842005579761440afb2c4";

    @GET(VERSION+"/movie/popular?api_key="+API_KEY+"&language=en-US")
    Call<ModelMovie> popularMovies();
}
