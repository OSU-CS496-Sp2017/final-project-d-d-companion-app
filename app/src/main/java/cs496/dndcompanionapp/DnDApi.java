package cs496.dndcompanionapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;


public class DnDApi {
	private final static String TAG = "";
	
	private final static String DND_SEARCH_BASE_URL = "http://dnd5eapi.co/api/";
	
	
	public static String buildSearchURL (String query, String searchType) {
		String url = DND_SEARCH_BASE_URL;
		
		switch(searchType) {
			case "class": case "classes":
				url = url + "classes/";
				break;
				
			case "subclass": case "subclasses":
				url = url + "subclasses/";
				break;
				
			case "race": case "races":
				url = url + "races/";
				break;
			
			case "subrace": case "subraces":
				url = url + "subraces/";
				break;
			
			case "skills":
				url = url + "skills/";
				break;
				
			case "spell": case "spells":
				url = url + "spells/";
				//note: if looking for specific spell, must be an index.
				break;
				
			case "proficiencies":
				url = url + "proficiencies/";
				//must cast as lowercase
				//can search by class, index, or keyword (ie: armor, weapons)
				break;
				
			case "languages":
				url = url + "languages/";
				break;
				
			case "equipment":
				url = url + "equipment/";
				break;
			
			default:
				//Log.d(TAG, "DnDApi.buildSearchURL: invalid searchType.");
				break;
		}
		return null;
	}
}