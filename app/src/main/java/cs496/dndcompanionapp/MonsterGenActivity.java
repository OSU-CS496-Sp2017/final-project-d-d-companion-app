package cs496.dndcompanionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Sanlador on 6/15/2017.
 */

public class MonsterGenActivity extends AppCompatActivity{
    private RecyclerView monsterList;
    private MonsterAdapter monster;
    Monster add;

    private EditText monsterNum;
    private EditText monsterCR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.monster_gen_activity);

        monsterList = (RecyclerView)findViewById(R.id.monsterList);
        monsterList.setLayoutManager(new LinearLayoutManager(this));
        monsterList.setHasFixedSize(true);

        monsterNum = (EditText)findViewById(R.id.editNumMonsters);
        monsterCR = (EditText)findViewById(R.id.editCR);

        monster = new MonsterAdapter();
        monsterList.setAdapter(monster);
        Button button = (Button)findViewById(R.id.buttonAddMonster);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = monsterNum.getText().toString();
                if (num.isEmpty() == false)
                {
                    int control = Integer.parseInt(num);
                    String CR = monsterCR.getText().toString();
                    if (!TextUtils.isEmpty(CR)) {
                        for (int i = 0; i < control; i++)
                        {
                            if (Integer.parseInt(CR) > 0 && Integer.parseInt(CR) < 21)
                            {
                                add = new Monster(Integer.parseInt(CR));
                                monster.addMonster(add);
                            }
                        }

                    }
                }
            }
        });
        getIntent().setAction("Already created"); //important for navigation
    }
    public void changeTheme(String theme){
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
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
        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Intent intent = new Intent(this, MonsterGenActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }
}
