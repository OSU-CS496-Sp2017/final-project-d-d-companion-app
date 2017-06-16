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
import cs496.dndcompanionapp.models.SkillResult;
import cs496.dndcompanionapp.models.SkillResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by G on 6/16/2017.
 */

public class ManualSkillActivity extends AppCompatActivity {
    DnDApi.DnDApiService dndApi;

    private RecyclerView mRecyclerView;
    private ManualSkillAdapter mAdapter;
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

        mAdapter = new ManualSkillAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        dndApi = new DnDApi().createService();
        Call<SkillResult> call = dndApi.getCharacterSkills();

        call.enqueue(new Callback<SkillResult>() {
            @Override
            public void onResponse(Call<SkillResult> call, Response<SkillResult> response) {
                mAdapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<SkillResult> call, Throwable t) {

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

    public class ManualSkillAdapter extends RecyclerView.Adapter<ManualSkillAdapter.ManualSkillItemViewHolder> {
        private Context context;
        private List<SkillResultItem> skills = new ArrayList<>();

        public ManualSkillAdapter(Context context) {
            this.context = context;
        }

        public void updateData(SkillResult results) {
            for (SkillResultItem result : results.results) {
                skills.add(result);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return skills.size();
        }

        @Override
        public ManualSkillItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.manual_item_card, parent, false);
            return new ManualSkillItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ManualSkillItemViewHolder holder, int position) {
            SkillResultItem cls = skills.get(position);
            holder.vName.setText(cls.name);
            String url = skills.get(position).url;
            holder.skillId = url.substring(url.length() - 1, url.length());
        }

        class ManualSkillItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            protected String skillId;
            protected TextView vName;

            public ManualSkillItemViewHolder(View v) {
                super(v);

                vName = (TextView) v.findViewById(R.id.name);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ManualSkillDetailActivity.class);
                        intent.putExtra("skillId", skillId);
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
            Intent intent = new Intent(this, ManualSkillActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }

}
