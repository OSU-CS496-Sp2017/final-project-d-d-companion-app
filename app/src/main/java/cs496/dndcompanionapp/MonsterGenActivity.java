package cs496.dndcompanionapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
                            add = new Monster(Integer.parseInt(CR));
                            monster.addMonster(add);
                        }

                    }
                }
            }
        });
    }
    public void changeTheme(String theme){
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
    }
}
