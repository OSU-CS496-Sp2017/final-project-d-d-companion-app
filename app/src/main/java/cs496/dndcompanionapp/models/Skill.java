package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liv on 6/16/2017.
 */

public class Skill {
    public String _id;
    public String index;
    public String name;
    public List<String> desc = new ArrayList<>();
    @SerializedName("ability_score") public abilityScores abilScore;
    public String url;

    public class abilityScores {
        public String url;
        @SerializedName("name") public String abilityName;

        public String getAbilityName() {
            return abilityName;
        }
    }
}
