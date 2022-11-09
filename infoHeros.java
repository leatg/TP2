package com.example.tp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class infoHeros extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        EditText editTextNom = findViewById(R.id.editTextNom);
        RadioGroup radio = findViewById(R.id.radioGroupGenre);
        EditText editTextSante = findViewById(R.id.editTextSante);
        EditText editTextMunitions = findViewById(R.id.editTextMunitions);
        Button btnSuivant = findViewById(R.id.btnSuivantHeros);


        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RadioButton radioGenre = findViewById(radio.getCheckedRadioButtonId());
                String nom = editTextNom.getText().toString();
                int sante = main.tryParseInt(editTextSante.getText().toString(),-1);
                int munitions = main.tryParseInt(editTextMunitions.getText().toString(),-1);
                if(radioGenre == null || nom == null || sante == -1 || munitions == -1){
                    Toast toast = Toast.makeText(infoHeros.this,"Veuillez remplir tous les champs",Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    String stringGenre = radioGenre.getText().toString();
                    Genre genre = getGenre(stringGenre);
                    Héros heros = new Héros(munitions,sante,nom,genre);
                    Intent intent = new Intent(infoHeros.this,infoMonstres.class);
                    intent.putExtra("heros",heros);
                    startActivity(intent);
                }
            }
        });



    }

    public static Genre getGenre(String genre){
        switch(genre){
            case "Homme":
                return Genre.Homme;
            case "Femme":
                return Genre.Femme;
            case "Indéterminé":
                return Genre.Non_déterminé;
            default:
                return null;
        }
    }


}