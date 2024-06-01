package com.ACMD.app.Graphic_Layer.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;

import com.ACMD.app.Engine_Layer.ParsePath;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;


public class GameFrame extends javax.swing.JFrame implements Frame {
    //Inizializzazione delle variabili del frame ( per altre, più complesse guardare initComponents() )
    int globalRiga=0; //Usate per il test
    int globalColonna=0; // Usate per i test
    
    Color backGround = new Color(150,150,150);
    Color lightBackground = new Color(204, 204, 204);

    ParsePath pathParser=new ParsePath();
    final String imageDirPath = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Graphic_Layer\\Images\\";
    boolean youWin=false;
    String mapIconPath     = ParsePath.getPath(imageDirPath, "Mappa_Definitiva.png");
    String mapIconPathOpen = ParsePath.getPath(imageDirPath, "Mappa_DefinitivaOpen.png");
    
    String playerIcon      = ParsePath.getPath(imageDirPath,"PersonIcon.jpg");
    String keyIcon         = ParsePath.getPath(imageDirPath,"Key.jpg");
    String musicIcon       = ParsePath.getPath(imageDirPath,"Music.png");
    final int numberRows   =20;
    final int numberCols   =20; 
    final float fontSize   =16f;
    /**
     * Costruttore che genera il gameFrame, inizializza le compontenti, ne setta il background 
     * 
     */
    public GameFrame() {
                    this.getContentPane().setBackground(backGround);
                    initComponents(); // Crea la finestra vuota, senza aggiugnere la mappa          
                    //addMapPicture(mapIconPath); // Aggiunge l'immagine (grafica)
                    //addPlayerPosition(1,19,playerIcon); 
                    move(new Coordinates(1,19));
                    updateGraphics(new Coordinates(1,19));
    }                   
    /**
     * Moves the player position from the current one to the desired one: 
     * @param x The position from the top left corner along the x axis, values 0 to 19
     * @param y The position from the top left corner along the y axis, values 0 to 19
     * @throws IllegalArgumentException x or y not in the range [0,19]
     */
    public void move (Coordinates c) throws IllegalArgumentException
    {
        int x=c.getX(); 
        int y=c.getY();
        if(x <0 || x>19 || y<0 || y>19 ) throw new IllegalArgumentException("either x or y values not between 0-19 ");
        jInternalFrame1.remove(jLabelMap); //jInternalFrame è il frame in cui c'è la mappa
        jLabelMap=new JLabel("");
        try
        { //TODO: Spostare il numero di chiavi a 4 
            if(MapGraph.keys==2) //TODO Se ho 4 chiavi allora devo aprire la porta del drago spostare 2 a 4 
            {
                MapGraph.setDragon();
            }  
            jLabelMap.setSize(jInternalFrame1.getSize());               //Ridimensiona 
            jLabelMap.setLayout(new GridLayout(numberRows,numberCols)); // Di default è 20 20  
            jButtonPlayer.setEnabled(false);                          //Lo rende non premibile, l'icona del player è un pulsante che non 
            jButtonPlayer.setOpaque(false);
            //Aggiungo le label e il pulsante del player nella posizione corretta, è forzato essere O(n^2)
            int i=0; int ii=0;
            for( i=0; i<20; i++)
            {
                for( ii=0; ii<20; ii++)
                {
                    if(ii== x&& i==y)
                    {
                        jLabelMap.add(jButtonPlayer);
                    }else
                    {
                        JLabel l=new JLabel(" ");
                        l.setForeground(Color.RED);
                        jLabelMap.add(l);
                    }
                }
            }
            jInternalFrame1.add(jLabelMap);
            jInternalFrame1.setVisible(true);
            }catch(Exception e){System.err.println("Errore");}
        }

        /**
         * Sets the postion of the player to the DEFAULT one
         * @param x The position from the top left corner along the x axis
         * @param y The position from the top left corner along the y axis
         * @param PlayerImage is the path to the icon of the player 
         * note: Questo metodo è ora oblsoleto
        */

