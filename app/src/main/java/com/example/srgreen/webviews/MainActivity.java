package com.example.srgreen.webviews;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    WebView navegador;
    ProgressDialog prgDialog;
    Button btnStop, btnPrevious, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navegador = (WebView) findViewById(R.id.webkit);
        navegador.getSettings().setJavaScriptEnabled(true);
        navegador.loadUrl("http://www.7towns4europe.eu");

        prgDialog = new ProgressDialog(this);
        prgDialog.setMessage("Downloading the web page. Please wait...");

        //Improved buttons
        navegador.setWebViewClient(new WebViewClient() {
            public boolean shouldOverriderUrlLoading (WebView view, String url) {
                return false;
            }
            public void onPageStarted (WebView view, String url, Bitmap favicon) {
                prgDialog.show();
                btnStop.setEnabled(true);
            }
            public void onPageFinished (WebView view, String url) {
                prgDialog.hide();
                btnStop.setEnabled(false);
                if (view.canGoBack()) {
                    btnPrevious.setEnabled(true);
                }
                else {
                    btnPrevious.setEnabled(false);
                }
                if (view.canGoForward()) {
                    btnNext.setEnabled(true);
                } else {
                    btnNext.setEnabled(false);
                }
            }
        });
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnNext = (Button) findViewById(R.id.btnNext);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    public void stopCarregaPagina(View view) {
        navegador.stopLoading();
    }

    public void goPreviousPage(View view) {
        navegador.goBack();
    }

    public void goNextPage(View view) {
        navegador.goForward();
    }
}
