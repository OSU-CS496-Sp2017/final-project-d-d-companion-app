package cs496.dndcompanionapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import cs496.dndcompanionapp.models.CharacterClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Liv on 6/16/2017.
 */

public class ManualClassItemDetailActivity extends AppCompatActivity {
    private DnDApi.DnDApiService dndApi;
    private TextView name;
    private TextView hitDie;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_class_item_detail);

        name = (TextView) findViewById(R.id.name);
        hitDie = (TextView) findViewById(R.id.hit_die);

        Intent intent = getIntent();
        if(intent != null) {
            String classId = intent.getStringExtra("classId");

            dndApi = new DnDApi().createService();
            Call<CharacterClass> call = dndApi.getCharacterClass(classId);
            call.enqueue(new Callback<CharacterClass>() {
                @Override
                public void onResponse(Call<CharacterClass> call, Response<CharacterClass> response) {
                    name.setText(response.body().name);
                    Log.d("TEST", Integer.toString(response.body().hitDie));
                    hitDie.setText(Integer.toString(response.body().hitDie));
                }

                @Override
                public void onFailure(Call<CharacterClass> call, Throwable t) {

                }
            });
        }
    }
}
