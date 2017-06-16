package cs496.dndcompanionapp.models;

import android.widget.ArrayAdapter;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liv on 6/15/2017.
 */

public class CharacterClass {
    public String _id;
    public String index;
    public String name;
    @SerializedName("hit_die") public int hitDie;
    public String url;

    public List<ProficiencyResultItem> proficiencies = new ArrayList<>();
    @SerializedName("saving_throws") public List<SavingThrowResultItem> savingThrows = new ArrayList<>();
    public List<SubclassResultItem> subclasses = new ArrayList<>();
    @SerializedName("starting_equipment") public StartingEquipmentItem startingEquipment;
}
