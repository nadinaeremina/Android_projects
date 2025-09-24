package com.example.motionevent;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // нужно завести две координаты
        float x = event.getX();
        float y = event.getY();

        // чистое действие
        int action = event.getActionMasked();

        // сколько пальцев на экране
        int pointerCount = event.getPointerCount();

        Log.d("Touch", "Количество пальцев "+pointerCount);

        // 1
        // switch (event.getAction()) {

            //// мы нажали (экран нас почувствовал)
            //case MotionEvent.ACTION_DOWN:
                //Log.d("Touch", "Нажатие x="+x+", y="+y);
                //break;

            //// движение (пальцем)
            //case MotionEvent.ACTION_MOVE:
                //Log.d("Touch", "Движение x="+x+", y="+y);
                //break;

            //// мы отпустили
            //case MotionEvent.ACTION_UP:
                //Log.d("Touch", "Отпускание x="+x+", y="+y);
                //break;
        //}
        //return  true;

        // 2
        switch (action) {

            case MotionEvent.ACTION_DOWN:

                // новый палец добавился
            case MotionEvent.ACTION_POINTER_DOWN:
                int indexDown = event.getActionIndex();
                int idDown = event.getPointerId(indexDown);
                Log.d("Touch", "Палец "+idDown+" коснулся x "+
                        event.getX(indexDown)+" коснулся y "+event.getY(indexDown));
                break;

                // один из пальцев убрали, но не последний
            case MotionEvent.ACTION_POINTER_UP:
                int indexUp = event.getActionIndex();
                int idUp = event.getPointerId(indexUp);
                Log.d("Touch", "Палец "+idUp+" отпущен");
                break;

                // обходим все пальцы целиком
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < pointerCount; i++) {
                    int id = event.getPointerId(i);
                    Log.d("Touch", "Палец "+id+" двигается x "+
                            event.getX(i)+" коснулся y "+event.getY(i));
                }
                break;
        }
        return  true;
    }
}