package com.paung.popularmovie;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.widget.Toast;

import com.paung.popularmovie.Adapter.AdapterHome;
import com.paung.popularmovie.Model.ModelMovie;
import com.paung.popularmovie.Networking.Endpoint;
import com.paung.popularmovie.Networking.Server;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    Endpoint endpoint;
    private ArrayList<ModelMovie.ResultMovie> modelMovie;
    AdapterHome adapterHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getMovie();

    }

    Callback<ModelMovie> callback = new Callback<ModelMovie>() {
        @Override
        public void onResponse(Call<ModelMovie> call, Response<ModelMovie> response) {
            modelMovie = new ArrayList<>();
            modelMovie.clear();
            Log.d("RESPONSE", "onResponse: " + response.body().results);
            adapterHome = new AdapterHome(response.body().results,getApplicationContext());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());;
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            RecyclerView rcHome = (RecyclerView) findViewById(R.id.rcHome);
            rcHome.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            rcHome.setAdapter(adapterHome);
            adapterHome.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<ModelMovie> call, Throwable t) {

        }
    };

    public void getMovie() {
        endpoint = Server.getClient().create(Endpoint.class);
        Call<ModelMovie> call = endpoint.popularMovies();
        call.enqueue(callback);
    }
}
