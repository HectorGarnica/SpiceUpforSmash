package com.example.spiceupforsmash;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {

    private Context mContext;
    private List<Player> playerList = new ArrayList<>();

    public PlayerAdapter(@NonNull Context context, @SuppressLint("SupportAnnotationUsage") @LayoutRes ArrayList<Player> list) {
        super(context,0, list);
        mContext = context;
        playerList = list;
    }

    @NonNull

    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;

        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.player_layout,parent,false);



        Player currentPlayer = playerList.get(position);



        TextView playerName = (TextView)listItem.findViewById(R.id.PlayerTextView);  //get player name layout

        playerName.setText(currentPlayer.getPlayerName());



        TextView playerQuirk = (TextView) listItem.findViewById(R.id.QuirkView);

        playerQuirk.setText(currentPlayer.getPlayerQuirk());


        return listItem;

    }

}
