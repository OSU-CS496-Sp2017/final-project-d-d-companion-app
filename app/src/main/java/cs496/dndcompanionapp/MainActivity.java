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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TabHost tabHost;



    @Override
    protected void onCreate(Bundle savedInstanceState){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button charBuilder = (Button)findViewById(R.id.goToChar);
        Button Manual = (Button)findViewById(R.id.goToManual);
        charBuilder.setOnClickListener(this);
        Manual.setOnClickListener(this);
        getIntent().setAction("Already created"); //important for navigation


    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.goToChar:
                startActivity(new Intent(MainActivity.this, MonsterGenActivity.class));
                break;

            case R.id.goToManual:
                startActivity(new Intent(MainActivity.this, ManualActivity.class));
                break;

            default:
                break;
        }
        // default method for handling onClick Events..
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
    protected void onResume() {
        Log.v("Example", "onResume");

        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Log.v("Example", "Force restart");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }

    public void changeTheme(String theme){
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
    }
    @Override
    public void onBackPressed() {
      //prevents user from backing out of back
    }

}


