package cs496.dndcompanionapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MonsterAdapter extends RecyclerView.Adapter<MonsterAdapter.monsterHolder>
{
    private ArrayList<Monster> monsterList;

    public MonsterAdapter()
    {
        monsterList = new ArrayList<Monster>();
    }

    public void addMonster(Monster monster)
    {
        monsterList.add(monster);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return monsterList.size();
    }

    @Override
    public monsterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.monster_detail, parent, false);
        monsterHolder viewHolder = new monsterHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(monsterHolder holder, int position) {
        Monster monster = monsterList.get(monsterList.size() - position - 1);
        holder.bind(monster);
    }

    class monsterHolder extends RecyclerView.ViewHolder
    {
        private TextView monsterNameView;
        private TextView monsterACView;
        private TextView monsterWillView;
        private TextView monsterRefView;
        private TextView monsterFortView;
        private TextView monsterXPView;
        private TextView monsterAtkView;
        private TextView monsterDmgView;
        private TextView monsterCRView;

        public monsterHolder(View itemView) {
            super(itemView);
            monsterNameView = (TextView)itemView.findViewById(R.id.monsterNameView);
            monsterACView = (TextView)itemView.findViewById(R.id.ACViewNum);
            monsterWillView = (TextView)itemView.findViewById(R.id.willViewNum);
            monsterRefView = (TextView)itemView.findViewById(R.id.refViewNum);
            monsterFortView = (TextView)itemView.findViewById(R.id.fortViewNum);
            monsterXPView = (TextView)itemView.findViewById(R.id.XPViewNum);
            monsterAtkView = (TextView)itemView.findViewById(R.id.atckViewNum);
            monsterDmgView = (TextView)itemView.findViewById(R.id.dmgViewNum);
            monsterCRView = (TextView)itemView.findViewById(R.id.CRViewNum);
    }

        public void bind(Monster monster)
        {
            monsterNameView.setText(monster.name);
            monsterACView.setText(Integer.toString(monster.AC));
            monsterWillView.setText(Integer.toString(monster.Will));
            monsterFortView.setText(Integer.toString(monster.Fort));
            monsterRefView.setText(Integer.toString(monster.Ref));
            monsterXPView.setText(Integer.toString(monster.XP));
            monsterAtkView.setText(Integer.toString(monster.Atk));
            monsterCRView.setText(Integer.toString(monster.CR));
            String damage = Integer.toString(monster.numDice) + "d" + Integer.toString(monster.DmgDice) + " + " + Integer.toString(monster.DmgBonus);
            monsterDmgView.setText(damage);
        }
    }
}