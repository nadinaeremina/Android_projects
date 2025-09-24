package com.example.paintproject;

import android.graphics.Paint;
import android.graphics.Path;

public class Stroke {

    // класс 'Path' описывает геометрию пути (фигуру)
    Path path;

    // класс 'Paint' описывает стиль и цвет рисования
    // как будет выглядеть наша кисть
    Paint paint;

    // передаем цвет и толщину строки
    public Stroke(int color, float strokeWidth) {
        path = new Path();
        paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        // сглаживание
        paint.setAntiAlias(true);
    }
}
