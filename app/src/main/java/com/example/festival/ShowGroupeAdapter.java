package com.example.festival;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival.entity.Groupe;
import com.example.festival.entity.User;

import java.util.List;

public class ShowGroupeAdapter extends RecyclerView.Adapter<ShowGroupeAdapter.GroupeViewHolder>{

    private  List<Groupe> groupeList;
    private final Context context;
    private final ActivityResultLauncher<Intent> editGroupeActivityResultLauncher;
  private User user ;
    public ShowGroupeAdapter(List<Groupe> groupeList, Context context, ActivityResultLauncher<Intent> editGroupeActivityResultLauncher , User user) {
       this.groupeList = groupeList;
        this.context = context;
        this.editGroupeActivityResultLauncher = editGroupeActivityResultLauncher;
        this.user = user ;
    }

    @NonNull
    @Override
    public GroupeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // if (role du user)
        View view = LayoutInflater.from(context).inflate(R.layout.groupe_show_item, parent, false);
        return new GroupeViewHolder(view, this.editGroupeActivityResultLauncher);
    }



    @Override
    public void onBindViewHolder(GroupeViewHolder holder, int position) {
        Groupe groupe = groupeList.get(position);
        holder.textViewDescription.setText(groupe.getDescription());
        holder.textViewNombreUtilisateurs.setText(String.valueOf(groupe.getNombredutilisateur()));
    }

    @Override
    public int getItemCount() {
        return groupeList.size();
    }

    public void setGroupeList(List<Groupe> updatedGroupeList) {
        groupeList = updatedGroupeList;
    }

    class GroupeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDescription, textViewNombreUtilisateurs;
        ImageView  imageViewShow;
        ActivityResultLauncher<Intent> editGroupeActivityResultLauncher;

        GroupeViewHolder(View itemView, ActivityResultLauncher<Intent> editGroupeActivityResultLauncher) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewGroupeDescription);
            textViewNombreUtilisateurs = itemView.findViewById(R.id.textViewNombreUtilisateurs);
            imageViewShow = itemView.findViewById(R.id.imageViewShow);
            Log.i("id",  String.valueOf(user.getId()));
            this.editGroupeActivityResultLauncher = editGroupeActivityResultLauncher;



            imageViewShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Groupe groupeToEdit = groupeList.get(position);

                    Intent editIntent = new Intent(context, ShowGroupeActivity.class);
                    editIntent.putExtra("groupe", groupeToEdit);
                    editIntent.putExtra("user1", user);
                    editGroupeActivityResultLauncher.launch(editIntent);
                }
            });
        }
    }
}



