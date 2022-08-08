package com.and.sauna.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;

import com.broooapps.graphview.CurveGraphConfig;
import com.broooapps.graphview.CurveGraphView;
import com.broooapps.graphview.models.GraphData;
import com.broooapps.graphview.models.PointMap;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.and.sauna.R;


public class GraphFragment extends Fragment {
CurveGraphView curveGraphView;




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        curveGraphView = view.findViewById(R.id.cgv);

        curveGraphView.configure(
                new CurveGraphConfig.Builder(getContext())
                        .setAxisColor(R.color.Blue)                                             // Set X and Y axis line color stroke.
                        .setIntervalDisplayCount(5)                                             // Set number of values to be displayed in X ax
                        .setHorizontalGuideline(2)                                             // Set number of background guidelines to be shown.
                        .setVerticalGuideline(2)
                        .setGuidelineColor(R.color.GreenYellow)                                 // Set color of the visible guidelines.
                        .setNoDataMsg("No Data")                                              // Message when no data is provided to the view.
                        .setxAxisScaleTextColor(R.color.Black)                                  // Set X axis scale text color.
                        .setyAxisScaleTextColor(R.color.Black)                                  // Set Y axis scale text color
                        .build()
        );

        PointMap pointMap = new PointMap();
        pointMap.addPoint(1, 100);
        pointMap.addPoint(2, 500);
        pointMap.addPoint(3, 800);
        pointMap.addPoint(4, 600);

        GraphData gd = GraphData.builder(getContext())
                .setPointMap(pointMap)
                .setGraphStroke(R.color.Black)
                .setGraphGradient(R.color.teal_200, R.color.purple_700)
                .setPointRadius(10)                                                      // set point radius
                .setPointColor(R.color.purple_500)                                              // set point color
                .animateLine(true)
                .build();

//        PointMap p2 = new PointMap();
//        p2.addPoint(0, 140);
//        p2.addPoint(1, 700);
//        p2.addPoint(2, 100);
//        p2.addPoint(3, 0);
//        p2.addPoint(4, 190);
//
//        GraphData gd2 = GraphData.builder(getContext())
//                .setPointMap(p2)
//                .setGraphStroke(R.color.GreenYellow)
//                .setGraphGradient(R.color.purple_200, R.color.purple_500)
//                .build();


        curveGraphView.setData(5, 1000, gd);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }
}