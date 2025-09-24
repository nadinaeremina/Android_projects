package com.example.sqllite;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Room;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String LOG="SQL";

    Button btnAdd;
    Button btnRead;
    Button btnClear;

    EditText etName;
    EditText etLastName;
    EditText etEmail;
    EditText etPhone;

    AppDatabase db;
    UserDao userDao;

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

        btnAdd = findViewById(R.id.btnAdd);
        btnRead = findViewById(R.id.btnRead);
        btnClear = findViewById(R.id.btnClear);
        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etLastName = findViewById(R.id.etLastName);
        etPhone = findViewById(R.id.etPhone);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"myDb")
                .addMigrations(AppDatabase.MIGRATION_1_2)
                .addMigrations(AppDatabase.MIGRATION_2_3)
                .allowMainThreadQueries().build();

        userDao = db.userDao();

        btnAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String phone = etPhone.getText().toString();

                User user = new User();
                user.name = name;
                user.lastName = lastName;
                user.email = email;
                user.phone = phone;

                long id = userDao.insert(user);

                Log.d(LOG,"Добавлен пользователь с ID "+id);
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                List<User> users = userDao.getAll();
                if (users.size()==0) {
                    Log.d(LOG,"Ничего нет");
                }
                for (User user:users) {
                    Log.d(LOG,"ID= "+user.id+", name= "+ user.name+", " +
                           "lastName= "+user.lastName+", email= "+user.email+", " +
                            "phone= "+user.phone);
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int count = userDao.clearAll();
                userDao.resetAutoIncrement();
                Log.d(LOG,"Удалено  "+count+" и сброшен инкремент");
            }
        });
    }
}