/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tic_tac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author PC
 */
public class Game {
    //creation de la fenetre du jeu
    int boardWith=600;
    int boardheight=650; //50px sera pour le texte en haut d ela fenetre 
    
    JFrame frame=new JFrame("Tic-Tac Game");
    //creation de jlabel et du jpanel
    JLabel text=new JLabel();
    JLabel score=new JLabel();
    JPanel textpanel=new JPanel();
    JPanel scorepanel=new JPanel();
    JPanel gamepanel=new JPanel();

    //creation des boutton qui seront en quelque sorte les cases de notre jeu
    JButton boutton[][] = new JButton[3][3];

    String joueurX="X";
    String joueurO="O";
    String joueurActuel=joueurX;
    String joueur1;
    String joueur2;
    String NomjoueurActuel;
    boolean gameover=false;
    boolean overcondition;
    int turns;
    int score1=0,score2=0;
    
    public Game( String joueur1,String joueur2){
        this.joueur1=joueur1;
        this.joueur2=joueur2;
        //parametre du frame qui va saccueilli le jeu
        frame.setVisible(true);
        frame.setSize(boardWith,boardheight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        //parametre du titre du jeu
        text.setBackground(Color.DARK_GRAY);
        text.setForeground(Color.white);
        text.setFont(new Font("Arila",Font.BOLD,50));
        text.setHorizontalAlignment(JLabel.CENTER);
        text.setText("Tic Tac Game");
        text.setOpaque(true);
        
        //parametre du score du jeu
        score.setBackground(Color.DARK_GRAY);
        score.setForeground(Color.white);
        score.setFont(new Font("Arila",Font.BOLD,25));
        score.setHorizontalAlignment(JLabel.CENTER);
        score.setText("Player "+joueur1+ " Score:"+score1+"      Player "+joueur2+ " Score:"+score2);
        score.setOpaque(true);
        
        textpanel.setLayout(new BorderLayout());
        textpanel.add(text);
        frame.add(textpanel,BorderLayout.NORTH);
        
        scorepanel.setLayout(new BorderLayout());
        scorepanel.add(score);
        frame.add(scorepanel,BorderLayout.SOUTH);


        gamepanel.setLayout(new GridLayout(3,3));
        gamepanel.setBackground(Color.DARK_GRAY);
        frame.add(gamepanel);
        
        for(int i=0;i<boutton.length;i++){
            for(int j=0;j<boutton.length;j++){
                JButton tile=new JButton();
                boutton[i][j]=tile;
                gamepanel.add(tile);
                
                tile.setBackground(Color.DARK_GRAY);
                tile.setForeground(Color.WHITE);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
                
                tile.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(gameover) return;
                       JButton tile=(JButton)e.getSource();
                        if(tile.getText().equals("")){   //cette condition permet de ne pas jouer d facon indeterminer
                           tile.setText(joueurActuel);
                           turns++;
                           CheckVainqueur();
                           if(!gameover){
                           if(joueurActuel==joueurX){
                               joueurActuel=joueurO;
                               NomjoueurActuel=joueur2;
                           }
                           else{
                               joueurActuel=joueurX;
                               NomjoueurActuel=joueur1;
                           }
                           text.setText(NomjoueurActuel+ "'s turn.");
                       }
                           
                       }
                    /*tile.setText(joueurActuel);
                    turns++;
                    CheckVainqueur();
                    if(!gameover){
                        if(joueurActuel==joueurX){
                            joueurActuel=joueurO;
                            NomjoueurActuel=joueur2;
                        }
                        else{
                            joueurActuel=joueurX;
                            NomjoueurActuel=joueur1;
                        }
                        text.setText(NomjoueurActuel+ "'s turn.");
                    }*/
                       
                    }
                });         
       }
    }   
}
    
    private void CheckVainqueur(){
        //on va verifier si soit les "X" ou les "O" on reussir a former une ligne horizontale alors celui la  sera le vainqueur
        for (int i = 0; i < boutton.length; i++) {
            if (boutton[i][0].getText() == ""){
                continue;
            }
            if (boutton[i][0].getText()==boutton[i][1].getText() && boutton[i][1].getText()==boutton[i][2].getText()) {
                if(boutton[i][0].getText().equals("X")){
                    score1++;
                    setGoal(joueur1);
                }
                else{
                   score2++;
                   setGoal(joueur2);
                }
                /*for (int j = 0; j < boutton.length; j++) {
                    setVainqueur(boutton[i][j]);
                }*/
                
                /*gameover=true;
                return;*/
                boutton[i][0].setText("");
                boutton[i][1].setText("");
                boutton[i][2].setText("");
            }
        }
        
        //on va verifier si soit les "X" ou les "O" on reussir a former une ligne verticale alors celui la  sera le vainqueur
        for (int i = 0; i < boutton.length; i++) {
            if (boutton[0][i].getText() == ""){
                continue;
            }
            if (boutton[0][i].getText()==boutton[1][i].getText() && boutton[1][i].getText()==boutton[2][i].getText()) {
                if(boutton[0][i].getText().equals("X")){
                    score1++;
                    setGoal(joueur1);
                }
                else{
                   score2++; 
                   setGoal(joueur2);
                }
                /*for (int j = 0; j < boutton.length; j++) {
                    setVainqueur(boutton[j][i]);
                }*/
                /*gameover=true;
                return;*/
                boutton[0][i].setText("");
                boutton[1][i].setText("");
                boutton[2][i].setText("");
            }
        }
        
        //on va verifier si soit les "X" ou les "O" on reussir a former une ligne diagonale principale alors celui la  sera le vainqueur
        if (boutton[0][0].getText().equals(boutton[1][1].getText()) && boutton[1][1].getText().equals(boutton[2][2].getText()) && !boutton[0][0].getText().equals("")) {
            if(boutton[0][0].getText().equals("X")){
                score1++;
                setGoal(joueur1);
            }
            else{
                score2++;
                setGoal(joueur2);
            }
            /*for (int j = 0; j < boutton.length; j++) {
                setVainqueur(boutton[j][j]);
            }*/
            /*gameover=true;
            return;*/
            boutton[0][0].setText("");
            boutton[1][1].setText("");
            boutton[2][2].setText("");
        }

        //on va verifier si soit les "X" ou les "O" on reussir a former une ligne diagonale secondaire alors celui la  sera le vainqueur
        if (boutton[2][0].getText().equals(boutton[1][1].getText()) && boutton[1][1].getText().equals(boutton[0][2].getText()) && !boutton[2][0].getText().equals("")) {
            int i=2;
            if(boutton[2][0].getText().equals("X")){
                score1++; 
                setGoal(joueur1);
            }
            else{
                score2++; 
                setGoal(joueur2);
            }
            /*for (int j = 0; j < boutton.length; j++) {
                setVainqueur(boutton[i][j]);
                i--;
            }*/
            /*gameover=true;
            return;*/
            boutton[2][0].setText("");
            boutton[1][1].setText("");
            boutton[0][2].setText("");
        }
        
        checkGridFull();
        
        
    }
    private void setVainqueur(JButton tile){
            /*tile.setForeground(Color.GREEN);
            tile.setBackground(Color.gray);
            text.setText(NomjoueurActuel +" is the Winner");
            text.setForeground(Color.GREEN);
            score.setText("Player "+joueur1+ " Score:"+score1+"      Player  "+joueur2+ " Score:"+score2);*/
    }
    private void setGoal(String joueur){
            score.setText("Player "+joueur1+ " Score:"+score1+"      Player  "+joueur2+ " Score:"+score2);
            showTemporaryMessage(frame,joueur +" set the gaol" , 2000);
    }
   
    private void setTile(JButton tile){
        tile.setForeground(Color.red);
        tile.setBackground(Color.gray);
        text.setText("Game Over try again!!!");

    }
    private void checkGridFull(){
        overcondition=true;
        for (int i = 0; i < boutton.length; i++) {
            for (int j = 0; j < boutton.length; j++) {
                if(boutton[i][j].getText().equals("")){
                    overcondition=false;
                    break;
                }
            }
            if (!overcondition){
                break;
            }
        }
        
        if(overcondition){
            for (int i = 0; i < boutton.length; i++) {
                for (int j = 0; j < boutton.length; j++) {
                     setTile(boutton[i][j]);
                }
            }
            gameover = true; // ← empêche les autres actions après
            text.setText("Game Over try again!!!"); // ← mieux ici pour plus de clarté
        }
        /*gameover=true;
        return;*/
    }
    
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
}
