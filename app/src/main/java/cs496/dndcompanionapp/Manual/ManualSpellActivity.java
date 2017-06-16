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
import cs496.dndcompanionapp.models.CharacterSpell;
import cs496.dndcompanionapp.models.CharacterSpellsResult;
import cs496.dndcompanionapp.models.CharacterSpellsResultItem;
import cs496.dndcompanionapp.models.CharacterSpellsResult;
import cs496.dndcompanionapp.models.CharacterSpellsResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;


import cs496.dndcompanionapp.DnDApi;

import cs496.dndcompanionapp.*;


public class ManualSpellActivity extends AppCompatActivity {
    DnDApi.DnDApiService dndApi;

    private RecyclerView mRecyclerView;
    private ManualSpellActivity.ManualSpellAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeTheme(sharedPreferences.getString(
                getString(R.string.theme_key),
                getString(R.string.theme_default)
        ));
        super.onCreate(savedInstanceState);

        getIntent().setAction("Already created"); //important for navigation

        setContentView(R.layout.manual_item);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_manual_item);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new ManualSpellActivity.ManualSpellAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        dndApi = new DnDApi().createService();
        Call<CharacterSpellsResult> call = dndApi.getCharacterSpells();

        call.enqueue(new Callback<CharacterSpellsResult>() {
            @Override
            public void onResponse(Call<CharacterSpellsResult> call, Response<CharacterSpellsResult> response) {
                mAdapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<CharacterSpellsResult> call, Throwable t) {

            }
        });
        getIntent().setAction("Already created"); //important for navigation
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


    public class ManualSpellAdapter extends RecyclerView.Adapter<ManualSpellActivity.ManualSpellAdapter.ManualSpellViewHolder> {
        private Context context;
        private List<CharacterSpellsResultItem> classes = new ArrayList<>();

        public ManualSpellAdapter(Context context) {
            this.context = context;
        }

        public void updateData(CharacterSpellsResult results) {
            for (CharacterSpellsResultItem result : results.results) {
                classes.add(result);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return classes.size();
        }

        @Override
        public ManualSpellActivity.ManualSpellAdapter.ManualSpellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.manual_item_card, parent, false);
            return new ManualSpellActivity.ManualSpellAdapter.ManualSpellViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ManualSpellActivity.ManualSpellAdapter.ManualSpellViewHolder holder, int position) {
            CharacterSpellsResultItem cls = classes.get(position);
            holder.vName.setText(cls.name);
            String url = classes.get(position).url;
            holder.classId = url.substring(url.length() - 1, url.length());
        }

        class ManualSpellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            protected String classId;
            protected TextView vName;

            public ManualSpellViewHolder(View v) {
                super(v);

                vName = (TextView) v.findViewById(R.id.name);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ManualSpellDetailActivity.class);
                        intent.putExtra("classId", classId);
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
            Log.v("Example", "Force restart");
            Intent intent = new Intent(this, ManualSpellActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }
}