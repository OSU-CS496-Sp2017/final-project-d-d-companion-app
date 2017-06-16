package cs496.dndcompanionapp;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import cs496.dndcompanionapp.models.AbilityScore;
import cs496.dndcompanionapp.models.CharacterClass;
import cs496.dndcompanionapp.models.CharacterClassesResult;
import cs496.dndcompanionapp.models.CharacterSpell;
import cs496.dndcompanionapp.models.CharacterSpellsResult;
import cs496.dndcompanionapp.models.CharacterSubclass;
import cs496.dndcompanionapp.models.CharacterSubclassesResult;
import cs496.dndcompanionapp.models.CharacterSubrace;
import cs496.dndcompanionapp.models.CharacterSubracesResult;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class DnDApi {
	private final static String TAG = "";
	
	private final static String DND_SEARCH_BASE_URL = "http://dnd5eapi.co/api/";

	public DnDApiService createService() {
        HttpLoggingInterceptor log = new HttpLoggingInterceptor();
        log.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(log);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(DND_SEARCH_BASE_URL)
                .build();

        return retrofit.create(DnDApiService.class);
    }

	public interface DnDApiService {
        @GET("classes")
        Call<CharacterClassesResult> getCharacterClasses();
        @GET("subraces")
        Call<CharacterSubracesResult> getCharacterSubraces();
        @GET("spells")
        Call<CharacterSpellsResult> getCharacterSpells();
        @GET("subclasses")
        Call<CharacterSubclassesResult> getCharacterSubclasses();
		@GET("classes/{class}")
        Call<CharacterClass> getCharacterClass(@Path("class") String characterClass);
        @GET("subraces/{subrace}")
        Call<CharacterSubrace> getCharacterSubraces(@Path("subrace") String characterSubraces);
        @GET("spells/{spell}")
        Call<CharacterSpell> getCharacterSpells(@Path("spell") String characterSpells);
        @GET("subclasses/{subclass}")
        Call<CharacterSubclass> getCharacterSubclasses(@Path("subclass") String characterSpells);

        @GET("ability-scores/{score}")
        Call<AbilityScore> getAbilityScore(@Path("score") String abilityScore);
	}

	public enum CharacterClasses {
        BARBARIAN("1"),
        BARD("2"),
        CLERIC("3"),
        DRUID("4"),
        FIGHTER("5"),
        MONK("6"),
        PALADIN("7"),
        RANGER("8"),
        ROGUE("9"),
        SORCERER("10"),
        WARLOCK("11"),
        WIZARD("12");

        private String value;

        private CharacterClasses(String value) { this.value = value; }

        public String getValue() {
            return value;
        }
    }

    public enum AbilityScores {
        STR("1"),
        DEX("2"),
        CON("3"),
        INT("4"),
        WIS("5"),
        CHA("6");

        private String value;

        private AbilityScores(String value) { this.value = value; }

        public String getValue() {
            return value;
        }
    }
}