package cs496.dndcompanionapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

/**
 * Created by brandon on 6/4/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Log.d("TAG","In onCreatePreferences");
        addPreferencesFromResource(R.xml.prefs);
        Log.d("TAG","After addPreferencesFromResource");
        Log.d("TAG","Name of theme_key:  "+ getString(R.string.theme_key));
        Log.d("TAG","Found theme! " +  findPreference("theme"));

        Log.d("TAG","After editTextPref");


        //addPreferencesFromResource(R.xml.prefs);




        //(EditTextPreference) findPreference("theme");



      // userPref.setSummary("theme");
        Log.d("TAG","After setSummary");
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
            Log.d("TAG","In onSharedPreferenceChanged");
        }
    }
}