package bj.ifri.studentmanagement.app;

import bj.ifri.studentmanagement.view.MainFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 * Point d'entrée de l'application de gestion des étudiants.
 */
public final class Main {

    private Main() {
        // Classe utilitaire : pas d'instanciation.
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Impossible d'appliquer le Look and Feel du système : " + e.getMessage());
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame fenetre = new MainFrame();
                fenetre.setVisible(true);
            }
        });
    }
}
