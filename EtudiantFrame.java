/**
 * @authors :  Christophe Desjardins, Tommy-Alexandre Lanteigne, Mathieu Paquette, Olivier Trudel. 
 * 
 */
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class EtudiantFrame extends JFrame implements ActionListener {

    JPanel panel;
    JLabel labelNom, labelPrenom, labelAnnee, labelEmptySpace;
    JTextField textField_Nom, textField_Prenom, textField_Annee;
    JRadioButton radioRegulier, radioDeficient, radioAdaptation;
    JButton boutonInscrire, boutonNoter, boutonAfficher, boutonStats, boutonReset, boutonQuit;
    JCheckBox couleurs;
    ButtonGroup group;

    Groupe unGroupe = new Groupe(1);

   public void resetEntries() {
    radioRegulier.setSelected(true);
    textField_Nom.setText(null);
    textField_Prenom.setText(null);
    textField_Annee.setText(null);
   }

   public EtudiantFrame(){
        setTitle("GestiEtudiant");
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        GridLayout gl = new GridLayout(7,2,20,20);
        EmptyBorder eb = new EmptyBorder(5, 5, 5, 5);
        panel.setLayout(gl);
        panel.setBorder(eb);

        // label 
    
        labelAnnee = new JLabel("Annee de Naissance : ");
        panel.add(labelAnnee);
        textField_Annee = new JTextField(10);
        panel.add(textField_Annee);

        labelEmptySpace = new JLabel(" ");
        panel.add(labelEmptySpace);

        labelPrenom = new JLabel("Prenom : ");
        panel.add(labelPrenom);
        textField_Prenom = new JTextField(10);
        panel.add(textField_Prenom);

        labelEmptySpace = new JLabel(" ");
        panel.add(labelEmptySpace);

        labelNom = new JLabel("Nom : ");
        panel.add(labelNom);
        textField_Nom = new JTextField(10);
        panel.add(textField_Nom);

        labelEmptySpace = new JLabel("");
        panel.add(labelEmptySpace);
        // radio
        group = new ButtonGroup();

        radioRegulier = new JRadioButton("Regulier");
        radioRegulier.setSelected(true);
        group.add(radioRegulier);
        panel.add(radioRegulier);

        radioAdaptation = new JRadioButton("Adaptation");
        group.add(radioAdaptation);
        panel.add(radioAdaptation);

        radioDeficient = new JRadioButton("Deficient");
        group.add(radioDeficient);
        panel.add(radioDeficient);

        // bouton
        boutonAfficher = new JButton("Afficher");
        panel.add(boutonAfficher);
        boutonAfficher.addActionListener(this);

        boutonInscrire = new JButton("Inscrire");
        panel.add(boutonInscrire);
        boutonInscrire.addActionListener(this);

        boutonNoter = new JButton("Noter");
        panel.add(boutonNoter);
        boutonNoter.addActionListener(this);

        boutonStats = new JButton("Stats");
        panel.add(boutonStats);
        boutonStats.addActionListener(this);

        boutonReset = new JButton("Reset");
        panel.add(boutonReset);
        boutonReset.addActionListener(this);

        boutonQuit = new JButton("Quit");
        panel.add(boutonQuit);
        boutonQuit.addActionListener(this);

        couleurs = new JCheckBox("couleurs");
        panel.add(couleurs);
        couleurs.addActionListener(this);

        add(panel);
   }

   @Override
   public void actionPerformed(ActionEvent event) {
        // var
        String unCodePermanent;
        String unNom;
        String unPrenom;
        int uneAnneeNaissance;
        double uneNote;
        String historique;
        String typeTrouble;
        int coteT;
        int coteS;

        if (event.getSource() == boutonInscrire) {

            try {
                uneAnneeNaissance = Integer.parseInt(textField_Annee.getText());
                unNom = textField_Nom.getText();
                unPrenom = textField_Prenom.getText();

                unCodePermanent = JOptionPane.showInputDialog("Entrez le code permanent");
                uneNote = 0; //Double.parseDouble(JOptionPane.showInputDialog("Entrez la note: "));
            
                if (radioRegulier.isSelected()) {
                    Etudiant unEtudiant = new Etudiant (unCodePermanent,unNom, unPrenom, uneAnneeNaissance, uneNote);
                    unGroupe.ajouterEtudiant(unEtudiant);
                }
                
                if (radioAdaptation.isSelected()) {
                    coteS = Integer.parseInt(JOptionPane.showInputDialog("Entrez la cote S : "));
                    historique = JOptionPane.showInputDialog("Entrez l'historique : ");
                    EtudiantAdaptation unEtudiantAdaptation = new EtudiantAdaptation(unCodePermanent, unNom, unPrenom, uneAnneeNaissance, uneNote, historique, coteS);
                    unGroupe.ajouterEtudiant(unEtudiantAdaptation);
                }

                if (radioDeficient.isSelected()) {
                    coteT = Integer.parseInt(JOptionPane.showInputDialog("Entrez la cote T : "));
                    typeTrouble = JOptionPane.showInputDialog("Entrez le type de trouble : ");
                    EtudiantDifficulte unEtudiantDifficulte = new EtudiantDifficulte(unCodePermanent, unNom, unPrenom, uneAnneeNaissance, uneNote, typeTrouble, coteT);
                    unGroupe.ajouterEtudiant(unEtudiantDifficulte);
                }
            }
            catch (Exception e) {
                //print seulement la premiere exception rencontree
                JOptionPane.showMessageDialog(null, e.getMessage());
            } 
            resetEntries();
        }

        if (event.getSource() == boutonAfficher) {
            JOptionPane.showMessageDialog(null, unGroupe.toString());
        }

        if (event.getSource() == boutonStats) {
            try {
                JOptionPane.showMessageDialog(null, "Moyenne: " + unGroupe.moyenne() +
                "\nTaux de succès: " + unGroupe.tauxSucces() +
                "\nMeilleure note: " + unGroupe.meilleur() +
                "\nÉcart type: " + unGroupe.ecartType() +
                "\nVariance: " + unGroupe.variance()) ;
            }
            catch (Exception e) {
                //print seulement la premiere exception rencontree
                JOptionPane.showMessageDialog(null, e.getMessage());
            } 

        }

        if (event.getSource() == boutonQuit) {
            JOptionPane.showMessageDialog(null, "Au revoir :) ");
            System.exit(0);
        }

        if (event.getSource() == boutonReset) {
            resetEntries();
        }

        if (event.getSource() == boutonNoter) {
            uneNote = 0;
            try {
                for (int i = 0; i < unGroupe.nbreEtudiants; i++) {
                    uneNote =  Double.parseDouble(JOptionPane.showInputDialog("Entrez la note de l'etudiant " + (i + 1)));
                    unGroupe.etudiants[i].setNote(uneNote);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (couleurs.isSelected()) {
            JOptionPane.showMessageDialog(null, "Attention, les couleurs sont affreuses");
            panel.setBackground(Color.BLACK);
            labelAnnee.setForeground(Color.ORANGE);
            textField_Annee.setForeground(Color.ORANGE);
            labelPrenom.setForeground(Color.CYAN);
            textField_Prenom.setForeground(Color.CYAN);
            labelNom.setForeground(Color.GREEN);
            textField_Nom.setForeground(Color.GREEN);
            radioRegulier.setBackground(Color.RED);
            radioAdaptation.setBackground(Color.RED);
            radioDeficient.setBackground(Color.RED);

        } else if (!couleurs.isSelected()) {
            panel.setBackground(null);
            labelAnnee.setForeground(null);
            textField_Annee.setForeground(null);
            labelPrenom.setForeground(null);
            textField_Prenom.setForeground(null);
            labelNom.setForeground(null);
            textField_Nom.setForeground(null);
            radioRegulier.setBackground(null);
            radioAdaptation.setBackground(null);
            radioDeficient.setBackground(null);
        }
    }
}
