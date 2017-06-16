package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liv on 6/15/2017.
 */

public class CharacterSpell {
    public String _id;
    public String index;
    public String name;
    @SerializedName("higher_level") public List<String> highLevel = new ArrayList<>();
    public List<String> desc = new ArrayList<>();
    public List<String> components = new ArrayList<>();
    public String material;
    public String url;
}
