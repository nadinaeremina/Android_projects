package com.example.paintproject;

import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    PaintView paintView;
    Button saveButton;
    Button change;
    Button clear;
    Button smallerWidth;
    Button biggerWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        paintView = findViewById(R.id.paintView);
        saveButton = findViewById(R.id.btnSave);
        change = findViewById(R.id.btnColor);
        clear = findViewById(R.id.clear);
        smallerWidth = findViewById(R.id.smallerWidth);
        biggerWidth = findViewById(R.id.biggerWidth);

        saveButton.setOnClickListener(v->{
            paintView.saveToGallery(this);
        });

        clear.setOnClickListener(v->{
            paintView.clearCanvas();
        });

        change.setOnClickListener(v->{
            paintView.changeColor(change);
        });

        smallerWidth.setOnClickListener(v->{
            paintView.smallerWidth(smallerWidth);
        });

        biggerWidth.setOnClickListener(v->{
            paintView.biggerWidth(biggerWidth);
        });
    }
}