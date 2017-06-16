package cs496.dndcompanionapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Liv on 6/16/2017.
 */

public class EquipmentResultItem {
    public int quantity;
    @SerializedName("item_url") public String url;
}
