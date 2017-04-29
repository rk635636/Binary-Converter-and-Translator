package com.example.androidworld.ed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class Welcome_Screen extends AppCompatActivity implements View.OnClickListener{

    Button binary,hex,url,base64;
    InterstitialAd mInterstitialAd;
    TextView textView;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);

        getSupportActionBar().setLogo(R.drawable.images);
        getSupportActionBar().setTitle("Binary Converter & Translator");


        binary = (Button)findViewById(R.id.binary);
        binary.setOnClickListener(this);
        hex = (Button)findViewById(R.id.hex);
        hex.setOnClickListener(this);
        url = (Button)findViewById(R.id.more);
        url.setOnClickListener(this);
        base64 = (Button)findViewById(R.id.Base64);
        base64.setOnClickListener(this);
        adView = (AdView)findViewById(R.id.adView);

        mInterstitialAd = new InterstitialAd(this);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("F93A998E24A406E6E77CB93AD3A4862E")
                .build();
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));


        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);
        adView.loadAd(adRequest);


    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdClosed() {
                    super.onAdClosed();
                    finish();
                }
            });
        }else{
            super.onBackPressed();
        }
//F93A998E24A406E6E77CB93AD3A4862E

    }
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.binary:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("arg", 1); // getText() SHOULD NOT be static!!!
                startActivity(intent);
                break;
            case R.id.hex:
                Intent intent1 = new Intent(this, MainActivity.class);
                intent1.putExtra("arg", 2); // getText() SHOULD NOT be static!!!
                startActivity(intent1);
                break;
            case R.id.Base64:
                Intent intent3 = new Intent(this, MainActivity.class);
                intent3.putExtra("arg", 4); // getText() SHOULD NOT be static!!!
                startActivity(intent3);
                break;
            case R.id.more:
                startActivity(new Intent(this,More.class));
                break;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_welcome, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {


            case R.id.share:
                Intent i=new Intent(android.content.Intent.ACTION_SEND);

                i.setType("text/plain");
                i.putExtra(android.content.Intent.EXTRA_SUBJECT,"Subject test");
                i.putExtra(android.content.Intent.EXTRA_TEXT, "extra text that you want to put https://play.google.com/store/apps/details?id=com.example.rushikesh.ed");
                startActivity(Intent.createChooser(i,"Share via"));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
