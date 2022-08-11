package com.and.sauna.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.graphics.Color;

import com.and.sauna.model.MeasurementDTO;
import com.and.sauna.networking.MeasurementAPI;
import com.and.sauna.repository.RetrofitClient;


import java.util.List;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.and.sauna.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GraphFragment extends Fragment {
    private static final String TAG = "GraphFragment";
    private GraphView graph;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        graph = view.findViewById(R.id.graph);

        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.BOTTOM);
        graph.getLegendRenderer().setSpacing(40);

        MeasurementAPI measurementAPI =
                RetrofitClient
                        .getRetrofitInstance()
                        .create(MeasurementAPI.class);

        Call<List<MeasurementDTO>> call = measurementAPI.getMeasurementsBySaunaName("HotSauna");
        call.enqueue(new Callback<List<MeasurementDTO>>() {
            @Override
            public void onResponse(
                    Call<List<MeasurementDTO>> call,
                    Response<List<MeasurementDTO>> response
            ) {
                if (response.code() == 200) {
                    List<MeasurementDTO> measurements = response.body();
                    LineGraphSeries<DataPoint> co2Series = createGraphSeries(
                            MeasurementType.CO2,
                            measurements,
                            Color.YELLOW
                    );
                    LineGraphSeries<DataPoint> humiditySeries = createGraphSeries(
                            MeasurementType.HUMIDITY,
                            measurements,
                            Color.GREEN
                    );
                    LineGraphSeries<DataPoint> temperatureSeries = createGraphSeries(
                            MeasurementType.TEMPERATURE,
                            measurements,
                            Color.BLUE
                    );

                    graph.addSeries(co2Series);
                    graph.addSeries(humiditySeries);
                    graph.addSeries(temperatureSeries);

                    graph.getLegendRenderer().setVisible(true);

                    Log.d("measurementAPI", "Data was fetched");
                     ;
                    Log.d(
                            "measurementAPI",
                            new GsonBuilder()
                                    .setPrettyPrinting()
                                    .create()
                                    .toJson(measurements)
                    );
                }
            }

            @Override
            public void onFailure(Call<List<MeasurementDTO>> call, Throwable t) {
                Log.e("measurementAPI", "onFailure was invoked");
                Log.e("measurementAPI", t.getMessage());
            }
        });
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }

    private LineGraphSeries<DataPoint> createGraphSeries(
            MeasurementType measurementType,
            List<MeasurementDTO> measurements,
            int color
    ) {
        DataPoint[] dataPoints = new DataPoint[measurements.size()];
        for (int i = 0; i < measurements.size(); i++) {
            int measurement = 0;
            if (measurementType.equals(MeasurementType.CO2)) {
                measurement = measurements.get(i).getCo2();
            }
            else if (measurementType.equals(MeasurementType.HUMIDITY)) {
                measurement = measurements.get(i).getHumidity();
            }
            else if (measurementType.equals(MeasurementType.TEMPERATURE)) {
                measurement = measurements.get(i).getTemperature();
            }

            dataPoints[i] = new DataPoint(
                    i,
                    measurement
            );
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries(dataPoints);

        setSeriesStyle(series, measurementType, color);

        return series;
    }

    private enum MeasurementType {
        CO2("CO2"),
        HUMIDITY("Humidity"),
        TEMPERATURE("Temperature");

        private String value;

        MeasurementType(final String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return this.getValue();
        }
    }

    private void setSeriesStyle(
            LineGraphSeries<DataPoint> series,
            MeasurementType measurementType,
            int color
    ) {
        series.setTitle(measurementType.toString());
        series.setColor(color);
        series.setDrawDataPoints(true);
        series.setDataPointsRadius(13);
        series.setThickness(8);
    }
}