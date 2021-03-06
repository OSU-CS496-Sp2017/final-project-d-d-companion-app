package cs496.dndcompanionapp.Manual;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cs496.dndcompanionapp.DnDApi;
import cs496.dndcompanionapp.MainActivity;
import cs496.dndcompanionapp.R;
import cs496.dndcompanionapp.SettingsActivity;
import cs496.dndcompanionapp.models.CharacterClassesResult;
import cs496.dndcompanionapp.models.CharacterClassesResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by brandon on 6/16/2017.
 */

public class ManualClassActivity extends AppCompatActivity {
    DnDApi.DnDApiService dndApi;

    private RecyclerView mRecyclerView;
    private ManualClassAdapter mAdapter;
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

        mAdapter = new ManualClassAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        dndApi = new DnDApi().createService();
        Call<CharacterClassesResult> call = dndApi.getCharacterClasses();

        call.enqueue(new Callback<CharacterClassesResult>() {
            @Override
            public void onResponse(Call<CharacterClassesResult> call, Response<CharacterClassesResult> response) {
                mAdapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<CharacterClassesResult> call, Throwable t) {

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


    public class ManualClassAdapter extends RecyclerView.Adapter<ManualClassAdapter.ManualClassItemViewHolder> {
        private Context context;
        private List<CharacterClassesResultItem> classes = new ArrayList<>();

        public ManualClassAdapter(Context context) {
            this.context = context;
        }

        public void updateData(CharacterClassesResult results) {
            for (CharacterClassesResultItem result : results.results) {
                classes.add(result);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return classes.size();
        }

        @Override
        public ManualClassItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.manual_item_card, parent, false);
            return new ManualClassItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ManualClassItemViewHolder holder, int position) {
            CharacterClassesResultItem cls = classes.get(position);
            holder.vName.setText(cls.name);
            String url = classes.get(position).url;
            holder.classId = url.split("http://www.dnd5eapi.co/api/classes/")[1];
        }

        class ManualClassItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            protected String classId;
            protected TextView vName;

            public ManualClassItemViewHolder(View v) {
                super(v);

                vName = (TextView) v.findViewById(R.id.name);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ManualClassItemDetailActivity.class);
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
            Intent intent = new Intent(this, ManualClassActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }
}
