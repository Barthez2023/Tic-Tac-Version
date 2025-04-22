# Tic-Tac-Version
Dans ce ripo nous allons creer le jeu tic-tac avec plus de fonctionnalite

# 18 04 2025
Nous avons ameliorer notre tic tac les score sont maintenqnt afficher te les jouurs on la possibilités de s'enreigistrer
Nous devons implementer un condition d'arret a un certain nombre de score et gerre le tableau de jeu quand quelque marque un but 
avant de passer la main au joueur suivant 

# 22 04 2025
Le jeu se joue jsuqua ce que tous le scases ce se remplisse .Et a chaque fois que un joueur marque un point une boite de dialogue s'affiche pour signaler et elle dure 1s. Lorsque toute les cases sont pleines on entre dans la condition game over toutees les cases deviennent rouges et le jeu est termine.

Maintenent nous allons implementer une boite de dialogue qui vas s'afficher quand le jeu sera game over  et declarer qui est le vainceurt avec son score.


pour implementer la boite d edialogue qui disparait apres un certain notre de temps nous avons utilise la propriet (Timer) de java.util
import java.util.Timer;
import java.util.TimerTask;

public static void showTemporaryMessage(Component parent, String message, int durationMillis) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE);
        JDialog dialog = optionPane.createDialog(parent, "Info");

        // Timer pour fermer la fenêtre automatiquement
        new Timer().schedule(new TimerTask() {
            public void run() {
                dialog.setVisible(false);
                dialog.dispose();
            }
        }, durationMillis);

        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setModal(false); // optionnel : n'empêche pas l'utilisateur d'interagir avec le reste
        dialog.setVisible(true);
    }

    showTemporaryMessage(frame,joueur +" set the gaol" , 1000);  // voici un appel de cette  fonction.S

