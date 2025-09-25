package com.example.paintproject;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Random;

// 'CustomView' - берем все обычные методы (скелет) и накидываем на них что-то свое
// 'View' - это наш кастомный view
public class PaintView extends View {

    // массив лний
    ArrayList<Stroke> strokes = new ArrayList<>();

    // текущий штрих
    Stroke currentStroke;

    // текущий цвет
    int currentColor = Color.BLACK;

    // толщина линии
    float strokeWidth = 10f;

    // переопределяем кон-р
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    // далее переопределяем методы, которые будут работать с вьюшкой
    // 1
    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        for (Stroke stroke:strokes){
            // как будет наш путь рисоваться и какой кисточкой
            canvas.drawPath(stroke.path,stroke.paint);
        }
    }

    // 2
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                currentStroke = new Stroke(currentColor,strokeWidth);
                currentStroke.path.moveTo(x,y);
                strokes.add(currentStroke);
                return true;
            case MotionEvent.ACTION_MOVE:
                if (currentStroke != null) {
                    currentStroke.path.lineTo(x,y);
                }
                break;
            case  MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }

    // метод очистки холста
    public void clearCanvas(){
        strokes.clear();
        // перерисовать холст
        invalidate();
    }

    // увеличение толщины линии
    public void biggerWidth(Button btn){
        if (strokeWidth != 30) {
            strokeWidth += 1;
        }
    }

    // уменьшение толщины линии
    public void smallerWidth(Button btn){
        if (strokeWidth != 1) {
            strokeWidth -= 1;
        }
    }

    // смена цвета
    public void changeColor(Button btn){
        Random random = new Random();
        currentColor = Color.rgb(random.nextInt(256),random.nextInt(256),random.nextInt(256));
        btn.setBackgroundColor(currentColor);
    }

    @SuppressLint("WrongThread")
    public void saveToGallery(Context context){

        // 'Bitmap' - класс, который работает с пикселями
        // создаем еще один холст, на который мы будем переносить изображение с нашего текущего холста
        // но при этом этот холст будет уже работать с форматом изображения
        Bitmap bitmap = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        draw(canvas);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.DISPLAY_NAME,"drawing_"+System.currentTimeMillis()+".png");
        values.put(MediaStore.Images.Media.MIME_TYPE,"image/png");
        values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES+"/MyDrawings");

        ContentResolver resolver = context.getContentResolver();

        try {
            Uri uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY);
            Uri imageUri = resolver.insert(uri,values);
            if (imageUri != null) {
                try (OutputStream outputStream = resolver.openOutputStream(imageUri)){
                    bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                }
            }
            Toast.makeText(context, "Сохранено в галерее", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Ошибка сохранения", Toast.LENGTH_SHORT).show();
        }
    }
}