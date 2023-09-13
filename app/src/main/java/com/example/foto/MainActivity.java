package com.example.foto;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;

import com.example.foto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private ActivityResultLauncher<Intent> content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        content = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), resultado -> {
           if (resultado.getResultCode() == Activity.RESULT_OK){
               Bundle data = resultado.getData().getExtras();
               Bitmap image = (Bitmap) data.get("data");
               binding.imageView.setImageBitmap(image);
           }
        });

        binding.btn.setOnClickListener(view -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            content.launch(intent);
        });
    }
}