package com.kurobarabenjamingeorge.preferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private LinearLayout rootLayout;
    private TextView heading, paragraph_one, paragraph_two, source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = (LinearLayout) findViewById(R.id.layout);
        heading = (TextView) findViewById(R.id.article_title);
        paragraph_one = (TextView) findViewById(R.id.article_content_one);
        paragraph_two = (TextView) findViewById(R.id.article_content_two);
        source = (TextView) findViewById(R.id.sources);

        setUpSharedPreferences();
    }

    private void setUpSharedPreferences() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        loadTextPref(sharedPreferences);
        loadTitlePref(sharedPreferences);
        loadColourPref(sharedPreferences);
        loadBgColourPref(sharedPreferences);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    private void loadTextPref(SharedPreferences sharedPreferences){
        if(sharedPreferences.getBoolean(getResources().getString(R.string.pref_show_text_key), getResources().getBoolean(R.bool.pref_show_text_default))){
            rootLayout.setVisibility(View.VISIBLE);
        }else{
            rootLayout.setVisibility(View.INVISIBLE);
        }
    }

    private void loadTitlePref(SharedPreferences sharedPreferences){
        if(sharedPreferences.getBoolean(getString(R.string.pref_show_title_key), getResources().getBoolean(R.bool.pref_show_title_default))){
            heading.setVisibility(View.VISIBLE);
        }else{
            heading.setVisibility(View.INVISIBLE);
        }
    }

    private void setTextColour(int colour){
        heading.setTextColor(colour);
        paragraph_one.setTextColor(colour);
        paragraph_two.setTextColor(colour);
    }

    private void loadColourPref(SharedPreferences sharedPreferences){
        String colour = sharedPreferences.getString(getString(R.string.pref_colour_key), getString(R.string.pref_colour_default));
        if(colour.equals(getString(R.string.pref_colour_red))){
        setTextColour(getResources().getColor(R.color.red));

        }else if(colour.equals(getString(R.string.pref_colour_blue))){
            setTextColour(getResources().getColor(R.color.blue));

        }else if(colour.equals(getString(R.string.pref_colour_green))){
            setTextColour(getResources().getColor(R.color.green));

        }else if(colour.equals(getString(R.string.pref_colour_black))){
            setTextColour(getResources().getColor(R.color.black));

        }else if(colour.equals(getString(R.string.pref_colour_white))){
            setTextColour(getResources().getColor(R.color.white));
        }
    }

    private void loadBgColourPref(SharedPreferences sharedPreferences){
        String colour = sharedPreferences.getString(getString(R.string.pref_bg_colour_key), getString(R.string.pref_colour_default));
        if(colour.equals(getString(R.string.pref_colour_red))){
            rootLayout.setBackgroundColor(getResources().getColor(R.color.red));

        }else if(colour.equals(getString(R.string.pref_colour_blue))){
            rootLayout.setBackgroundColor(getResources().getColor(R.color.blue));

        }else if(colour.equals(getString(R.string.pref_colour_green))){
            rootLayout.setBackgroundColor(getResources().getColor(R.color.green));

        }else if(colour.equals(getString(R.string.pref_colour_black))){
            rootLayout.setBackgroundColor(getResources().getColor(R.color.black));

        }else if(colour.equals(getString(R.string.pref_colour_white))){
            rootLayout.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(getString(R.string.pref_show_text_key))){
            loadTextPref(sharedPreferences);
        }else if(s.equals(getString(R.string.pref_show_title_key))){
            loadTitlePref(sharedPreferences);
        }else if(s.equals(getString(R.string.pref_colour_key))){
            loadColourPref(sharedPreferences);
        }else if(s.equals(getString(R.string.pref_bg_colour_key))){
            loadBgColourPref(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
