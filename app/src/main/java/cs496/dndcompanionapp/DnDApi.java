package cs496.dndcompanionapp;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

//Note: all api queries need to be lowercase, and not uppercase.

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
	public class ClassDetail {

		public String id;
		public Integer index;
		public String name;
		public Integer hitDie;
		public List<ProficiencyChoice> proficiencyChoices = null;
		public List<ClassProficiency> proficiencies = null;
		public List<SavingThrow> savingThrows = null;
		public StartingEquipment startingEquipment;
		public ClassLevels classLevels;
		public List<Subclass> subclasses = null;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class ClassLevels {

		public String url;
		public String _class;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class ProficiencyChoiceFrom {

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
	public class ClassProficiency {

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
	public class ProficiencyChoice {

		public List<ProficiencyChoiceFrom> proficiencyChoiceFrom = null;
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
	public class SavingThrow {

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
	public class StartingEquipment {

		public String url;
		public String _class;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class Subclass {

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
	public interface ClassDetails {
		@GET("classes/{indexOrClass}")
		Call <List<ClassDetail>> classDetailsCall(@Path("indexOrClass") String classQuery);
	}


	//subclasses (can do /subclasses/num or /subclasses/className, but they return different things.)
	public class SubclassClassReference {

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
	public class Spell {

		public Spell_ spell;
		public List<Object> prerequisites = null;
		public SpellAcquisitionMethod spellAcquisitionMethod;
		public Integer levelAcquired;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class SpellAcquisitionMethod {

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
	public class Spell_ {

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
	public class SubclassDetail {

		public String id;
		public Integer index;
		public SubclassClassReference _class;
		public String name;
		public String subclassFlavor;
		public List<String> desc = null;
		public List<Object> features = null;
		public List<Spell> spells = null;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface SubclassDetails {
		@GET("subclasses/{num}")
		Call <List<SubclassDetail>> subclassDetailsCall(@Path("index") int num);
	}

	
	//equipment/{index}

	//spell data


	//skills/{index}


	//proficiencies/{index or class}



	public static void main(String ... args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).build();

		OptionsForSelectionList rootSearch = retrofit.create(OptionsForSelectionList.class);

	}

}