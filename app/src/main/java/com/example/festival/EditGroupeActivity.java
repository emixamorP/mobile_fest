package com.example.festival;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.festival.dao.GroupeDao;
import com.example.festival.entity.Groupe;

public class EditGroupeActivity extends AppCompatActivity {

    private EditText editTextDescription, editTextNombreParticipants;
    private Button buttonSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_groupe);

        editTextDescription = findViewById(R.id.editTextDescription);
        editTextNombreParticipants = findViewById(R.id.editTextNombreUtilisateurs);
        buttonSaveChanges = findViewById(R.id.buttonUpdate);

        // Récupérer l'ID de la groupe depuis l'Intent
      Groupe groupe = (Groupe) getIntent().getSerializableExtra("groupe");

        // Charger les données de la groupe depuis la base de données


        // Afficher les données existantes de la groupe dans les EditText
        if (groupe != null) {
            editTextDescription.setText(groupe.getDescription());

        }

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérer les valeurs modifiées
                String description = editTextDescription.getText().toString().trim();
                int nombreParticipants = Integer.parseInt(editTextNombreParticipants.getText().toString().trim());

                // Valider les champs et mettre à jour la groupe dans la base de données
                groupe.setDescription(description);


                GroupeDao.updateGroupe(groupe);

                // Retourner le résultat à l'activité précédente si nécessaire
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}