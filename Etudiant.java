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

public class Etudiant implements Facture {
    //Attributs
    private String codePermanent;
    private String nom;
    private String prenom;
    private int anneeNaissance;
    private double note ;
    private int ANNEECOURANTE = 2021;

    //Accesseurs
    public String getCodePermanent(){
        return codePermanent;
    }

    public String getNom(){
        return nom;
    }

    public String getPrenom(){
        return prenom;
    }

    public int getAnneeNaissance(){
        return anneeNaissance;
    }

    public double getNote(){
        return note;
    }

    //Mutateurs
    public void setCodePermanent(String unCodePermanent){
        codePermanent = unCodePermanent;
    }

    public void setNom(String unNom){
        nom = unNom;
    }

    public void setPrenom(String unPrenom){
        prenom = unPrenom;
    }

    public void setAnneeNaissance(int uneAnneeNaissance) throws ArithmeticException{
        if (uneAnneeNaissance > (ANNEECOURANTE - 10 )) {
            throw new ArithmeticException("Anne de naissance trop eleve !!!");
        }else {
            anneeNaissance = uneAnneeNaissance;
        }

    }

    public void setNote(double uneNote) throws ArithmeticException{
        if (uneNote > 100 || uneNote < 0) {
            throw new ArithmeticException("note hors de l'intervalle");
        }
        else{
            note = uneNote;
        }
    }



    //constructeurs
    public Etudiant (String codePermanent,String nom, String prenom, int anneeNaissance, double note ){
        this(codePermanent, nom, prenom);
        setAnneeNaissance(anneeNaissance);
        setNote(note);
    }
    
    public Etudiant(String codePermanent,String nom, String prenom){
        setCodePermanent(codePermanent);
        setNom(nom);
        setPrenom(prenom);
    }

    public Etudiant(){
    }

    // Méthodes
    //Retourne si c'est un succès ou non
    public boolean isSucces(){
        final int SEUIL_REUSSITE = 60;
        return (note >= SEUIL_REUSSITE);
    }

    //Retourne la note littérale d'un étudiant
    public char noteLitterale(){
        final int LIMITE1 = 0;
        final int LIMITE2 = 40;
        final int LIMITE3 = 60;
        final int LIMITE4 = 80;
        final int LIMITE5 = 100;
        if ( (note >= LIMITE1) && (note <= LIMITE2) )
            return 'D';
        else
        if ( (note >LIMITE2) && (note <= LIMITE3) )
            return 'C';
        else
        if ( (note > LIMITE3) && (note <= LIMITE4) )
            return 'B';
        else
        if ( (note >LIMITE4) && (note <= LIMITE5) )
            return 'A';
        else
            return ' '; // car exige un return
    }
    //Retourne les informations d'un élève
    public String toString(){
        String informations;
        informations = "\nCode permanent: " + codePermanent + " - Nom: " + nom + " - Prénom: " + prenom + " - Année de naissance: " + anneeNaissance + " - Note: " + note ;
        return informations;
    }

    @Override
    public double facturer(){
        return MONTANT;
    }

    //Test avec un main
    public static void main (String[] args){
        Etudiant unEtudiant = new Etudiant("AA", "aa", "aa", 1900, 80);
        //Affichage des attributs
        System.out.println("Code permanent: " + unEtudiant.getCodePermanent());
        System.out.println("Nom: " +unEtudiant.getNom());
        System.out.println("Prénom: " +unEtudiant.getPrenom());
        System.out.println("Année de naissance: " +unEtudiant.getAnneeNaissance());
        System.out.println("Note: " +unEtudiant.getNote());

        //Affichage des méthodes
        System.out.println("Succès:" + unEtudiant.isSucces());
        System.out.println("Note littérale:" + unEtudiant.noteLitterale());
        System.out.println("Informations de l'élève:" + unEtudiant.toString());
        System.out.println("Montant à payer:" + unEtudiant.facturer());
    }
}
