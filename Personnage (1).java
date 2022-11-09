package com.example.tp2;

import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;
enum GentilMechant {Gentil,Méchant}
enum Genre {Homme,Femme,Non_déterminé}

public class Personnage implements Ijeu, Serializable {
    protected int munitions;
    protected int santé;

    public int GetMunitions(){
        return munitions;
    }

    public int GetSanté(){
        return santé;
    }

    public int Attaque(Scanner lire){
        return 0;
    }

   public void AfficherÉtat(){

   }

    public Personnage(int munitions, int santé){
        this.munitions = munitions;
        this.santé = santé;
    }
}

class Héros extends Personnage{
    private String nom;
    private Genre genre;

    public String GetNom(){
        return nom;
    }

    public Genre GetGenre(){
        return genre;
    }

    public Héros(int munitions, int santé, String nom, Genre genre){
        super(munitions, santé);
        this.nom = nom;
        this.genre = genre;
    }

    @Override
    public int Attaque(Scanner lire){
        int nbMunitions;
        Random random = new Random();
        System.out.println("C'est à votre tour d'attaquer! Voulez vous attaquer avez 1, 2 ou 3 munitions?");
        nbMunitions = lire.nextInt();

        while(nbMunitions > 3 || nbMunitions < 1 || nbMunitions > munitions){

            if(nbMunitions > munitions && nbMunitions <= 3){
                System.out.println("Vous n'avez pas assez de munitions! Choississez en moins.");
                nbMunitions = lire.nextInt();
            }

            else{
                System.out.println("Vous devez attaquer avec 1, 2 ou 3 munitions seulement");
                nbMunitions = lire.nextInt();
            }
        }

        int monstresTouches = random.nextInt(nbMunitions + 1);
        munitions -= nbMunitions;
        return monstresTouches;
    }


}

class Monstre extends Personnage{
    private GentilMechant type;
    private String race;

    public GentilMechant GetType(){
        return type;
    }

    public String GetRace(){
        return race;
    }

    public Monstre(int munitions, int santé, String race, GentilMechant type){
        super(munitions, santé);
        this.race = race;
        type = type;
    }

    public int Attaque(){
        if(type == GentilMechant.Méchant){
            if(munitions > 0){
                System.out.println("Votre héros a été attaqué par un monstre méchant! -1 de vie!");
                munitions -= 1;
                return 1;
            }

            System.out.println("Le monstre qui vous a attaqué n'avais plus de munitions!");
            return 0;

        }
        System.out.println("Votre héros n'a pas été attaqué, car le monstre était gentil!");
        return 0;
    }


}

