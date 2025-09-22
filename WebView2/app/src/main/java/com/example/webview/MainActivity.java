package com.example.webview;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    // класс, используемый при работе с сетью - проверяет, есть ли сеть
    ConnectivityManager connectivityManager;
    // класс, который будет показываться при отсутствии интернета
    AlertDialog alertDialog;
    TextView logTextView;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        logTextView = findViewById(R.id.logTextView);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // создаем менеджер для работы с интернетом
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        // создаем диалог по умолчанию, но сразу не показываем
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Нет интернета")
                .setMessage("проверьте соединение")
                // нельзя будет выключить
                .setCancelable(false)
                .create();

        // зарегистрируем callback, чтобы он чекал состояние сети
        connectivityManager.registerNetworkCallback(new NetworkRequest.Builder().build(),
                new ConnectivityManager.NetworkCallback() {
                // здесь нам нужно переопределить 2 метода
                    // 1 // интернет появился
                    @Override
                    public void onAvailable(@NonNull Network network) {
                        // если диалог открыт - мы его закрываем
                        runOnUiThread(()->{
                            // определим тип сети (вай фай или мобильная)
                            String type = getNetworkType(network);
                            new Thread(()->{
                                boolean realInternet = checkInternetAccess();

                                runOnUiThread(()-> {
                                    if (realInternet) {
                                        // поставим доп проверку реального интернета (в отдельном потоке)
                                        if (alertDialog.isShowing()) {
                                            alertDialog.dismiss();
                                        }
                                        appendLog("Интернет появился ("+type+")");
                                    } else {
                                        // если диалог еще не открыт - мы его открываем
                                        if (!alertDialog.isShowing()) {
                                            alertDialog.show();
                                        }
                                        appendLog("Сеть "+type+" сеть подключена, но без доступа в интернет");
                                    }
                                });
                            }).start();
                        });
                    }

                    // 2 // интернет пропал
                    @Override
                    public void onLost(@NonNull Network network) {

                        runOnUiThread(()->{
                            // если диалог еще не открыт - мы его открываем
                            if (!alertDialog.isShowing()) {
                                alertDialog.show();
                            }
                            appendLog("Интернет пропал!");
                        });
                    }
                });
    }

    // поиграемся с жизненным циклом - чтобы не было утечек
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (connectivityManager != null) {
            // закрываем
            connectivityManager.unregisterNetworkCallback(new ConnectivityManager.NetworkCallback());
        }
    }

    // метод, который будет определять тип сети (вай фай или мобильная)
    private String getNetworkType (Network network) {
        NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(network);
        if (capabilities == null) {
            return "неизвестно";
        }
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
            return "WiFi";
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            return "Мобильный интернет";
        } else {
            return "Другая сеть";
        }
    }

    // метод, который будет писать текст в TextView
    private void appendLog (String message) {
        // берем текущее время
        String time = dateFormat.format(new Date());
        logTextView.append("\n"+time+" - "+message);
    }

    // метод, который будет писать текст в TextView
    // проверка пингом через сайт
    private boolean checkInternetAccess () {
        try {
            // сделаем запрос
            URL url = new URL("http://www.google.com");
            HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
            urlc.setConnectTimeout(2000);
            urlc.connect();
            return (urlc.getResponseCode()==200);
        } catch (Exception ex) {
            return false;
        }
    }

    // проверка через сокет Cloudflare DNS
    private boolean checkInternetBySocket () {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("1.1.1.1", 53), 1500);
            socket.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}