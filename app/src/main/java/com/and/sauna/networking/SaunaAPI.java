package com.and.sauna.networking;

import android.graphics.ColorSpace;

import com.and.sauna.model.Sauna;

import retrofit2.Call;

public interface SaunaAPI {

    Call<Sauna> getAllData();
}
