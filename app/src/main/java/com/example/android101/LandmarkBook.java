package com.example.android101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.android101.databinding.ActivityDetailBinding;
import com.example.android101.databinding.ActivityMainBinding;
import com.example.android101.databinding.LandmarkBookActivityBinding;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LandmarkBook extends AppCompatActivity {
    ArrayList<Landmark> landmarkArraylist;
    private LandmarkBookActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LandmarkBookActivityBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        landmarkArraylist = new ArrayList<>();

        Landmark pisa = new Landmark("pisa","italy",R.drawable.pizza);
        Landmark effiel = new Landmark("effiel","france",R.drawable.eyfel);
        Landmark collessium = new Landmark("collessium","italy",R.drawable.collesium);
        Landmark londonBridge =new Landmark("LondonBridge","UK",R.drawable.londonbridge);

        landmarkArraylist.add(pisa);
        landmarkArraylist.add(effiel);
        landmarkArraylist.add(collessium);
        landmarkArraylist.add(londonBridge);

        // Adapter , listView , mapping
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,landmarkArraylist
                    .stream().map(landmark -> landmark.name).collect(Collectors.toList()));

            binding.listView.setAdapter(arrayAdapter);
        }


    }
}