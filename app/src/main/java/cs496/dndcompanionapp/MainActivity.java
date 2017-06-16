package cs496.dndcompanionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //setTheme(R.style.Barbarian);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String theme =
                sharedPreferences.getString(
                        getString(R.string.theme_key),
                        getString(R.string.theme_default)
                );
        changeTheme(theme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button charBuilder = (Button)findViewById(R.id.goToChar);

        charBuilder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CharacterBuilderActivity.class));
            }
        });
        // Below is for settings/theme/preference
        //android.preference.PreferenceManager.setDefaultValues(getBaseContext(),R.xml.prefs,false);
        //String theme = SharedPreferences.getString("theme","theme");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        //run();

    }
    public void run() {
        Intent intent =getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
       overridePendingTransition(0, 0);
      finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    public void changeTheme(String theme){
        Log.d("TAG","Value of units in changeTheme:  " + theme);
        switch (theme) {
            case "Bard":
                setTheme(R.style.Bard);
                break;
            case "Barbarian":
                setTheme(R.style.Barbarian);
                break;
            case "Cleric":
                //setTheme();
                break;
            case "Druid":
                //setTheme();
                break;
            case "Fighter":
                //setTheme();
                break;
            case "Monk":
                //setTheme();
                break;
            case "Paladin":
                //setTheme();
                break;
            case "Ranger":
                //setTheme();
                break;
            case "Rogue":
                //setTheme();
                break;
            case "Sorcerer":
                //setTheme();
                break;
            case "Warlock":
                //setTheme();
                break;
            case "Wizard":
                //setTheme();
                break;
            default:
                break;
        }
    }
}


