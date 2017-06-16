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
	 * spells
	 * skills
	 * languages
	 * proficiencies
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
	public class SubclassSpell {

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
		public List<SubclassSpell> spells = null;
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
	public class WeaponCost {

		public Integer quantity;
		public String unit;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class WeaponDamage {

		public Integer diceCount;
		public Integer diceValue;
		public WeaponDamageType damageType;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class WeaponDamageType {

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
	public class WeaponDetail {

		public String id;
		public Integer index;
		public String name;
		public String equipmentCategory;
		public String weaponCategory;
		public String weaponRange;
		public String categoryRange;
		public WeaponCost cost;
		public WeaponDamage damage;
		public WeaponRange range;
		public Integer weight;
		public List<WeaponProperty> properties = null;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class WeaponProperty {

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
	public class WeaponRange {

		public Integer normal;
		public Object _long;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface WeaponDetails {
		@GET("equipment/{num}")
		Call <List<WeaponDetail>> weaponDetailsCall(@Path("index") int num);
	}


	public class ArmorCost {

		public Integer quantity;
		public String unit;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public class ArmorDetail {

		public String id;
		public Integer index;
		public String name;
		public String equipmentCategory;
		public String gearCategory;
		public ArmorCost cost;
		public Integer weight;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface ArmorDetails {
		@GET("equipment/{num}")
		Call <List<ArmorDetail>> armorDetailsCall(@Path("index") int num);
	}



	//spell data
	public class SpellClass {

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
	public class SpellSubclass {

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
	public class SpellSchool {

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
	public class SpellDetail {

		public String id;
		public Integer index;
		public String name;
		public List<String> desc = null;
		public List<String> higherLevel = null;
		public String page;
		public String range;
		public List<String> components = null;
		public String material;
		public String ritual;
		public String duration;
		public String concentration;
		public String castingTime;
		public Integer level;
		public SpellSchool school;
		public List<SpellClass> classes = null;
		public List<SpellSubclass> subclasses = null;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface SpellDetails {
		@GET("spells/{num}")
		Call <List<SpellDetail>> spellDetailsCall(@Path("index") int num);
	}


	//skills/{index}
	public class AbilityScore {

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
	public class SkillDetail {

		public String id;
		public Integer index;
		public String name;
		public List<String> desc = null;
		public AbilityScore abilityScore;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface SkillDetails {
		@GET("skills/{num}")
		Call <List<SkillDetail>> skillDetailsCall(@Path("index") int num);
	}



	//proficiencies/{index or class}
	public class ProficiencyClass {

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
	public class ProficiencyDetail {

		public String id;
		public Integer index;
		public String type;
		public String name;
		public List<ProficiencyClass> classes = null;
		public List<Object> races = null;
		public String url;
		private Map<String, Object> additionalProperties = new HashMap<String, Object>();

		public Map<String, Object> getAdditionalProperties() {
			return this.additionalProperties;
		}

		public void setAdditionalProperty(String name, Object value) {
			this.additionalProperties.put(name, value);
		}

	}
	public interface ProficiencyDetails {
		@GET("proficiencies/{num}")
		Call <List<ProficiencyDetail>> proficiencyDetailsCall(@Path("index") int num);
	}



	public static void main(String ... args) throws IOException {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).build();

		OptionsForSelectionList rootSearch = retrofit.create(OptionsForSelectionList.class);
		LanguageDetailsInterface languageSearch = retrofit.create(LanguageDetailsInterface.class);
		RaceDetails raceSearch = retrofit.create(RaceDetails.class);
		SubRaceDetails subraceSearch = retrofit.create(SubRaceDetails.class);
		ClassDetails classSearch = retrofit.create(ClassDetails.class);
		SubclassDetails subclassSearch = retrofit.create(SubclassDetails.class);
		WeaponDetails weaponSearch = retrofit.create(WeaponDetails.class);
		ArmorDetails armorSearch = retrofit.create(ArmorDetails.class);
		SpellDetails spellSearch = retrofit.create(SpellDetails.class);
		SkillDetails skillSearch = retrofit.create(SkillDetails.class);
		ProficiencyDetails proficiencySearch = retrofit.create(ProficiencyDetails.class);


		
	}

}