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

import java.util.List;

import cs496.dndcompanionapp.DnDApi;
import cs496.dndcompanionapp.R;
import cs496.dndcompanionapp.SettingsActivity;
import cs496.dndcompanionapp.models.CharacterSpell;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by brandon on 6/16/2017.
 */

public class ManualSpellDetailActivity extends AppCompatActivity {
    private DnDApi.DnDApiService dndApi;
    private TextView name;
    private TextView desc;
    private TextView highLevel;
    private TextView material;
    private TextView components;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_spell_detail);

        name = (TextView) findViewById(R.id.name);
        desc = (TextView) findViewById(R.id.spellDesc);
        highLevel = (TextView) findViewById(R.id.higherLevel);
        material = (TextView)findViewById(R.id.materials);
        components = (TextView)findViewById(R.id.components);

        Intent intent = getIntent();
        if(intent != null) {
            String spellId = intent.getStringExtra("spellId");

            dndApi = new DnDApi().createService();
            Call<CharacterSpell> call = dndApi.getCharacterSpell(spellId);
            call.enqueue(new Callback<CharacterSpell>() {
                @Override
                public void onResponse(Call<CharacterSpell> call, Response<CharacterSpell> response) {
                    Log.d("TEST", response.body().name);
                    name.setText(response.body().name);
                    String d = writeLong(response.body().desc);
                    desc.setText((d));
                    d = writeLong(response.body().highLevel);
                    highLevel.setText(d);
                    if (response.body().material == null)
                        material.setText("");
                    else
                        material.setText("Materials:\n" + response.body().material);
                    d = "Components:\n";
                    for (int i = 0; i < response.body().components.size(); i++)
                        d += "- " + response.body().components.get(i) + "\n";
                    components.setText(d);
                }

                @Override
                public void onFailure(Call<CharacterSpell> call, Throwable t) {

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
            Intent intent = new Intent(this, ManualSpellDetailActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }

    public String writeLong(List<String> words)
    {

        String temp = "";
        for (int i = 0; i < words.size(); i++)
        {
            temp += words.get(i) + " ";
        }
        return temp;
    }
}
