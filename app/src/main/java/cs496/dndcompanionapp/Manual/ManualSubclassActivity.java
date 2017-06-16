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
import cs496.dndcompanionapp.models.CharacterSubclass;

import cs496.dndcompanionapp.models.CharacterSubclassesResult;
import cs496.dndcompanionapp.models.CharacterSubclassesResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.ArrayList;
import java.util.List;


import cs496.dndcompanionapp.DnDApi;

import cs496.dndcompanionapp.*;


public class ManualSubclassActivity extends AppCompatActivity {
    DnDApi.DnDApiService dndApi;

    private RecyclerView mRecyclerView;
    private ManualSubclassActivity.ManualSubclassAdapter mAdapter;
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

        mAdapter = new ManualSubclassActivity.ManualSubclassAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        dndApi = new DnDApi().createService();
        Call<CharacterSubclassesResult> call = dndApi.getCharacterSubclasses();

        call.enqueue(new Callback<CharacterSubclassesResult>() {
            @Override
            public void onResponse(Call<CharacterSubclassesResult> call, Response<CharacterSubclassesResult> response) {
                mAdapter.updateData(response.body());
            }

            @Override
            public void onFailure(Call<CharacterSubclassesResult> call, Throwable t) {

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


    public class ManualSubclassAdapter extends RecyclerView.Adapter<ManualSubclassActivity.ManualSubclassAdapter.ManualSubclassViewHolder> {
        private Context context;
        private List<CharacterSubclassesResultItem> classes = new ArrayList<>();

        public ManualSubclassAdapter(Context context) {
            this.context = context;
        }

        public void updateData(CharacterSubclassesResult results) {
            for (CharacterSubclassesResultItem result : results.results) {
                classes.add(result);
            }
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return classes.size();
        }

        @Override
        public ManualSubclassActivity.ManualSubclassAdapter.ManualSubclassViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.manual_item_card, parent, false);
            return new ManualSubclassActivity.ManualSubclassAdapter.ManualSubclassViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ManualSubclassActivity.ManualSubclassAdapter.ManualSubclassViewHolder holder, int position) {
            CharacterSubclassesResultItem cls = classes.get(position);
            holder.vName.setText(cls.name);
            String url = classes.get(position).url;
            holder.subclassId = url.substring(url.length() - 1, url.length());
        }

        class ManualSubclassViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            protected String subclassId;
            protected TextView vName;

            public ManualSubclassViewHolder(View v) {
                super(v);

                vName = (TextView) v.findViewById(R.id.name);

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), ManualSubclassDetailActivity.class);
                        intent.putExtra("subclassId", subclassId);
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
            Intent intent = new Intent(this, ManualSubclassActivity.class);
            startActivity(intent);
            finish();
        }
        // Remove the unique action so the next time onResume is called it will restart
        else
            getIntent().setAction(null);

        super.onResume();
    }
}