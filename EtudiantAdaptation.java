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

public class EtudiantAdaptation extends Etudiant {

    //Attributs
    private String historique="";
    private int coteS;
    
    //Méthode exception
    public void setCoteS(int uneCoteS) throws ArithmeticException{ 
        if (uneCoteS > 10 || uneCoteS < 0) {
            throw new ArithmeticException(" cote S hors limite ");
        }else{
            coteS = uneCoteS;
        }
    }

    //Mutateurs
    public void setHistorique(String historique){
        this.historique = historique;
    }
    
    //Accesseurs
    public String getHistorique() {
        return historique;
    }

    public int getCoteS() {
        return coteS;
    }

    //Constructeur avec parametres
    public EtudiantAdaptation(String codePermanent,String nom, String prenom, int anneeNaissance, double note, String historique, int coteS){
        super(codePermanent,nom,prenom,anneeNaissance,note);
        this.historique=historique;
        setCoteS(coteS);
    }

    @Override
    public boolean isSucces(){
        boolean succ = false;
        if (super.getNote() > 60 || (super.getNote() >= 55 && super.getNote() <= 60 && coteS < 2)) { //les deux if statements sont combines ici pour simplifier le code
            succ = true ;
        }
        return succ; // pas besoin de else, la valeur par defaut est false
    }

    @Override
    public String toString(){
        String informations;
        informations = "\nCode permanent: " + super.getCodePermanent() + " - Nom: " + super.getNom() + " - Prénom: " + super.getPrenom() + " - Année de naissance: " + super.getAnneeNaissance() + " - Note: " + super.getNote() + " Is Sucess : " + isSucces() + " coteS : " + coteS + " historique : " +historique;
        return informations;
    }
    
    @Override
    public double facturer() {
        return MONTANT + coteS * ((double) MONTANT * 20 / 100);
    }
    
    //Test avec un main
    
    public static void main (String[] args){
        EtudiantAdaptation unEtudiant = new EtudiantAdaptation("AA", "aa", "aa", 1900, 55, "allo", 3);
        
        //Affichage des attributs
        System.out.println("Code permanent: " + unEtudiant.getCodePermanent());
        System.out.println("Nom: " +unEtudiant.getNom());
        System.out.println("Prénom: " +unEtudiant.getPrenom());
        System.out.println("Année de naissance: " +unEtudiant.getAnneeNaissance());
        System.out.println("Note: " +unEtudiant.getNote());
        System.out.println("Historique avant modif: " +unEtudiant.getHistorique());
        unEtudiant.setHistorique("mal aux jambes");
        System.out.println("Historique apres modif: " +unEtudiant.getHistorique());
        System.out.println("Cote S avant modif : " +unEtudiant.getCoteS());
        unEtudiant.setCoteS(5);
        System.out.println("Cote S apres modif : " +unEtudiant.getCoteS());

        //Affichage des méthodes
        System.out.println("Succès:" + unEtudiant.isSucces());
        System.out.println("Note littérale:" + unEtudiant.noteLitterale());
        System.out.println("Informations de l'élève:" + unEtudiant.toString());
        System.out.println("Montant à payer:" + unEtudiant.facturer());
    }
}
