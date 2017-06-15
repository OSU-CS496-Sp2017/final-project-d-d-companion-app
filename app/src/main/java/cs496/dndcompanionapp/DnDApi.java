package cs496.dndcompanionapp;


import java.io.IOException;
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
	 * equipment
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
		Call<List<RootList>> listClasses(@Path("root") String listSearch);
	}




	//languages/{index}
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
	public interface LanguageDetailsInterface {
		@GET("languages/{languageIndex}")
		Call<List<LanguageDetail>> languageDetailsCall(@Path("languageIndex") int lang);
	}




	//races/{index}
	public class RaceFrom {

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
	public class RaceLanguage {

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
	public class RaceStartingProficiency {

		public String name;
		public String url;
		public List<RaceFrom> from = null;
		public String type;
		public Integer choose;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class Subrace {

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
	public class RaceTrait {

		public String url;
		public String name;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class RaceDetail {

		public String id;
		public Integer index;
		public String name;
		public Integer speed;
		public List<Integer> abilityBonuses = null;
		public String alignment;
		public String age;
		public String size;
		public String sizeDescription;
		public List<RaceStartingProficiency> startingProficiencies = null;
		public List<RaceLanguage> languages = null;
		public String languageDesc;
		public List<RaceTrait> traits = null;
		public List<Subrace> subraces = null;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface RaceDetails {
		@GET("races/{raceIndex}")
		Call<List<RaceDetail>> raceDetailsCall(@Path("raceIndex") int raceIndex);
	}



	//subraces/{index}
	public class SubRaceRace {

		public String url;
		public String name;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class SubRaceRacialTrait {

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
	public class SubraceDetail {

		public String id;
		public Integer index;
		public String name;
		public SubRaceRace race;
		public String desc;
		public List<Integer> abilityBonuses = null;
		public List<Object> startingProficiencies = null;
		public List<Object> languages = null;
		public List<SubRaceRacialTrait> racialTraits = null;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface SubRaceDetails {
		@GET("races/{subraceIndex}")
		Call<List<SubraceDetail>> subraceDetailsCall(@Path("subraceIndex") int subraceIndex);
	}




	//classes/{index or class}

	//subclasses (can do /subclasses/num or /subclasses/class)

	//equipment/{index}

	//spell data


	//skills/{index}


	//proficiencies/{index or class}



	public static void main(String ... args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).build();
		
	}

}