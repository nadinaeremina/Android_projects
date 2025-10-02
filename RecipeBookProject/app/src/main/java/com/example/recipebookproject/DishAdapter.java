package com.example.recipebookproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.ViewHolder> {

    interface OnStateClickListener {
        void onStateClick (Dish dish, int position);
    }

    OnStateClickListener onClickListener;
    LayoutInflater inflater;
    List<Dish> dishes;

    public DishAdapter(Context context, List<Dish> dishes, OnStateClickListener onClickListener) {
        this.dishes = dishes;
        this.inflater = LayoutInflater.from(context);
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish dish = dishes.get(position);
        holder.flagView.setImageResource(dish.getFlagResource());
        holder.nameView.setText(dish.getName());
        holder.referenceView.setText(dish.getReference());
        holder.webView.loadUrl("https://www.russianfood.com/recipes/bytype/?fid=721");

        // в 'webview' нужно включить 'JS'
        WebSettings webSettings = holder.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // чтобы ссылки открывались внутри приложения
        holder.webView.setWebViewClient(new WebViewClient());

        //holder.webView.setWebViewClient(new WebViewClient() {
            //@Override
            //public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //view.loadUrl(url); // Загрузить URL в текущий WebView
                //return true; // Сообщить WebView, что мы обработали ссылку
            //}
        //});

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onStateClick(dish, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dishes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView flagView;
        TextView nameView, referenceView;
        WebView webView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            flagView = itemView.findViewById(R.id.flag);
            nameView = itemView.findViewById(R.id.name);
            referenceView = itemView.findViewById(R.id.reference);
            webView = itemView.findViewById(R.id.webview);
        }
    }
}
