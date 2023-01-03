package com.example.android101.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android101.databinding.ActivityFeedBinding;
import com.example.android101.databinding.ActivityInstagramCloneBinding;

public class FeedActivity extends AppCompatActivity {
    private ActivityFeedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
    public void startTop(View view) {
        if(topOne <= 0){
            return;
        }

        topOne--;
        bottomOne++;
        binding.secondText.setText("score " + topOne);

        if (topOne == 0) {
            Toast.makeText(getApplicationContext(), R.string.bottom_won, Toast.LENGTH_LONG).show();
        }

    }

    public void startBottom(View view) {
        if(bottomOne <= 0){
            return;
        }

        bottomOne--;
        topOne++;
        binding.firstText.setText("score " + bottomOne);

        if (bottomOne == 0) {
            Toast.makeText(getApplicationContext(), R.string.top_won, Toast.LENGTH_LONG).show();
        }

    }
}