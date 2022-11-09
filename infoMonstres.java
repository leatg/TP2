package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class infoMonstres extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_monstres);

        EditText editTextRace = findViewById(R.id.editTextRace);
        EditText editTextNb = findViewById(R.id.editTextNbMonstre);
        Button btnSuivant = findViewById(R.id.btnSuivantMonstres);
        Héros heros = (Héros) getIntent().getSerializableExtra("heros");
        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String race = editTextRace.getText().toString();
                int nombre = main.tryParseInt(editTextNb.getText().toString(),-1);
                if(race == null || nombre == -1){
                    Toast toast = Toast.makeText(infoMonstres.this,"Veuillez remplir tous les champs",Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Monstre[] tabMonstre = main.GénérerTabMonstre(nombre,race);
                    //Intent intent = new Intent(infoMonstres.this,autre classe);
                    //intent.putExtra("monstres",tabMonstre");
                    //intent.putExtra("heros",heros);
                    //startActivity(intent);

                }
            }
        });
    }
}