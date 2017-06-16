package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by G on 6/16/2017.
 */

public class CharacterRace {
    public String _id;
    public String index;
    public String name;
    public String alignment;
    public String age;
    public String size;
    @SerializedName("size_description") public String sizeDescription;
    @SerializedName("language_desc")public String languageDescription;
    public String url;
}
