package com.and.sauna.networking;

import com.and.sauna.model.MeasurementDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MeasurementAPI {
    @GET("measurement/getMeasurementBySaunaName/{saunaName}")
    Call<List<MeasurementDTO>> getMeasurementsBySaunaName(@Path("saunaName") String saunaName);
}
