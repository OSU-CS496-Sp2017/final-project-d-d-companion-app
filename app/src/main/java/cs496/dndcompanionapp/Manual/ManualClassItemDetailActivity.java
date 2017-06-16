package cs496.dndcompanionapp.Manual;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs496.dndcompanionapp.DnDApi;
import cs496.dndcompanionapp.R;
import cs496.dndcompanionapp.SettingsActivity;
import cs496.dndcompanionapp.models.CharacterClass;
import cs496.dndcompanionapp.models.Equipment;
import cs496.dndcompanionapp.models.Proficiency;
import cs496.dndcompanionapp.models.ProficiencyResultItem;
import cs496.dndcompanionapp.models.SavingThrowResultItem;
import cs496.dndcompanionapp.models.StartingEquipment;
import cs496.dndcompanionapp.models.StartingEquipmentItem;
import cs496.dndcompanionapp.models.StartingEquipmentResultItem;
import cs496.dndcompanionapp.models.SubclassResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Liv on 6/16/2017.
 */

public class ManualClassItemDetailActivity extends AppCompatActivity {
    private DnDApi.DnDApiService dndApi;
    private TextView name;
    private TextView hitDie;

    private CharacterClass cls;
    List<Proficiency> proficiencies = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_class_item_detail);

        name = (TextView) findViewById(R.id.name);
        hitDie = (TextView) findViewById(R.id.hit_die);

        Intent intent = getIntent();
        if(intent != null) {
            String classId = intent.getStringExtra("classId");

            dndApi = new DnDApi().createService();
            Call<CharacterClass> call = dndApi.getCharacterClass(classId);
            call.enqueue(new Callback<CharacterClass>() {
                @Override
                public void onResponse(Call<CharacterClass> call, Response<CharacterClass> response) {
                    cls = response.body();

                    // Basic Class Info
                    name.setText(cls.name);
                    Log.d("TEST", Integer.toString(cls.hitDie));
                    hitDie.setText(Integer.toString(cls.hitDie));

                    // Proficiencies
                    getProficiencies();

                    // Saving Throws
                    for (SavingThrowResultItem savingThrow : cls.savingThrows) {
                        TextView tv = new TextView(ManualClassItemDetailActivity.this);
                        tv.setText(savingThrow.name);
                        LinearLayout ll = (LinearLayout) findViewById(R.id.saving_throws);
                        ll.addView(tv);
                    }

                    // Subclasses
                    for (SubclassResultItem subclass : cls.subclasses) {
                        TextView tv = new TextView(ManualClassItemDetailActivity.this);
                        tv.setText(subclass.name);
                        LinearLayout ll = (LinearLayout) findViewById(R.id.subclasses);
                        ll.addView(tv);
                    }

                    // Starting Equipment
                    getStartingEquipment();
                }

                @Override
                public void onFailure(Call<CharacterClass> call, Throwable t) {

                }
            });
        }
        getIntent().setAction("Already created"); //important for navigation
    }

    public void getStartingEquipment() {
        Call<StartingEquipment> call = dndApi.getCharacterStartingEquipment(cls.index);

        call.enqueue(new Callback<StartingEquipment>() {
            @Override
            public void onResponse(Call<StartingEquipment> call, Response<StartingEquipment> response) {
                // Get individual equipment
                if (response.body() != null) {
//                    Log.d("TEST", "EQUIPMENT LENGTH: ");
//                    Log.d("TEST", Integer.toString(response.body().startingEquipment.size()));
                    for (StartingEquipmentResultItem startingEquipmentResultItem : response.body().startingEquipment) {
                        String itemId = startingEquipmentResultItem.item.url.split("http://www.dnd5eapi.co/api/equipment/")[1];
                        getStartingEquipmentItem(itemId);
                    }
                }
            }

            @Override
            public void onFailure(Call<StartingEquipment> call, Throwable t) {

            }
        });
    }

    public void getStartingEquipmentItem(String id) {
        Call<Equipment> call = dndApi.getEquipment(id);

        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment> call, Response<Equipment> response) {
                if(response.body() != null) {
                    Equipment equip = response.body();
                    LinearLayout ll = (LinearLayout) findViewById(R.id.equipment);
                    TextView tv = new TextView(ManualClassItemDetailActivity.this);
                    tv.setText(equip.name);
                    ll.addView(tv);
                }
            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {

            }
        });
    }

    public void getProficiencies() {
        for (ProficiencyResultItem proficiency : cls.proficiencies) {
            String proficiencyId = proficiency.url.split("http://www.dnd5eapi.co/api/proficiencies/")[1];
            Call<Proficiency> call = dndApi.getCharacterProficiency(proficiencyId);

            call.enqueue(new Callback<Proficiency>() {
                @Override
                public void onResponse(Call<Proficiency> call, Response<Proficiency> response) {
                    if (response.body() != null) {
                        Proficiency prof = response.body();
                        proficiencies.add(prof);
                        TextView tv = new TextView(ManualClassItemDetailActivity.this);
                        tv.setText(prof.name);
                        LinearLayout ll = (LinearLayout) findViewById(R.id.proficiencies);
                        ll.addView(tv);
                    }
                }

                @Override
                public void onFailure(Call<Proficiency> call, Throwable t) {

                }
            });
        }
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
            Intent intent = new Intent(this, ManualClassItemDetailActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }
}