        private void addPlayerPosition(int x, int y, String PlayerImage) {


           // ImageIcon imageIcon = new ImageIcon("C:\\Users\\Matteo\\Desktop\\Prova mappa\\PersonIcon.jpg"); // load the image to a imageIcon
            // Image image = imageIcon.getImage(); // transform it 
            // Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
             //imageIcon = new ImageIcon(newimg);  // transform it back

           //  jButtonPlayer.setIcon(imageIcon);
             try {
              //  addMapPicture(MapGraph.getPathImage(new Coordinates(x,y)));
                move(new Coordinates(x,y));
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
            SwingUtilities.updateComponentTreeUI(jInternalFrame1); //Update del frame
        }
        
        /**
         * 
         * @param c the node you want the image of 
         */
        public void updateGraphics(Coordinates c )
        {
            String path = MapGraph.getIconOf(c); //Aggiungi il percorso del nuovo nodo per avere l'immagine corretta 
                   path = pathParser.getPath(imageDirPath,path );
                   jLabelMap.setIcon(new ImageIcon(path));
        }


        /**
         * @param amount the value of the bar [0,100]
         * @throws RuntimeException thrown if amount is grater 100. If negative amount is set to 0 
         */
        public void setPlayerHealth(int amount ) throws RuntimeException
        {
            if(amount>100) throw new RuntimeException("value greater than 100 ");
            if (amount <0) amount =0; 
            jBarVitaPlayer.setValue(amount);
        }

        /**
         * @param amount the value of the bar [0,100]
         * @throws RuntimeException thrown if amount is grater 100. If negative amount is set to 0 
         */
        public void setEnemyHealth(int amount ) throws RuntimeException
        {
            if(amount>100) throw new RuntimeException("value greater than 100 ");
            if (amount <0) amount =0; 
            jBarVitaNemico.setValue(amount);
        }

        /**
         * @param amount the value of the bar [0,100]
         * @throws RuntimeException thrown if amount is grater 100. If negative amount is set to 0 
         */
        public void setPeso(int amount) throws RuntimeException
        {
            if(amount>100) throw new RuntimeException("value greater than 100 ");
            if (amount <0) amount =0; 
            jBarPeso.setValue(amount);
        }







 



        /**
         * Adds some components
         * Note: this method is black box generated 
         */
        public void AddComponents1(){javax.swing.GroupLayout jPanelProgressBarsLayout = new javax.swing.GroupLayout(jPanelProgressBars);
            jPanelProgressBars.setLayout(jPanelProgressBarsLayout);
            jPanelProgressBarsLayout.setHorizontalGroup(
                jPanelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProgressBarsLayout.createSequentialGroup()
                    .addGroup(jPanelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelProgressBarsLayout.createSequentialGroup()
                            .addComponent(jLabelVita)
                            .addGap(4, 4, 4))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBarVitaPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBarPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBarVitaNemico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanelProgressBarsLayout.setVerticalGroup(
                jPanelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanelProgressBarsLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelProgressBarsLayout.createSequentialGroup()
                            .addComponent(jBarVitaNemico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanelProgressBarsLayout.createSequentialGroup()
                            .addGroup(jPanelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelVita)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(25, 25, 25)
                            .addComponent(jLabel2)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanelProgressBarsLayout.createSequentialGroup()
                            .addComponent(jBarVitaPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jBarPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
    }
    /**
         * Adds some other components
         * Note: this method is black box generated 
         */
    public void addComponents2(){
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTextComandi, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonInvio, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonInvio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextComandi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jPanelProgressBars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 656, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane4)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelProgressBars, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
       JButton jButtonSave = new JButton("Save Game");
       jButtonSave.setSize(100,30);
       jButtonSave.setLocation(1200,730);
       add(jButtonSave);

       key = new JButton();
       key.setSize(120,70);
       key.setBackground(backGround);
       key.setForeground(new Color(255,0,0));
       key.setBorderPainted(false);
       
       ImageIcon imageIcon2 = new ImageIcon(keyIcon); // load the image to a imageIcon
       Image image2 = imageIcon2.getImage(); // transform it 
       Image newimg2 = image2.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
       imageIcon2 = new ImageIcon(newimg2);  // transform it back
       key.setIcon(imageIcon2);
       key.setText(MapGraph.keys+"");
       key.setLocation(675,710);
       key.setOpaque(false);
       add(key);

       CircleButton JButtonMusic=new CircleButton("");
       //JButtonMusic.setContentAreaFilled(false);
       JButtonMusic.setFocusPainted(false);
       JButtonMusic.setBorderPainted(false);
       JButtonMusic.setSize(120,70);
       imageIcon2 = new ImageIcon(musicIcon); // load the image to a imageIcon
       
       image2 = imageIcon2.getImage(); // transform it 
       newimg2 = image2.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
       imageIcon2 = new ImageIcon(newimg2);  // transform it back
       JButtonMusic.setIcon( imageIcon2); 

              
       JButtonMusic.setLocation(1050,710);
       
       add(JButtonMusic);

    }
    
    /**
     * Metodo che inizializza tutte le componenti del frame
     */
    public void initComponents() {
        //Inizializzazione delle variabili oggetto per la grafica 
        //setBackground(new java.awt.Color(0,0,0));
        jToolBar1 = new javax.swing.JToolBar();
        jScrollPane4 = new javax.swing.JScrollPane();
        jInternalFrame1 = new javax.swing.JInternalFrame("The cave");
        jButtonPlayer = new javax.swing.JRadioButton();
        jLabelMap = new javax.swing.JLabel();
        jPanelProgressBars = new javax.swing.JPanel(); jPanelProgressBars.setBackground(backGround);
        jBarVitaNemico = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabelVita = new javax.swing.JLabel();
        jBarVitaPlayer = new javax.swing.JProgressBar();
        jLabel2 = new javax.swing.JLabel();
        jBarPeso = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        jButtonInvio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextStory = new javax.swing.JTextArea();
        jTextComandi = new javax.swing.JTextField();

        jToolBar1.setRollover(true);

        //Implementazione dell'inventario

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        jLabel1.setText("Nemico");
        jLabelVita.setText("Vita");
        jLabel2.setText("Peso");
        jInternalFrame1.setVisible(true);
        jButtonPlayer.setEnabled(false);    
        jBarPeso.setBackground(lightBackground);
        jBarVitaNemico.setBackground(lightBackground);
        jBarVitaNemico.setForeground(new Color(255,0,0));
        jBarVitaPlayer.setBackground(lightBackground);
        jBarVitaNemico.setForeground(new Color(255,0,0));

        jBarPeso.setForeground(new java.awt.Color(0, 255, 0));
        jBarPeso.setValue(10);


        AddComponents1(); //Metodo black box
        
        jTextComandi.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (jTextComandi.getText().equals("Enter your answer")) {
                    jTextComandi.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (jTextComandi.getText().isEmpty()) {
                    jTextComandi.setText("Enter your answer");
                }
            }
        });


        jButtonInvio.setText("Submit");
        jButtonInvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                try {
                    if(jTextComandi.getText().contains("Open"))
                    {
                        String[] t=jTextComandi.getText().split(",");
                        int x=Integer.parseInt(t[0]);
                        int y=Integer.parseInt(t[1]);
                        MapGraph.setBeaten(new Coordinates(x,y));
                        key.setVisible(false);
                        key.setText(MapGraph.keys+"");
                        key.setVisible(true);                        
                    }
                    String[] t=jTextComandi.getText().split(",");
                    int x=Integer.parseInt(t[0]);
                    int y=Integer.parseInt(t[1]);
                    move(new Coordinates(x,y));
                    updateGraphics(new Coordinates(x,y));
                }catch (Exception e ){}
                
                SwingUtilities.updateComponentTreeUI(jInternalFrame1);
                
           }
        });

