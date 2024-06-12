package com.example.festival;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival.dao.GroupeDao;
import com.example.festival.entity.Groupe;
import com.example.festival.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ListShowGroupeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    private ShowGroupeAdapter showgroupeAdapter;
    private List<Groupe> groupeList;
    public static final int EDIT_COMPETITION_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activtiy_list_show_groupe);

        recyclerView = findViewById(R.id.recyclerViewS);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser la liste des compétitions (cela pourrait venir d'une base de données)
        groupeList = new ArrayList<>();
        groupeList = GroupeDao.findAllGroupes();

        User user = (User) getIntent().getSerializableExtra("user");

        showgroupeAdapter = new ShowGroupeAdapter(groupeList, this, editGroupeActivityResultLauncher ,user);
        recyclerView.setAdapter(showgroupeAdapter);


    }


    private final ActivityResultLauncher<Intent> editGroupeActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    // Le code ici sera exécuté si le résultat de EditGroupeActivity est RESULT_OK
                    // Par exemple, recharger les données de la compétition et mettre à jour le RecyclerView
                    reloadGroupeData();
                }
            });

    private void reloadGroupeData() {
        List<Groupe> updatedGroupeList = GroupeDao.findAllGroupes();
        showgroupeAdapter.setGroupeList(updatedGroupeList);
        showgroupeAdapter.notifyDataSetChanged();
    }
}