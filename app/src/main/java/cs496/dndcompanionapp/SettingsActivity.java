package cs496.dndcompanionapp;



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
                //setTheme();
                break;
            case "Druid":
                //setTheme();
                break;
            case "Fighter":
                //setTheme();
                break;
            case "Monk":
                //setTheme();
                break;
            case "Paladin":
                //setTheme();
                break;
            case "Ranger":
                //setTheme();
                break;
            case "Rogue":
                //setTheme();
                break;
            case "Sorcerer":
                //setTheme();
                break;
            case "Warlock":
                //setTheme();
                break;
            case "Wizard":
                //setTheme();
                break;
            default:
                break;
        }
    }


}
