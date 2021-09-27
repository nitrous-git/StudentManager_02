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

public class Groupe {
    // Constante
    static final int MAX=30;

    //Attributs
    int numeroGroupe;
    int nbreEtudiants;
    Etudiant etudiants[];

    //Constructeur
    public Groupe (int unNumeroGroupe){
        numeroGroupe = unNumeroGroupe;
        etudiants = new Etudiant[MAX];
        nbreEtudiants = 0;
    }

    //Méthodes

    //Ajoute un étudiant
    public void ajouterEtudiant(Etudiant unEtudiant) throws ArrayIndexOutOfBoundsException{
        if (nbreEtudiants >= 30) {
            throw new ArrayIndexOutOfBoundsException("limite d'etudiants atteinte");
        }
        else{
            etudiants[nbreEtudiants] = unEtudiant;
            nbreEtudiants += 1;
        }

    }

    //Retourne la moyenne du groupe
    public double moyenne(){
        double totalNotes = 0;
        for (int i =0; i<nbreEtudiants; i++)
            totalNotes += etudiants[i].getNote();
        return totalNotes/nbreEtudiants;
    }

    //Retourne le taux de succès du groupe
    public double tauxSucces(){
        int nombreSucces = 0;
       for (int i =0; i<nbreEtudiants; i++)
           if(etudiants[i].isSucces())
               nombreSucces++ ;
       return (double)nombreSucces/nbreEtudiants;
    }

    //Retourne la meilleure note
    public double meilleur(){
        double meilleureNote = etudiants[0].getNote();
        for (int i =1; i<nbreEtudiants; i++)
            if (etudiants[i].getNote() > meilleureNote)
               meilleureNote = etudiants[i].getNote();
        return meilleureNote;
    }

    //Retourne la variance
    public double variance() throws ArithmeticException{
        if (etudiants.length == 0) {
            throw new ArithmeticException(" division par zero ");
        }
        else{
            double ecartMoyenne = 0;
            for (int i =0; i<nbreEtudiants; i++)
                ecartMoyenne += Math.pow(etudiants[i].getNote() - moyenne() , 2);
            return ecartMoyenne/nbreEtudiants;
        }

    }

    //Retourne l'écart type
    public double ecartType(){
        if (etudiants.length == 0) {
            throw new ArithmeticException(" division par zero ");
        }
        else{
            return Math.sqrt(variance());
        }
    }

    //Retourne les informations du groupe
    public String toString(){
        String informations = "";
        for (int i =0; i<nbreEtudiants; i++)
            informations += etudiants[i].toString();
        return informations;
    }

    //Retourne la facture
    public String factureString(){
        String factureInfo = "";
        for (int i =0; i<nbreEtudiants; i++)
            factureInfo += "\nCode : "+etudiants[i].getCodePermanent()+" Nom : "+etudiants[i].getNom()+ " Prenom : "+ etudiants[i].getPrenom()+ " Note : "+ etudiants[i].getNote()+ " Facture : "+ etudiants[i].facturer();
        return factureInfo;
    }

        //Test avec un main
        public static void main (String[] args){
            Groupe unGroupe = new Groupe (22);
            final String codeString = "Un code permanent";
            unGroupe.ajouterEtudiant(new Etudiant ("AA", "aa", "aa", 2000, 80));
            unGroupe.ajouterEtudiant(new Etudiant ("BB","bb", "bb", 2000, 90));
            unGroupe.ajouterEtudiant(new Etudiant ("CC","cc", "cc", 2000, 50));
            unGroupe.ajouterEtudiant(new Etudiant ("DD","dd", "dd", 2000, 60));
            unGroupe.ajouterEtudiant(new EtudiantAdaptation(codeString, "sans-famille", "Rémi", 1997, 60, "lost his parents", 5));
            unGroupe.ajouterEtudiant(new EtudiantAdaptation(codeString, "Lnat", "Tommy", 1997, 55, "mal de ventre", 2));
            unGroupe.ajouterEtudiant(new EtudiantDifficulte(codeString, "pauvre", "Nicko", 1997, 90, "vie dans la rue", 6));
            unGroupe.ajouterEtudiant(new EtudiantDifficulte(codeString, "lancelot", "Maxime", 1997, 45, "win with excalibur", 2));

            System.out.println("Moyenne: " + unGroupe.moyenne());
            System.out.println("Taux de succès: " + unGroupe.tauxSucces());
            System.out.println("Meilleure note: " + unGroupe.meilleur());
            System.out.println("Écart type: " + unGroupe.ecartType());
            System.out.println("Variance: " + unGroupe.variance());
            System.out.println("Informations: " + unGroupe.toString());
            System.out.println("Informations facturation: " + unGroupe.factureString());
        }
}
