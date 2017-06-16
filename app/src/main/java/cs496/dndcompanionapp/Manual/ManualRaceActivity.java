package cs496.dndcompanionapp.Manual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs496.dndcompanionapp.*;
import cs496.dndcompanionapp.models.CharacterClassesResult;
import cs496.dndcompanionapp.models.CharacterClassesResultItem;
import cs496.dndcompanionapp.models.CharacterRacesResult;
import cs496.dndcompanionapp.models.CharacterRacesResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by G on 6/16/2017.
 */

public class ManualRaceActivity extends AppCompatActivity {
    DnDApi.DnDApiService dndApi;

    private RecyclerView mRecyclerView;
    private ManualRaceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual_item);
        getIntent().setAction("Already created"); //important for navigation
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_manual_item);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ManualRaceActivity.ManualRaceAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        dndApi = new DnDApi().createService();
        Call<CharacterRacesResult> call = dndApi.getCharacterRaces();

        call.enqueue(new Callback<CharacterRacesResult>() {
            @Override
            public void onResponse(Call<CharacterRacesResult> call, Response<CharacterRacesResult> response) {
                mAdapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<CharacterRacesResult> call, Throwable t) {

            }
        });
    }
    public void changeTheme(String theme){
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class ManualRaceAdapter extends RecyclerView.Adapter<ManualRaceAdapter.ManualRaceItemViewHolder> {
        private Context context;
        private List<CharacterRacesResultItem> races = new ArrayList<>();

        public ManualRaceAdapter(Context context) {
            this.context = context;
        }

        public void updateData(CharacterRacesResult results) {
            for (CharacterRacesResultItem result : results.results) {
                races.add(result);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return races.size();
        }

        @Override
        public ManualRaceItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.manual_item_card, parent, false);
            return new ManualRaceItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ManualRaceItemViewHolder holder, int position) {
            CharacterRacesResultItem cls = races.get(position);
            holder.vName.setText(cls.name);
            String url = races.get(position).url;
            holder.raceId = url.substring(url.length() - 1, url.length());
        }

        class ManualRaceItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            protected String raceId;
            protected TextView vName;

            public ManualRaceItemViewHolder(View v) {
                super(v);

                vName = (TextView) v.findViewById(R.id.name);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ManualRaceDetailActivity.class);
                        intent.putExtra("raceId", raceId);
                        v.getContext().startActivity(intent);
                    }
                });
            }

            @Override
            public void onClick(View v) {

            }
        }
    }

    @Override
    protected void onResume() {
        String action = getIntent().getAction();
        // Prevent endless loop by adding a unique action, don't restart if action is present
        if(action == null || !action.equals("Already created")) {
            Intent intent = new Intent(this, ManualRaceActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }

}
