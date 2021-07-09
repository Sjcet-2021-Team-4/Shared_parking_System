package com.aumento.sharedparking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aumento.sharedparking.utils.GlobalPreference;

public class PaymentGatewayActivity extends AppCompatActivity {

    private WebView paymentGatewayWV;
    private GlobalPreference globalPreference;
    private String ip;
    private String uid;
    private Intent intent;
    private String pid, vehicleType, vehicleNumber, bdate, duration, amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_gateway);

        globalPreference = new GlobalPreference(this);
        ip = globalPreference.RetriveIP();
        uid = globalPreference.RetriveUID();
        Intent intent = getIntent();
        pid = intent.getStringExtra("pid");
        String bid = intent.getStringExtra("bid");
//        String lid = intent.getStringExtra("lid");
        String lid = "";
        vehicleType = intent.getStringExtra("vehicleType");
        vehicleNumber = intent.getStringExtra("vehicleNumber");
        bdate = intent.getStringExtra("bdate");
        duration = intent.getStringExtra("duration");
        amount = intent.getStringExtra("amount");


        paymentGatewayWV = findViewById(R.id.paymentgatewayWebView);
        paymentGatewayWV.clearCache(true);
        paymentGatewayWV.clearHistory();
        paymentGatewayWV.getSettings().setJavaScriptEnabled(true);
        paymentGatewayWV.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        paymentGatewayWV.loadUrl("http://"+ip+ "/shared_parking/API/payment/sec.php" +"?ip="+ip+"&uid="+uid+"&pid="+pid+"&vehicleType="+vehicleType+"&vehicleNumber="+vehicleNumber+"&bdate="+bdate+"&duration="+duration+"&amount="+amount);
        paymentGatewayWV.loadUrl("http://"+ip+ "/shared_parking/API/payment/First.php" +"?ip="+ip+"&uid="+uid+"&pid="+pid+"&bid="+bid+"&lid="+lid+"&vehicleType="+vehicleType+"&bdate="+bdate+"&duration="+duration+"&amount="+amount);

        paymentGatewayWV.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                Log.d("WebView", "your current url when webpage loading.." + url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("WebView", "your current url when webpage loading.. finish" + url);
                if(url.contains("complete.php"))
                {
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 1000);
                }

                super.onPageFinished(view, url);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onLoadResource(view, url);
            }
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("when you click on any interlink on webview that time you got url :-" + url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }
}