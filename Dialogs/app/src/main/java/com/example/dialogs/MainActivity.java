package com.example.dialogs;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button button;
    Button date;
    Button time;
    TextView current;

    // установка начального значение времени
    Calendar dateAndTime = Calendar.getInstance();

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

        button = findViewById(R.id.button);
        time = findViewById(R.id.time);
        date = findViewById(R.id.date);
        current = findViewById(R.id.textview1);

        setInitialDate();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //класс билдера для создания диалога
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //заголовок диалога
                builder.setTitle("Подтверждение");
                //иконка
                builder.setIcon(R.drawable.ic_launcher_background);
                //метод запрета на сворачивание диалога (опционально)
                builder.setCancelable(false);
                //сообщение пользователю
                builder.setMessage("Вы уверены что хотите выйти?");

                // 1
                //кнопка согласия пользователя
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                // 2
                //кнопка отрицания пользователя
                builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // закрытие диалога
                        dialogInterface.dismiss();
                    }
                });

                // 3
                //кнопка нейтрального выбора
                /*
                builder.setNeutralButton("Не знаю", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //действие которое не вписывается ни в одну из предыдущих кнопок
                    }
                });
                 */

                //создание самого диалога и вывод на экран
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //диалог с выбором даты (второй аргумент - слушатель,
                // который нужно предварительно создать
                new DatePickerDialog(MainActivity.this,
                        d,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //диалог с выбором времени(второй аргумент-слушатель который нужно предварительно создать
                new TimePickerDialog(MainActivity.this,t,dateAndTime.get(Calendar.HOUR_OF_DAY),dateAndTime.get(Calendar.MINUTE),true).show();
            }
        });
    }

    //метод инициализации времени в Textview
    private void setInitialDate() {
        current.setText(DateUtils.formatDateTime(
                this,dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE|DateUtils.FORMAT_SHOW_YEAR|DateUtils.FORMAT_SHOW_TIME));
    }

    //слушатель даты (обработчик)
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR,year);
            dateAndTime.set(Calendar.MONTH,monthOfYear);
            dateAndTime.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            setInitialDate();
        }
    };

    //слушатель времени (обработчик)
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY,hourOfDay);
            dateAndTime.set(Calendar.MINUTE,minute);
            setInitialDate();
        }
    };
    }