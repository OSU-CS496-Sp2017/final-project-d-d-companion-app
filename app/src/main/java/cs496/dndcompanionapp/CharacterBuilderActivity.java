package cs496.dndcompanionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TabHost;

/**
 * Created by Sanlador on 6/10/2017.
 */

public class CharacterBuilderActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private EditText charName;
    private EditText charGender;
    private EditText charDescription;
    private EditText charPersonality;
    public String name;
    public String Class;
    public int buyPoints;
    public int lvlPoints;
    public int str;
    public int con;
    public int dex;
    public int wis;
    public int intel;
    public int cha;
    public String[] prof = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.character_builder_activity);

        TabHost host = (TabHost)findViewById(R.id.characterTabHost);
        host.setup();

        //alignment layout
        setSpinner((Spinner) findViewById(R.id.alignmentSpinner), R.array.alignmentArray);
        //background layout
        setSpinner((Spinner) findViewById(R.id.backgroundSpinner), R.array.backgroundPlaceholder);

        //Ready tabs
        setTab(host, "Background", R.id.background);
        setTab(host, "Class and Race", R.id.classRace);
        setTab(host, "Stats", R.id.stats);


        charName = (EditText)findViewById(R.id.editName);
        charGender = (EditText)findViewById((R.id.editGender));
        charDescription = (EditText)findViewById(R.id.editDescription);
        charPersonality = (EditText)findViewById(R.id.editPersonality);

        Button charBuilder = (Button)findViewById(R.id.prevBackground);

        charBuilder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });

        Button getData = (Button)findViewById(R.id.nextBackground);
        getData.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }

        });

    }

    @Override
    protected void onDestroy()
    {

        super.onDestroy();
    }

    //readies a new spinner
    public void setSpinner(Spinner spinner, int id)
    {
        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this, id, android.R.layout.simple_spinner_item);
        Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(Adapter);
    }
    //readies a new tab
    public void setTab(TabHost host, String name, int id)
    {
        TabHost.TabSpec spec = host.newTabSpec(name);
        spec.setContent(id);
        spec.setIndicator(name);
        host.addTab(spec);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        name = sharedPreferences.getString(getString(R.string.pref_name_key), "Bardicus");
        Class = sharedPreferences.getString(getString(R.string.pref_class_key), getString(R.string.pref_class_default));
        charName.setText(name);

        Log.d("Test", name);
    }

}
