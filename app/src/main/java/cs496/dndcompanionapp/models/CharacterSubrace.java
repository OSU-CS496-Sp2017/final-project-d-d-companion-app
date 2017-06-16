package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Liv on 6/15/2017.
 */

public class CharacterSubrace {
    public String _id;
    public String index;
    public String name;
    @SerializedName("ability_bonuses") public int[] abilityBonus;
    public String desc;
    public String url;
}
