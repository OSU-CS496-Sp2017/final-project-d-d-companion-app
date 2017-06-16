package cs496.dndcompanionapp.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liv on 6/16/2017.
 */

public class Proficiency {
    public String _id;
    public String index;
    public String type;
    public String name;
    public List<CharacterClassesResultItem> classes = new ArrayList<>();
    public String url;
}
