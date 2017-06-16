package cs496.dndcompanionapp;



import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String theme =
                sharedPreferences.getString(
                        getString(R.string.theme_key),
                        getString(R.string.theme_default)
                );
        changeTheme(theme);
        Log.d("TAG","Value of units:  "+ theme);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }
    @Override
    public void onBackPressed() {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void changeTheme(String theme){
        Log.d("TAG","Value of units in changeTheme:  " + theme);
        switch (theme) {
            case "Bard":
                setTheme(R.style.Bard);
                break;
            case "Barbarian":
                setTheme(R.style.Barbarian);
                break;
            case "Cleric":
                setTheme(R.style.Cleric);
                break;
            case "Druid":
                setTheme(R.style.Druid);
                break;
            case "Fighter":
                setTheme(R.style.Fighter);
                break;
            case "Monk":
                setTheme(R.style.Monk);
                break;
            case "Paladin":
                setTheme(R.style.Paladin);
                break;
            case "Ranger":
                setTheme(R.style.Ranger);
                break;
            case "Rogue":
                setTheme(R.style.Rogue);
                break;
            case "Sorcerer":
                setTheme(R.style.Sorcerer);
                break;
            case "Warlock":
                setTheme(R.style.Warlock);
                break;
            case "Wizard":
                setTheme(R.style.Wizard);
                break;
            default:
                break;
        }
    }


}
