package com.example.festival;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.festival.dao.UserDao;
import com.example.festival.entity.User;

public class EditUserActivity extends AppCompatActivity {

    private EditText editTextNom, editTextPrenom , editTextEmail;
    private Button buttonSaveChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        editTextNom = findViewById(R.id.editTextEditNom);
        editTextPrenom = findViewById(R.id.editTextEditPrenom);
        editTextEmail = findViewById(R.id.editTextEditEmail);
        buttonSaveChanges = findViewById(R.id.buttonUpdate);

        // Récupérez l'ID de l'utilisateur ou l'objet User depuis l'Intent
        // Exemple avec un ID :
        User user =  (User) getIntent().getSerializableExtra("user_id");
        editTextEmail.setText(user.getEmail());
        editTextNom.setText(user.getNom());
        editTextPrenom.setText(user.getPrenom());
        // Utilisez cet ID pour charger les données utilisateur depuis votre base de données
        // Affichez les données existantes de l'utilisateur dans les EditText

        buttonSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Récupérez les valeurs modifiées
                String nom = editTextNom.getText().toString().trim();
                String prenom = editTextPrenom.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                // Validez les champs et mettez à jour l'utilisateur dans la base de données

                user.setEmail(nom);
                user.setPrenom(prenom);
                user.setEmail(email);
                UserDao.updateUser(user);
                // Retournez le résultat à l'activité précédente si nécessaire
                setResult(RESULT_OK);
                finish();
            }
        });
    }
}

