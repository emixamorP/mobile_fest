package com.example.festival;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival.dao.GroupeDao;
import com.example.festival.entity.Groupe;

import java.util.List;

public class GroupeAdapter extends RecyclerView.Adapter<GroupeAdapter.GroupeViewHolder> {

    private static List<Groupe> groupeList;
    private final Context context;
    private final ActivityResultLauncher<Intent> editGroupeActivityResultLauncher;

    public GroupeAdapter(List<Groupe> groupeList, Context context, ActivityResultLauncher<Intent> editGroupeActivityResultLauncher) {
        GroupeAdapter.groupeList = groupeList;
        this.context = context;
        this.editGroupeActivityResultLauncher = editGroupeActivityResultLauncher;
    }

    @NonNull
    @Override
    public GroupeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // if (role du user)
        View view = LayoutInflater.from(context).inflate(R.layout.groupe_item, parent, false);
        return new GroupeViewHolder(view, this.editGroupeActivityResultLauncher);
    }

    @Override
    public void onBindViewHolder(GroupeViewHolder holder, int position) {
        Groupe groupe = groupeList.get(position);
        holder.textViewDescription.setText(groupe.getDescription());
        holder.textViewNombreUtilisateur.setText(String.valueOf(groupe.getNombredutilisateur()));
    }

    @Override
    public int getItemCount() {
        return groupeList.size();
    }

    public void setGroupeList(List<Groupe> updatedGroupeList) {
        groupeList = updatedGroupeList;
    }

    class GroupeViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDescription, textViewNombreUtilisateur;
        ImageView imageViewDelete, imageViewEdit;
        ActivityResultLauncher<Intent> editGroupeActivityResultLauncher;

        GroupeViewHolder(View itemView, ActivityResultLauncher<Intent> editGroupeActivityResultLauncher) {
            super(itemView);
            textViewDescription = itemView.findViewById(R.id.textViewGroupeDescription);
            textViewNombreUtilisateur = itemView.findViewById(R.id.textViewNombreUtilisateur);
            imageViewDelete = itemView.findViewById(R.id.imageViewDelete);
            imageViewEdit = itemView.findViewById(R.id.imageViewEdit);
            this.editGroupeActivityResultLauncher = editGroupeActivityResultLauncher;

            imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Groupe groupe = groupeList.get(position);
                    GroupeDao.deleteGroupe(groupe.getId());
                    groupeList.remove(position);
                    notifyItemRemoved(position);
                }
            });

            imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Groupe groupeToEdit = groupeList.get(position);

                    Intent editIntent = new Intent(context, EditGroupeActivity.class);
                    editIntent.putExtra("groupe", groupeToEdit);
                    editGroupeActivityResultLauncher.launch(editIntent);
                }
            });
        }
    }
}

