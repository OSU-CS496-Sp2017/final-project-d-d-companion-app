package cs496.dndcompanionapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;


public final class DnDApi {
	public static final String apiUrl = "http://www.dnd5eapi.co/api/";

	/*
	 * Root is basically querying the first level of the api.  For example, if you
	 * were looking for a list of classes, you would do a root search of classes.
	 *
	 * Verified options:
	 * classes
	 * subclasses
	 * races
	 * subraces
	 * ability-scores
	 *
	 */
	public class RootSearchResult {
		public String name;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}
	}
	public class RootList {
		public Integer count;
		public List<RootSearchResult> results = null;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}
	}
	public interface OptionsForSelectionList {
		@GET("{root}/")
		Call<List<RootSearchResult>> listClasses(@Path("root") String listSearch);
	}



	public class LanguageDetail {
		public String id;
		public Integer index;
		public String name;
		public String type;
		public List<String> typicalSpeakers = null;
		public String script;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface LanguageDetails {
		@GET("languages/{languageIndex}")
		Call<List<LanguageDetail>> languageDetailsCall(@Path("languageIndex") int lang);
	}



	//races/{index}


	//subraces/{index or race}

	//classes/{index or class}

	//subclasses (can do /subclasses/num or /subclasses/class)

	//equipment/{index}

	//spell data


	//skills/{index}


	//proficiencies/{index or class}





}




