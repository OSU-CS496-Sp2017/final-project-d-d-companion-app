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
import cs496.dndcompanionapp.models.CharacterClass;
import cs496.dndcompanionapp.models.CharacterSubrace;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by brandon on 6/16/2017.
 */

public class ManualSubraceDetailActivity extends AppCompatActivity {

    private DnDApi.DnDApiService dndApi;
    private TextView name;
    private TextView hitDie;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_subrace_detail);

        name = (TextView) findViewById(R.id.subraceName);


        Intent intent = getIntent();
        if (intent != null) {
            String subraceId = intent.getStringExtra("classId");

            dndApi = new DnDApi().createService();
            Call<CharacterSubrace> call = dndApi.getCharacterSubraces(subraceId);
            call.enqueue(new Callback<CharacterSubrace>() {
                @Override
                public void onResponse(Call<CharacterSubrace> call, Response<CharacterSubrace> response) {
                    name.setText(response.body().name);
                }

                @Override
                public void onFailure(Call<CharacterSubrace> call, Throwable t) {

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

    public void changeTheme(String theme) {
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
    }

    @Override
    protected void onResume() {
        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if (action == null || !action.equals("Already created")) {
            Log.v("Example", "Force restart");
            Intent intent = new Intent(this, ManualSubraceDetailActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }


}
