import android.net.Uri;
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
			case "classes":
				url = url + "classes/"
				break;
			
			case "skills":
				url = url + "skills"
				break;
			
			case default:
				Log.d(TAG, "DnDApi.buildSearchURL: invalid searchType.");
				break;
		}
	}
}