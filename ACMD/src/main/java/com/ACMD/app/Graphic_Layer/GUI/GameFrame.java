package com.ACMD.app.Graphic_Layer.GUI;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;


import javax.swing.*;


public class GameFrame extends javax.swing.JFrame implements Frame {
    int globalRiga=0;
    int globalColonna=0;
    int playerX=19;
    int playerY=1;
    public GameFrame() {
                    initComponents(); // Crea la finestra vuota, senza aggiugnere la mappa  
                    this.getContentPane().setBackground(new java.awt.Color(0,0,0));
                    addMapPicture(); // Aggiunge l'immagine (grafica)
                    addPlayerPosition(); //Default = (5,5) 
    }
                      
    

    public void initComponents() {
        //Inizializzazione delle variabili oggetto per la grafica 
        setBackground(new java.awt.Color(0,0,0));
        jToolBar1 = new javax.swing.JToolBar();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jInternalFrame1 = new javax.swing.JInternalFrame("Caverna");
        jButtonPlayer = new javax.swing.JRadioButton();
        jLabelMap = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
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
        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "AHOHHOHOHOH", "Item 2", "Item 3", "Item 4"};
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(true);
        jLabel1.setText("Nemico");
        jLabelVita.setText("Vita");
        jLabel2.setText("Peso");

/*     


*/
        jInternalFrame1.setVisible(true);
        jButtonPlayer.setEnabled(false);    
        jBarPeso.setBackground(new java.awt.Color(0, 0, 0));
        jBarPeso.setForeground(new java.awt.Color(0, 255, 0));


        AddComponents1(); //Metodo black box
        
        
        jButtonInvio.setText("Submit");
        jButtonInvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
                move(globalRiga++%20,globalColonna%20);
                
                SwingUtilities.updateComponentTreeUI(jInternalFrame1);
                
           }
        });

        jTextStory.setBackground(new java.awt.Color(204, 204, 204));
        jTextStory.setColumns(20);
        jTextStory.setRows(5);
        jTextStory.setText("Here goes the story");
        jScrollPane1.setViewportView(jTextStory);

        jTextComandi.setText("Enter your answer");

        addComponents2();
        pack();
    }// </editor-fold>                        
                
    
    
    public void move(int x,int y)
        {
        jInternalFrame1.remove(jLabelMap);
        jLabelMap=new JLabel("");
        jLabelMap.setSize(jInternalFrame1.getSize());
        jLabelMap.setLayout(new GridLayout(20,20));
        jLabelMap.setIcon(new ImageIcon("C:\\Users\\Matteo\\Desktop\\Prova mappa\\Mappa_Definitiva1.png"));
        jButtonPlayer.setEnabled(false);    
        

            int i=0; int ii=0;
            for( i=0; i<20; i++){
                for( ii=0; ii<20; ii++){
                    if(i== x&& ii==y){jLabelMap.add(jButtonPlayer);}
                    else{
                        JLabel l=new JLabel(" ");
                    l.setForeground(Color.RED);
                    jLabelMap.add(l);
                }
                }
            }

            jInternalFrame1.add(jLabelMap);
            jInternalFrame1.setVisible(true);

         

        }

        private void addPlayerPosition() {
            ImageIcon imageIcon = new ImageIcon("C:\\Users\\Matteo\\Desktop\\Prova mappa\\PersonIcon.jpg"); // load the image to a imageIcon
            // Image image = imageIcon.getImage(); // transform it 
            // Image newimg = image.getScaledInstance(25, 25,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
             //imageIcon = new ImageIcon(newimg);  // transform it back

           //  jButtonPlayer.setIcon(imageIcon);
             move(playerX, playerY);
            SwingUtilities.updateComponentTreeUI(jInternalFrame1); //Update del frame
        }
    
        private void addMapPicture() {
            // TODO Auto-generated method stub
            jInternalFrame1.getContentPane().setLayout(new BorderLayout());
            jInternalFrame1.setSize(500,500);
            //jButtonPlayer.setForeground(new java.awt.Color(255, 255, 255));
            
            jButtonPlayer.setOpaque(false);
            jButtonPlayer.setVisible(true);
            jLabelMap.setSize(jInternalFrame1.getSize(getSize()));
           
            jLabelMap.setIcon(new javax.swing.ImageIcon("C:\\Users\\Matteo\\Desktop\\Prova mappa\\Mappa_Definitiva1.png")); // NOI18N
            jLabelMap.setLayout(new GridLayout(20,20));
            jInternalFrame1.add(jLabelMap);
            SwingUtilities.updateComponentTreeUI(jInternalFrame1);
        }













        public void AddComponents1(){javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabelVita)
                            .addGap(4, 4, 4))
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBarVitaPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBarPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jBarVitaNemico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jBarVitaNemico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabelVita)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(25, 25, 25)
                            .addComponent(jLabel2)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jBarVitaPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jBarPeso, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            );
    }

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
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
}
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
     private javax.swing.JList<String> jList2;
     private javax.swing.JPanel jPanel1;
     private javax.swing.JPanel jPanel2;
     private javax.swing.JRadioButton jButtonPlayer;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane3;
     private javax.swing.JScrollPane jScrollPane4;
     private javax.swing.JTextField jTextComandi;
     private javax.swing.JTextArea jTextStory;
     private javax.swing.JToolBar jToolBar1;
     // End of variables declaration      
}    




