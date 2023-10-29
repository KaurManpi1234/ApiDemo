package com.example.apidemo;




import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.annotation.SuppressLint;
import android.os.Bundle;




public class MainActivity extends AppCompatActivity {


    public static final String API_KEY ="z5hrIaovsbrqYH3QqpfWPzS4sx1d3PIFqzNKAJdf" ;
    private ViewPager viewPager;


    private SearchRoverFragmentAdapter adapter;


    @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
             super.onCreate(savedInstanceState);
             setContentView(R.layout.activity_main);
        setupFragment();
    }


    private void setupFragment(){

        if(this.viewPager == null) {

            this.viewPager = findViewById(R.id.viewPager);
            this.adapter = new SearchRoverFragmentAdapter(getSupportFragmentManager());

            this.viewPager.setAdapter(this.adapter);

        }

    }
    }
