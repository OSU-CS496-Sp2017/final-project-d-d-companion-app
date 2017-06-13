package cs496.dndcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TabHost;

/**
 * Created by Sanlador on 6/10/2017.
 */

public class CharacterBuilderActivity extends AppCompatActivity
{
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
}
