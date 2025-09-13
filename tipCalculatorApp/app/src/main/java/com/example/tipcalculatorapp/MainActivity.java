package com.example.tipcalculatorapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText editTextOrder;
    EditText editTextTip;
    Button buttonOk;
    TextView textOrder;
    TextView textOrderNum;
    TextView textTip;
    TextView textTipNum;
    TextView textTotal;
    TextView textTotalNum;

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

        editTextOrder = findViewById(R.id.textViewOrder);
        editTextTip = findViewById(R.id.textViewTip);

        buttonOk = findViewById(R.id.buttonOk);

        textOrder = findViewById(R.id.textOrder);
        textOrderNum = findViewById(R.id.textOrderNum);
        textTip = findViewById(R.id.textTip);
        textTipNum = findViewById(R.id.textTipNum);
        textTotal = findViewById(R.id.textTotal);
        textTotalNum = findViewById(R.id.textTotalNum);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int total = (Integer.parseInt(textOrderNum.getText().toString())) +
                        (Integer.parseInt(textTipNum.getText().toString()));

                textTotalNum.setText(String.valueOf(total));
            }
        });

        editTextOrder.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textOrderNum.setText(s);
            }
        });

        editTextTip.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                textTipNum.setText(s);
            }
        });
    }
}