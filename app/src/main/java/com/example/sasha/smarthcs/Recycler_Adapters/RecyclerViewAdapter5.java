package com.example.sasha.smarthcs.Recycler_Adapters;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasha.smarthcs.Main_1.MainActivity;
import com.example.sasha.smarthcs.ProfileActivity;
import com.example.sasha.smarthcs.R;

import static com.example.sasha.smarthcs.ProfileActivity.cards3;

public class RecyclerViewAdapter5 extends RecyclerView.Adapter<RecyclerViewAdapter5.ViewHolder> {

    interface OnResourceSelected {
        void onResourcesSelected(int pos);
    }

    Resources resources;

    public RecyclerViewAdapter5(Resources resources) {
        this.resources = resources;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card2_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.name.setText("Дата : ");
        if (i<4){
            viewHolder.namet.setText( String.valueOf(cards3.get(i))+"."+ MainActivity.year);}
        else { viewHolder.namet.setText( String.valueOf(cards3.get(i))+"."+MainActivity.year_pre);}
        viewHolder.cost.setText("Цена : ");
        String res;
        String pars = ProfileActivity.Total_cards.get(i);
        pars=ProfileActivity.removeChar(pars,0);
        pars=ProfileActivity.removeChar(pars,0);
        pars=ProfileActivity.removeChar(pars,0);
        pars=ProfileActivity.removeChar(pars,4);
        pars=ProfileActivity.removeChar(pars,4);
        pars=ProfileActivity.removeChar(pars,4);


        res=pars;
        viewHolder.costt.setText(res+" "+"Рублей");
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView cost;
        TextView namet;
        TextView costt;
        View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
            cost = view.findViewById(R.id.textView2);
            name = view.findViewById(R.id.name);
            namet = view.findViewById(R.id.cost);
            costt = view.findViewById(R.id.textView3);
        }
    }
}
