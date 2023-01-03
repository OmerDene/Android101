package com.example.android101.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.android101.databinding.ActivityInstagramCloneBinding;
import com.example.android101.databinding.ActivityUploadBinding;
import com.google.firebase.auth.FirebaseAuthException;

public class UploadActivity extends AppCompatActivity {
    private ActivityUploadBinding binding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

    }
    public void uploadButtonClicked(View view) {

    }
    public void selectImage(View view) {

    }
}