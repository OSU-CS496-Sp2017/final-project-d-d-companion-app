package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Liv on 6/15/2017.
 */

public class CharacterClass {
    public String _id;
    public String index;
    public String name;
    @SerializedName("hit_die") public int hitDie;
    public String url;
}
