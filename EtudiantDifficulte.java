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

public class EtudiantDifficulte extends Etudiant {
    
    //Attributs
    private String typeTrouble = "";
    private int coteT;
    
    //Mutateurs
    public void setCoteT(int uneCoteT){
        if (uneCoteT > 10 || uneCoteT < 0) {
            throw new ArithmeticException(" cote T hors limite ");
        }else{
            coteT = uneCoteT;
        }
    }

    public void setTroubleDiagnostique(String typeTrouble){
        this.typeTrouble = typeTrouble;
    }
    
    //Accesseurs
    public String getTroubleDiagnostique(){
        return typeTrouble;
    }

    public int getCoteT(){
        return coteT;
    }
    

    //constructeur avec parametres
    public EtudiantDifficulte(String codePermanent,String nom, String prenom, int anneeNaissance, double note, String typeTrouble, int coteT){
        super(codePermanent,nom,prenom,anneeNaissance,note);
        this.typeTrouble=typeTrouble;
        setCoteT(coteT);
    }

    @Override
    public boolean isSucces(){
        if (super.getNote() > 60) {
            return true;
        }else if (super.getNote() >= 50 && super.getNote() <= 60 && coteT < 5 ) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString(){
        String informations;
        informations = "\nCode permanent: " + super.getCodePermanent() + " - Nom: " + super.getNom() + " - Prénom: " + super.getPrenom() + " - Année de naissance: " + super.getAnneeNaissance() + " - Note: " + super.getNote() + " Is Sucess : " + isSucces() + " coteT : " + coteT + " typeTrouble " +typeTrouble;
        return informations;
    }

    @Override
    public double facturer(){
        return MONTANT + coteT*(MONTANT*10/100);
    }
    
    //Test avec un main

    public static void main (String[] args){
        EtudiantDifficulte unEtudiant = new EtudiantDifficulte("AA", "aa", "aa", 1990, 80, "miam", 4);
        
        //Affichage des attributs
        System.out.println("Code permanent: " + unEtudiant.getCodePermanent());
        System.out.println("Nom: " +unEtudiant.getNom());
        System.out.println("Prénom: " +unEtudiant.getPrenom());
        System.out.println("Année de naissance: " +unEtudiant.getAnneeNaissance());
        System.out.println("Note: " +unEtudiant.getNote());
        System.out.println("Trouble diagnostiqué avant modif: " +unEtudiant.getTroubleDiagnostique());
        unEtudiant.setTroubleDiagnostique("mal aux jambes");
        System.out.println("Trouble diagnostiqué apres modif: " +unEtudiant.getTroubleDiagnostique());
        System.out.println("Cote T avant modif : " +unEtudiant.getCoteT());
        unEtudiant.setCoteT(10);
        System.out.println("Cote T apres modif : " +unEtudiant.getCoteT());
        
        //Affichage des méthodes
        System.out.println("Succès:" + unEtudiant.isSucces());
        System.out.println("Note littérale:" + unEtudiant.noteLitterale());
        System.out.println("Informations de l'élève:" + unEtudiant.toString());
        System.out.println("Montant à payer:" + unEtudiant.facturer());

    }
}
