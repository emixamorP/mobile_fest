package com.example.festival;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.festival.dao.GroupeDao;
import com.example.festival.entity.Groupe;

import java.util.ArrayList;
import java.util.List;

public class ListGroupeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private GroupeAdapter groupeAdapter;
    private List<Groupe> groupeList;
    public static final int EDIT_GROUPE_REQUEST_CODE = 1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_groupe);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialiser la liste des compétitions (cela pourrait venir d'une base de données)
        groupeList = new ArrayList<>();
        groupeList = GroupeDao.findAllGroupes();

        groupeAdapter = new GroupeAdapter(groupeList, this, editGroupeActivityResultLauncher);
        recyclerView.setAdapter(groupeAdapter);
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
        groupeAdapter.setGroupeList(updatedGroupeList);
        groupeAdapter.notifyDataSetChanged();
    }

    public static class ShowGroupeActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EdgeToEdge.enable(this);
            setContentView(R.layout.activity_show_groupe);
            ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }
    }
}