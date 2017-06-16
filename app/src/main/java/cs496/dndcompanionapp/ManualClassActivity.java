package cs496.dndcompanionapp;

import android.content.Context;
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

import cs496.dndcompanionapp.models.AbilityScore;
import cs496.dndcompanionapp.models.CharacterClass;
import cs496.dndcompanionapp.models.CharacterClassesResult;
import cs496.dndcompanionapp.models.CharacterClassesResultItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Path;

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
    }
    public void changeTheme(String theme){
        setTheme(getResources().getIdentifier(theme, "style", getPackageName()));
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
        }

        class ManualClassItemViewHolder extends RecyclerView.ViewHolder {
            protected TextView vName;

            public ManualClassItemViewHolder(View v) {
                super(v);

                vName = (TextView) v.findViewById(R.id.name);
            }
        }
    }

}
