package com.example.tp2;

import java.util.Scanner;
import java.util.Random;
public class main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Genre genre = Genre.Non_déterminé;
        System.out.println("Entrez le nom de votre Héros: ");
        String nom = scan.nextLine();
        System.out.println("Entrez le genre de votre héros:");
        System.out.println("1 : Homme | 2 : Femme | 3 : Non déterminé");

        int choix = scan.nextInt();
        if(choix == 1){
            genre = Genre.Homme;
        }

        else if(choix == 2){
            genre = Genre.Femme;
        }

        while(choix != 1 && choix != 2 && choix != 3){

            System.out.println("SVP Entrez 1, 2 ou 3");
            choix = scan.nextInt();

            if(choix == 1){
                genre = Genre.Homme;
            }

            else if(choix == 2){
                genre = Genre.Femme;
            }

        }


        System.out.println("Entrez le nombre de munitions de votre héros: ");
        int munitions = scan.nextInt();
        System.out.println("Entrez la santé de votre héros: ");
        int sante = scan.nextInt();
        System.out.println("Votre héros, " + nom + " de genre " + genre + " a été créé" );

        Héros heros = new Héros(munitions,sante, nom, genre);
        heros.AfficherÉtat();
        scan.nextLine();

        System.out.println("Entrez la race des monstres: ");
        String race = scan.nextLine();
        System.out.println("Entrez le nombre de monstres:");
        int nbMonstres = scan.nextInt();
        Monstre[] tabMonstre = new Monstre[nbMonstres];


        tabMonstre = GénérerTabMonstre(nbMonstres, race);

        AfficherTableauMonstre(tabMonstre, race);

        //Début de la partie
        Jouer(nbMonstres, heros, tabMonstre, race, scan,sante,munitions);
        scan.close();


    }

    static Monstre[] GénérerTabMonstre(int nbMonstres,String race){
        Random random = new Random(); //pour chosir aléatoirement si monstre est gentil ou méchant
        boolean estMéchant;
        Monstre[] tabMonstre = new Monstre[nbMonstres];
        for(int i = 0; i < nbMonstres; i++){
            estMéchant = random.nextBoolean();
            if(estMéchant){
                tabMonstre[i] = new Monstre(1, 1, race, GentilMechant.Méchant);
            }
            else{
                tabMonstre[i] = new Monstre(1, 1, race, GentilMechant.Gentil);
            }

        }
        return tabMonstre;
    }

    static void Jouer(int nbMonstres, Héros heros, Monstre[] tabMonstre, String race, Scanner scan,int sante, int munitions){
        int munitionsRestante = nbMonstres; //car 1 munition par monstres
        int numBataille = 1;
        int resultatAttaque;

        while(heros.GetSanté() > 0 && nbMonstres > 0 && heros.GetMunitions() > 0 && munitionsRestante > 0){
            System.out.println("----------Bataille " + numBataille + "----------");
            resultatAttaque = heros.Attaque(scan);
            nbMonstres -= resultatAttaque;
            if(nbMonstres <= 0){
                break;
            }


            CalculAttaque(resultatAttaque, tabMonstre);

            System.out.println("Attaque des monstres!");

            resultatAttaque = AttaqueMonstre(tabMonstre);
            heros.santé -= resultatAttaque;

            munitionsRestante = CalculerMunitions(tabMonstre);
            System.out.println("État de votre héros: ");
            heros.AfficherÉtat();
            System.out.println("État des monstres: ");
            AfficherTableauMonstre(tabMonstre, race);
            numBataille++;

        }

        if(heros.GetSanté() <= 0){
            System.out.println("Désolé, vous avez perdu. Votre héro est mort!");
        }
        else if(heros.GetMunitions() <= 0){
            System.out.println("Désolé, vous avez perdu. Vous n'avez plus de munitions!");
        }

        else if(nbMonstres <= 0){
            System.out.println("Bravo! Vous avez gagné! Tous les monstres sont morts!");
        }

        else if(munitionsRestante == 0){
            System.out.println("Bravo! Vous avez gagné! Les monstres n'ont plus de munitions!");
        }

        scan.nextLine();
        System.out.println("Voulez vous rejouer? (o/n)");
        String reponse = scan.nextLine();

        if(reponse.equals("o")){
            heros.santé = sante;
            heros.munitions = munitions;
            System.out.println("Entrez le nombre de monstres pour cette nouvelle partie: ");
            nbMonstres = scan.nextInt();
            tabMonstre = GénérerTabMonstre(nbMonstres, race);
            System.out.println("État de votre héros: ");
            heros.AfficherÉtat();
            System.out.println("Nouveau groupe de monstres: ");
            AfficherTableauMonstre(tabMonstre, race);
            Jouer(nbMonstres, heros, tabMonstre, race, scan, sante, munitions);
        }

        else if (reponse.equals("n")){
            System.out.println("Au revoir!");
        }
    }

    static void CalculAttaque(int resultat, Monstre[] tabMonstre){
        Random random = new Random();
        int index;
        for(int i = 0; i < resultat; i++){
            index = random.nextInt(tabMonstre.length);
            while(tabMonstre[index] == null){
                index = random.nextInt(tabMonstre.length);
            }
            tabMonstre[index] = null;

        }
        System.out.println(resultat + " monstres touchés");
    }

    static void AfficherTableauMonstre(Monstre[] tab, String race){
        for(int i = 0; i < tab.length; i++){
            if(tab[i] != null){
                System.out.println("Monstre de race " + race + " | Monstre " + tab[i].GetType());
                tab[i].AfficherÉtat();
                System.out.println();
            }
        }
    }

    static int AttaqueMonstre(Monstre[] tab){
        Random random = new Random();
        boolean estValide = true;
        int index = random.nextInt(tab.length);
        int resultat = 0;
        //voir si l'index est valide (si il y un monstre dans la case)
        if(tab[index] == null){
            estValide = false;
        }

        while(!estValide){
            index = random.nextInt(tab.length);
            if(tab[index] != null){
                estValide = true;
            }
        }

        resultat = tab[index].Attaque();
        return resultat;



    }

    static int CalculerMunitions(Monstre[] tab){
        int munitions = 0;
        for(int i = 0; i < tab.length; i++){
            if(tab[i] != null){
                munitions += tab[i].GetMunitions();
            }
        }
        return munitions;
    }


    public static int tryParseInt(String str,int defaultVal){
        int res;
        try{
            res =  Integer.parseInt(str);
        }
        catch (Exception e){
            res = defaultVal;
        }
        return res;
    }


}
