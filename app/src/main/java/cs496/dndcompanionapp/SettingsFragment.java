package cs496.dndcompanionapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

import static cs496.dndcompanionapp.R.attr.colorPrimaryDark;

/**
 * Created by brandon on 6/4/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

        Log.d("TAG","In onCreatePreferences");
        addPreferencesFromResource(R.xml.prefs);
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("TAG","In onResume");
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(getString(R.string.theme_key))) {
            Log.d("TAG", "Value of key:  "+ getString(R.string.theme_key));
            Log.d("TAG","In onSharedPreferenceChanged");
            run(); //recreates activity after change


        }
    }

    public void run() {
        Intent intent = getActivity().getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().overridePendingTransition(0, 0);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }

}