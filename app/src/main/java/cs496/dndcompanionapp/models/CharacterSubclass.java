package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liv on 6/15/2017.
 */

public class CharacterSubclass {
    public String _id;
    public String index;
    public String name;
    public List<String> desc = new ArrayList<>();
    public String subclass_flavor;
    public String url;
}