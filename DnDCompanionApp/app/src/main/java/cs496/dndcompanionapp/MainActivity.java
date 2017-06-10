package cs496.dndcompanionapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost host = (TabHost)findViewById(R.id.characterTabHost);
        host.setup();


        //allignment layout
        Spinner alignmentSpinner = (Spinner) findViewById(R.id.alignmentSpinner);
        ArrayAdapter<CharSequence> alignmentAdapter = ArrayAdapter.createFromResource(this, R.array.alignmentArray, android.R.layout.simple_spinner_item);
        alignmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        alignmentSpinner.setAdapter(alignmentAdapter);

        //background layout
        Spinner backgroundSpinner = (Spinner) findViewById(R.id.backgroundSpinner);
        ArrayAdapter<CharSequence> backgroundAdapter = ArrayAdapter.createFromResource(this, R.array.backgroundPlaceholder, android.R.layout.simple_spinner_item);
        backgroundAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        backgroundSpinner.setAdapter(backgroundAdapter);

        //

        //Background tab
        TabHost.TabSpec spec = host.newTabSpec("Background");
        spec.setContent(R.id.background);
        spec.setIndicator("Background");
        host.addTab(spec);

        //Race and Class tab
        spec = host.newTabSpec("Race and Class");
        spec.setContent(R.id.RaceClass);
        spec.setIndicator("Race and Class");
        host.addTab(spec);

        //Stats tab
        spec = host.newTabSpec("Stats");
        spec.setContent(R.id.Stats);
        spec.setIndicator("Stats");
        host.addTab(spec);

        //Items tab
        spec = host.newTabSpec("Items");
        spec.setContent(R.id.Items);
        spec.setIndicator("Items");
        host.addTab(spec);

        //Feats and Spells tab
        spec = host.newTabSpec("Feats and Spells");
        spec.setContent(R.id.FeatsAndSpells);
        spec.setIndicator("Feats and Spells");
        host.addTab(spec);


    }
}
