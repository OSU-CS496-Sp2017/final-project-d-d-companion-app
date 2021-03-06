package cs496.dndcompanionapp.Manual;

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

import cs496.dndcompanionapp.MainActivity;
import cs496.dndcompanionapp.R;
import cs496.dndcompanionapp.SettingsActivity;

/**
 * Created by brandon on 6/16/2017.
 */

public class ManualActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_manual_activity);

        Button classes = (Button) findViewById(R.id.btnViewClassInfo);
        Button subclasses = (Button) findViewById(R.id.btnViewSubclassInfo);
        Button races = (Button) findViewById(R.id.btnViewRaceInfo);
        Button subraces= (Button) findViewById(R.id.btnViewSubraceInfo);
        Button skills = (Button) findViewById(R.id.btnViewSkillInfo);
        Button spells = (Button) findViewById(R.id.btnViewSpellInfo);
        classes.setOnClickListener(this);
        subclasses.setOnClickListener(this);
        races.setOnClickListener(this);
        subraces.setOnClickListener(this);
        skills.setOnClickListener(this);
        spells.setOnClickListener(this);
        getIntent().setAction("Already created"); //important for navigation
    }
        @Override
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.btnViewClassInfo:
                    startActivity(new Intent(ManualActivity.this, ManualClassActivity.class));
                    break;
                case R.id.btnViewSubclassInfo:
                    startActivity(new Intent(ManualActivity.this, ManualSubclassActivity.class));
                    break;
                case R.id.btnViewRaceInfo:
                    startActivity(new Intent(ManualActivity.this, ManualRaceActivity.class));
                    break;
                case R.id.btnViewSubraceInfo:
                    startActivity(new Intent(ManualActivity.this, ManualSubraceActivity.class));
                    break;
                case R.id.btnViewSkillInfo:
                    startActivity(new Intent(ManualActivity.this, ManualSkillActivity.class));
                    break;
                case R.id.btnViewSpellInfo:
                    startActivity(new Intent(ManualActivity.this, ManualSpellActivity.class));
                    break;
/*
                case R.id.goToManual:
                    startActivity(new Intent(MainActivity.this, ManualActivity.class));
                    break;
*/
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

    public void changeTheme(String theme){
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
    }
    @Override
    protected void onResume() {
               String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Log.v("Example", "Force restart");
            Intent intent = new Intent(this, ManualActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }

}
