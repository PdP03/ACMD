package com.ACMD.app.Graphic_Layer.GUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.*;
import com.ACMD.app.Engine_Layer.ParsePath;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;


public class GameFrame extends javax.swing.JFrame implements Frame {
    //Inizializzazione delle variabili del frame ( per altre, più complesse guardare initComponents() )

    // =======================================
    // | Variabili create per l'uso          |
    // =======================================
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
    public boolean isOutputReady;
    //private final int delay = 100; 
    // ====================
    // Stili per la shell
    /// ====================




    
    /**
     * Costruttore che genera il gameFrame, inizializza le compontenti, ne setta il background 
     * 
     */
    public GameFrame() {
                    this.getContentPane().setBackground(backGround);
                    initComponents(); // Crea la finestra vuota, senza aggiugnere la mappa          
                    move(new Coordinates(1,19));
                    updateGraphics(new Coordinates(1,19));
                    setPlayerHealth(100);
                    setEnemyHealth(100);
                    setPeso(0);
                    isOutputReady=false;
                    jTextStory.setEnabled(false);       
    }                   
    /**
     * Moves the player position from the current one to the desired one: 
     * @param x The position from the top left corner along the x axis, values 0 to 19
     * @param y The position from the top left corner along the y axis, values 0 to 19
     * @throws IllegalArgumentException x or y not in the range [0,19]
     */
    public void move (Coordinates c) throws IllegalArgumentException
    {
        //System.out.println("move chiamato con" + c.toString());
        //===================================================================
        // Voglio spostare il player nel posto giusto se entra in una stanza
        // OBSOLETO MA LO TENGO PERCHE' POTREBBE ESSERE UTILE IN FUTURO 
        // ==================================================================
        
        /*if(MapGraph.isStanza(c))
            {
                int x,y;
                Coordinates temp = MapGraph.getPlayerPositionOf(c);
                x=temp.getX();
                y=temp.getY();
                System.out.println("Ti sposto in" +x+y);
                if(MapGraph.getPlayerPositionOf(c).getX()==c.getX() && MapGraph.getPlayerPositionOf(c).getY() == c.getY()) return;
                move(new Coordinates(x,y));
                String iconPath = MapGraph.getIconOf(new Coordinates(x,y));
                iconPath=ParsePath.getPath(imageDirPath,iconPath);
                ImageIcon theIcon = new ImageIcon(iconPath); 
                jLabelMap.setIcon(theIcon);
                SwingUtilities.updateComponentTreeUI(jLabelMap);
                jInternalFrame1.add(jLabelMap);
    
                jInternalFrame1.setVisible(true);
                return;
            }
        */
        int x=c.getX(); 
        int y=c.getY();
        if(x <0 || x>19 || y<0 || y>19 ) throw new IllegalArgumentException("either x or y values not between 0-19 ");
        jInternalFrame1.remove(jLabelMap); //jInternalFrame è il frame in cui c'è la mappa

        jLabelMap=new JLabel("");
        try
        {                         
            if(MapGraph.keys==4) 
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
                        JLabel l=new JLabel("");
                        l.setForeground(Color.RED);
                        jLabelMap.add(l);
                    }
                }
            }
            
            String iconPath = MapGraph.getIconOf(new Coordinates(x,y));
            iconPath=ParsePath.getPath(imageDirPath,iconPath);
            ImageIcon theIcon = new ImageIcon(iconPath); 
            jLabelMap.setIcon(theIcon);
            SwingUtilities.updateComponentTreeUI(jLabelMap);
            jInternalFrame1.add(jLabelMap);

            jInternalFrame1.setVisible(true);
            }catch(Exception e){System.err.println("Errore ");}
        }

        /**
         * Sets the postion of the player to the DEFAULT one
         * @param x The position from the top left corner along the x axis
         * @param y The position from the top left corner along the y axis
         * @param PlayerImage is the path to the icon of the player 
         * note: Questo metodo è ora oblsoleto
        */

        /**
         * 
         * @param c the node you want the image of 
         */
        public void updateGraphics(Coordinates c )
        {
            String path = MapGraph.getIconOf(c); //Aggiungi il percorso del nuovo nodo per avere l'immagine corretta 
                   path = ParsePath.getPath(imageDirPath,path );
                   jLabelMap.setIcon(new ImageIcon(path));
        }


        /**
         * @param amount the value of the bar [0,100]
         * @throws RuntimeException thrown if amount is grater 100. If negative amount is set to 0 
         */
        public void setPlayerHealth(int amount ) throws RuntimeException
        {
            //System.out.println("Vita "+amount );
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
            //System.out.println("Enemy "+amount );
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
            //System.out.println("Peso "+amount );
            if(amount>100) throw new RuntimeException("value greater than 100 ");
            if (amount <0) amount =0; 
            jBarPeso.setValue(amount);
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
        jLabelNemico = new javax.swing.JLabel();
        jLabelVita = new javax.swing.JLabel();
        jBarVitaPlayer = new javax.swing.JProgressBar();
        jLabelPeso = new javax.swing.JLabel();
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
        jLabelNemico.setText("Nemico");
        jLabelVita.setText("Vita");
        jLabelPeso.setText("Peso");
        jInternalFrame1.setVisible(true);
        jButtonPlayer.setEnabled(false);    
        jBarPeso.setBackground(lightBackground);
        jBarVitaNemico.setBackground(lightBackground);
        jBarVitaNemico.setForeground(new Color(255,0,0));
        jBarVitaPlayer.setBackground(lightBackground);
        jBarVitaPlayer.setForeground(Color.red);
        jBarVitaNemico.setForeground(new Color(255,0,0));

        jBarPeso.setForeground(new java.awt.Color(0, 255, 0));
        jBarPeso.setValue(10);


        AddComponents1(); //Metodo black box
    // =====================
    // Gestione dell'invio
    //=====================
    jTextComandi.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
           
            jButtonInvio.setEnabled(true);  // Attiva il pulsante quando si preme Invio
            jButtonInvio.doClick(); } // Simula un click sul pulsante
    });
    // ==============================
    // Gestione del testo automatico 
    // ==============================
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
        jButtonInvio.addActionListener(this);
        

        jTextStory.setBackground(new Color(0,0,0));
        jTextStory.setForeground(new Color (255,255,255));
        jTextStory.setFont(jTextStory.getFont().deriveFont(fontSize)); 
        jTextStory.setColumns(20);
        jTextStory.setRows(5);
        jScrollPane1.setViewportView(jTextStory);

        jTextComandi.setText("Enter your answer");

        addComponents2();

       // JButtonMusic.addActionListener(this);
        pack();
    }// </editor-fold>
    /**
     * For busy waiting 
     * @return If the button has been pressed return that value, otherwise return null 
     */
    public String textInput()
    {
        System.out.printf("");
        if(isOutputReady){
            isOutputReady=false;
            String theInput = jTextComandi.getText();
            jTextComandi.setText(null);

            return theInput;}
        return null; 
       
    }
    /**
     * 
     * @param s testo da visualizzare. Nota: la console VIENE PULITA prima del reset, per non pulirla usare il metodo 
     * "appendOnConsole" che non pulisce
     */
    public void writeOnConsole(String s)
    {
        jTextStory.setText(s);
        SwingUtilities.updateComponentTreeUI(jTextStory);
    }
    public void writeRedOnConsole(String s)
    {
        jTextStory.setForeground(Color.red);
        jTextStory.append(s);
        SwingUtilities.updateComponentTreeUI(jTextStory);
    }
    public void writeBlueOnConsole(String s)
    {
        jTextStory.setForeground(Color.blue);
        jTextStory.append(s);
        SwingUtilities.updateComponentTreeUI(jTextStory);
    }

    /**
     * 
     * @param s testo da visualizzare. Nota: la console NON VIENE PULITA prima del reset, per non pulirla usare il metodo 
     * "writeOnConsole" che pulisce
     */
    public void appendOnConsole(String s)
    {
        jTextStory.append(s);
    }
    /**
     * Pulisce la console: 
     */
    public void resetConsole()
    {
        jTextStory.setText("");
    }
    
    // ==================================
    // Dichiarazione variabili del frame
    // ==================================
    private javax.swing.JProgressBar jBarPeso;
     private javax.swing.JProgressBar jBarVitaNemico;
     private javax.swing.JProgressBar jBarVitaPlayer;
     private javax.swing.JButton jButtonInvio;
     private javax.swing.JInternalFrame jInternalFrame1;
     private javax.swing.JLabel jLabelNemico;        //nemico salute
     private javax.swing.JLabel jLabelPeso;        //peso      (commenta sempre)
     private javax.swing.JLabel jLabelMap;
     private javax.swing.JLabel jLabelVita;     //player
     private javax.swing.JPanel jPanelProgressBars; // Pannello del 
     private javax.swing.JPanel jPanel2;
     private javax.swing.JRadioButton jButtonPlayer;
     private javax.swing.JScrollPane jScrollPane1;
     private javax.swing.JScrollPane jScrollPane4;
     private javax.swing.JTextField jTextComandi;
     private javax.swing.JTextArea jTextStory;
     private javax.swing.JToolBar jToolBar1;
     private JLabel key;
     private MyRoundButton JButtonMusic;
     // End of variables declaration      
    public final String lorem ="Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed faucibus ligula arcu, facilisis bibendum orci sagittis ac. Vestibulum maximus odio quis lobortis condimentum. Cras nisi mauris, suscipit non mauris quis, rhoncus vulputate leo. Fusce leo neque, mollis nec lectus et, ornare eleifend velit. Praesent sagittis, nulla a varius accumsan, nisi sem tempus lectus, quis vehicula dui risus vel leo. Cras ante sem, porta quis aliquam sit amet, ultrices a ipsum. Quisque libero elit, accumsan a est congue, finibus rhoncus elit. Ut mattis commodo sapien, in scelerisque tellus molestie eu";
    public final String[] splitLorem = lorem.split(" ");
    

     
    // =====================
    // Gestione bottoni
    // =====================
    public void actionPerformed(java.awt.event.ActionEvent evt) 
            {
                // ========================
                // |    BOTTONE SUBMIT    |
                // ========================
                if(evt.getSource()==jButtonInvio)
                isOutputReady=true;
                //System.out.println("Hai premuto il bottone: isOutputReady è " + isOutputReady);
                {
                    isOutputReady=true;
                    System.out.println("isoutput ready is now true");
                    SwingUtilities.updateComponentTreeUI(jInternalFrame1);
                }

            }

    // ===========================
    // |    RIMOZIONE BARRE      |
    // ===========================
    /**
     * Metodo che cancella la barra del nemico, essendo finita la battaglia aggiorna le chiavi
     */
    public void removeEnemyBar()
    {
        jBarVitaNemico.setVisible(false);
        jLabelNemico.setVisible(false);
        updateKeys();
    }
    /**
     * Metodo che la mostra del nemico
     * */
    public void addEnemyBar()
    {
        jBarVitaNemico.setVisible(true);
        jLabelNemico.setVisible(true);

    }
    /**
     * Metodo che la mostra barra del player
     */
    public void addPlayerBar()
    {
        jBarVitaPlayer.setVisible(true); jLabelVita.setVisible(true);
    }
    /**
     * Metodo che rimuove la barra del player
     */
    public void removePlayerBar()
    {
        jBarVitaPlayer.setVisible(false); jLabelVita.setVisible(false);
    }
    /**
     * Metodo che la mostra barra del peso del player
     */
    public void addWeightBar()
    {
        jBarPeso.setVisible(true); jLabelPeso.setVisible(true);
    }
    /**
     * Metodo che la nasconde mostra barra del peso
     */
    public void removeWeightBar()
    {
        jBarPeso.setVisible(false); jLabelPeso.setVisible(false);
    }

    /** 
     * Metodo che aggiorna le chiavi
     * */ 
    private void updateKeys()
    {
        key.setText(MapGraph.keys+"");
        key.setVisible(false);
        key.setVisible(true);
    }

     //============================
        //       Black box
        //===========================
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
                        .addComponent(jLabelPeso, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(jPanelProgressBarsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBarVitaPlayer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBarPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(23, 23, 23)
                    .addComponent(jLabelNemico, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jLabelNemico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGap(25, 25, 25)
                            .addComponent(jLabelPeso)
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
       
       

       key = new JLabel();
       //key.setEnabled(false);
       //ey.setOpaque(true);
       key.setSize(120,70);
       key.setBackground(backGround);
       key.setForeground(new Color(255,0,0));
      
       
       ImageIcon imageIcon2 = new ImageIcon(keyIcon); // load the image to a imageIcon
       Image image2 = imageIcon2.getImage(); // transform it 
       Image newimg2 = image2.getScaledInstance(70, 70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
       imageIcon2 = new ImageIcon(newimg2);  // transform it back
       key.setIcon(imageIcon2);
       key.setText(MapGraph.keys+"");
       key.setLocation(675,710);
       key.setOpaque(false);
       add(key);

       JButtonMusic=new MyRoundButton("");
       //JButtonMusic.setContentAreaFilled(false);

       JButtonMusic.setSize(70,70);
       imageIcon2 = new ImageIcon(musicIcon); // load the image to a imageIcon
       
       image2 = imageIcon2.getImage(); // transform it 
       newimg2 = image2.getScaledInstance(60, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
       imageIcon2 = new ImageIcon(newimg2);  // transform it back
       JButtonMusic.setIcon( imageIcon2); 

              
       JButtonMusic.setLocation(1210,710);
       
       add(JButtonMusic);

    }
}
