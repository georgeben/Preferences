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
        showTextPref(sharedPreferences);

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    private void showTextPref(SharedPreferences sharedPreferences){
        if(sharedPreferences.getBoolean(getResources().getString(R.string.pref_show_text_key), getResources().getBoolean(R.bool.pref_show_text_default))){
            rootLayout.setVisibility(View.VISIBLE);
        }else{
            rootLayout.setVisibility(View.INVISIBLE);
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
            showTextPref(sharedPreferences);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
