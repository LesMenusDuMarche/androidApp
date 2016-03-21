package fr.lesmenusdumarche.lesmenusdumarche.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.lesmenusdumarche.lesmenusdumarche.R;
import fr.lesmenusdumarche.lesmenusdumarche.cache.MarketCacher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MarketCacher marketCacher = new MarketCacher();
        marketCacher.cacheFromRest();
    }
}