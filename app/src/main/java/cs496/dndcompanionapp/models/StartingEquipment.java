package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Liv on 6/15/2017.
 */

public class StartingEquipment {
    public String _id;
    public String index;
    @SerializedName("starting_equipment") public List<StartingEquipmentResultItem> startingEquipment;
}