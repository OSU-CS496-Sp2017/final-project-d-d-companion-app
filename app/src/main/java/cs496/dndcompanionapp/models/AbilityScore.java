package cs496.dndcompanionapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liv on 6/16/2017.
 */

public class AbilityScore {
    public String id;
    public Integer index;
    public String name;
    public String fullName;
    public List<String> desc = new ArrayList<>();
    public List<Skill> skills = new ArrayList<>();
    public String url;
}
