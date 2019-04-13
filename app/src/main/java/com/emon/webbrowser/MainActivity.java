package com.emon.webbrowser;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    EditText editText;
    Button button;
    String url;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.webEt);
        button=findViewById(R.id.btn);
        webView=findViewById(R.id.webViewid);
        webView.setWebViewClient(new browser(){
                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         pd.dismiss();
                                     }
                                 }

        );

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pd=new ProgressDialog(MainActivity.this);
                pd.setTitle("Please wait");
                pd.setMessage("Loading....");
                pd.show();

                url=editText.getText().toString();
                webView.loadUrl("http://"+url);
                webView.getSettings().setJavaScriptEnabled(true);
                //  Toast.makeText(Main2Activity.this, "loding", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

        if (webView.canGoBack()){
            webView.goBack();
        }else {
            new AlertDialog.Builder(this)
                    .setTitle("Exit:")
                    .setMessage("Do You Want To Exit..")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    }).setNegativeButton("No",null)

                    .show();
        }
    }
    public class browser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


}