        jTextStory.setBackground(new Color(0,0,0));
        jTextStory.setForeground(new Color (255,255,255));
        jTextStory.setFont(jTextStory.getFont().deriveFont(fontSize)); 
        jTextStory.setColumns(20);
        jTextStory.setRows(5);
        jTextStory.setText(MapGraph.getAllIcons());
        jScrollPane1.setViewportView(jTextStory);

        jTextComandi.setText("Enter your answer");

        addComponents2();
        pack();
    }// </editor-fold>



     // Variables declaration - do not modify                     
     private javax.swing.JProgressBar jBarPeso;
     private javax.swing.JProgressBar jBarVitaNemico;
     private javax.swing.JProgressBar jBarVitaPlayer;
     private javax.swing.JButton jButtonInvio;
     private javax.swing.JInternalFrame jInternalFrame1;
     private javax.swing.JLabel jLabel1;
     private javax.swing.JLabel jLabel2;
     private javax.swing.JLabel jLabelMap;
     private javax.swing.JLabel jLabelVita;
     private javax.swing.JPanel jPanelProgressBars; // Pannello del 
     private javax.swing.JPanel jPanel2;
     private javax.swing.JRadioButton jButtonPlayer;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane4;
     private javax.swing.JTextField jTextComandi;
     private javax.swing.JTextArea jTextStory;
     private javax.swing.JToolBar jToolBar1;
     private JButton key;
     // End of variables declaration      
}    




