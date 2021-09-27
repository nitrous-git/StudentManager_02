/**
 * 
 * @authors :  Christophe Desjardins, Tommy-Alexandre Lanteigne, Mathieu Paquette, Olivier Trudel. 
 * 
 * Partage des tâches : 
 * Étape 1 : Tommy (Integrer l'heritage)
 * Étape 2 : Olivier (Surcharge - Surdefinition)
 * Étape 3 : Christophe (Classes abstraites et interfaces)
 * Étape 4 : Mathieu (Traitement des exceptions)
 */

public interface Facture {
    //Attributs

    int MONTANT = 4000;
    int TAUX = 10;

    //Méthode abstraite pour facturer

    public abstract double facturer();
}