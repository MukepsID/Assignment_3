package com.example.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.assignment3.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private static final String TAG = "CovidTrackerActivity";
    int data = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        findCovid();
    }

    private void findCovid() {
        showLoading(true);

        Call <Covid> client = ApiConfig.service().getDataCovid();
        client.enqueue(new Callback<Covid>() {
            @Override
            public void onResponse(@NonNull Call<Covid> call, @NonNull Response<Covid> response) {
                showLoading(false);
                if (response.isSuccessful()) {
                    Covid result = response.body();
                    assert result != null;
                    Log.d(TAG, result.toString());
                    data = result.getCases();
                    setCovidData(result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Covid> call, @NonNull Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                showLoading(true);

            }
        });
    }

    private void setCovidData(Covid covidData) {
        binding.countCases.setText(String.valueOf(covidData.getCases()));
        binding.countRecoverCovid.setText(String.valueOf(covidData.getRecovered()));
        binding.countDeathCovid.setText(String.valueOf(covidData.getDeaths()));
    }

    private void showLoading(boolean isLoading) {
        if (isLoading) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }
    }
}