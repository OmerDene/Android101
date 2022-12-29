package com.example.android101.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;

import com.example.android101.databinding.ActivityInstagramCloneBinding;

public class InstagramClone extends AppCompatActivity {
    private ActivityInstagramCloneBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInstagramCloneBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
    }
    public void signInClicked(View view) {

    }
    public void signUpClicked (View view) {


    }
}