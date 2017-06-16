package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Liv on 6/16/2017.
 */

public class Equipment {
    public String _id;
    public String index;
    public String name;
    @SerializedName("equipment_category") public String equipmentCategory;
    @SerializedName("gear_category") public String gearCategory;
    public Cost cost;
    public List<EquipmentResultItem> contents;
    public String url;
}