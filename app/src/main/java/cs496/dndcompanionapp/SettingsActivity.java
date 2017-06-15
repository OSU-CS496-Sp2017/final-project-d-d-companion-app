package cs496.dndcompanionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import cs496.dndcompanionapp.R;


import cs496.dndcompanionapp.MainActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
