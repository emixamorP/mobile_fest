package com.example.festival;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.festival.dao.InscriptionDao;
import com.example.festival.entity.Groupe;
import com.example.festival.entity.Inscription;
import com.example.festival.entity.User;

public class ShowGroupeActivity extends AppCompatActivity {

    private TextView editTextDescription, editTextDateDebut, editTextDateFin, editTextNombreParticipants;
    private Button buttonInscription;
    Groupe groupe ;
    User  user;
    Inscription inscription ;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_groupe);

        editTextDescription = findViewById(R.id.editTextDescriptionS);
        editTextNombreParticipants = findViewById(R.id.editTextNombreUtilisateurs);
        buttonInscription = findViewById(R.id.buttonInscriptionS);

        // Récupérer l'ID de la groupe depuis l'Intent
        groupe = (Groupe) getIntent().getSerializableExtra("groupe");
        user = (User) getIntent().getSerializableExtra("user1");

        // Charger les données de la groupe depuis la base de données

        // Afficher les données existantes de la groupe dans les EditText
        if (groupe != null) {
            editTextDescription.setText(groupe.getDescription());
            editTextNombreParticipants.setText(groupe.getNombredutilisateur());

        }

         buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id_groupe = editTextDescription.getText().toString().trim();
                String dateDebut = editTextDateDebut.getText().toString().trim();
                String dateFin = editTextDateFin.getText().toString().trim();
                String nombreParticipants = editTextNombreParticipants.getText().toString().trim();

                // Créer une nouvelle instance de Inscription

                inscription = new Inscription();

                inscription.setId_user(user);
                inscription.setId_groupe(groupe);
                 Long id =InscriptionDao.saveInscription(inscription);
                 if ( id != -1){
                     showAlert("Inscription réussie", "Vous êtes bien inscrit à la groupe.");
                 } else {
                     showAlert("Échec de l'inscription", "Une erreur s'est produite lors de l'inscription. Veuillez réessayer.");
                 }

                setResult(RESULT_OK);
                finish();
            }
     private void showAlert(String title, String message) {
                 AlertDialog.Builder builder = new AlertDialog.Builder(ShowGroupeActivity.this);
                 builder.setTitle(title);
                 builder.setMessage(message);
                 builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         dialog.dismiss();
                     }
                 });
                 AlertDialog alert = builder.create();
                 alert.show();
             }
        });
    }



}