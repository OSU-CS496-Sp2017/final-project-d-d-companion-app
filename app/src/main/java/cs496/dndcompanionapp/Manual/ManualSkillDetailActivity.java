package cs496.dndcompanionapp.Manual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import cs496.dndcompanionapp.DnDApi;
import cs496.dndcompanionapp.R;
import cs496.dndcompanionapp.SettingsActivity;
import cs496.dndcompanionapp.models.Skill;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by G on 6/16/2017.
 */

public class ManualSkillDetailActivity extends AppCompatActivity {
    private DnDApi.DnDApiService dndApi;
    private TextView name;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_skill_detail);

        name = (TextView) findViewById(R.id.skillName);



        Intent intent = getIntent();
        if(intent != null) {
            String skillId = intent.getStringExtra("classId");

            dndApi = new DnDApi().createService();
            Call<Skill> call = dndApi.getCharacterSkill(skillId);
            call.enqueue(new Callback<Skill>() {
                @Override
                public void onResponse(Call<Skill> call, Response<Skill> response) {
                    name.setText(response.body().name);
                }

                @Override
                public void onFailure(Call<Skill> call, Throwable t) {

                }
            });
        }
        getIntent().setAction("Already created"); //important for navigation
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
            Intent intent = new Intent(this, ManualSkillDetailActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }
}
