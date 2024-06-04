package com.ACMD.app.Graphic_Layer.GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.Icon;
import javax.swing.JButton;

import com.ACMD.app.Engine_Layer.ParsePath;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.io.File;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyRoundButton extends JButton implements ActionListener
{
// ===========
// Costruttore
// ===========
    public MyRoundButton(String label)
    {
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
        this.addActionListener(this);
    }
    //====================
    // Cerchio
    // ===================
    protected void paintComponent(Graphics g) 
    {
        if (getModel().isArmed()) {
              g.setColor(Color.lightGray);
        } else {
             g.setColor(getBackground());
        }
        g.fillOval(0, 0, getSize().width-1, getSize().height-1);
        super.paintComponent(g);
   }
   protected void paintBorder(Graphics g) 
   {
        g.setColor(getForeground());
        g.drawOval(0, 0, getSize().width-1, getSize().height-1);
   }
   Shape shape;
   public boolean contains(int x, int y) 
   {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
             shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }
        return shape.contains(x, y);
   }
   // =================
   // Gestione musica
   // =================
     final String imageDirPath = "\\ACMD\\src\\main\\java\\com\\ACMD\\app\\Graphic_Layer\\Images\\";
     String musicPath       = ParsePath.getPath(imageDirPath, "GameMusic.wav");
     private boolean isPaused=true; 
     
   public void actionPerformed(ActionEvent e)
   {
     if(isPaused) playMusic();
     else pauseMusic();
   }

   private Clip clip;
    

        private void playMusic() {
        if (clip != null && clip.isRunning()) 
        {
            clip.stop();
        }
        
        try 
        {
            File file = new File(musicPath);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(file);
            
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.loop(Clip.LOOP_CONTINUOUSLY);          
            clip.start();
 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        isPaused=false;
        
    }
    
    private void pauseMusic() 
    {

        if (clip != null && clip.isRunning()) 
        {
            clip.stop();
            isPaused = true;
        } 
        else if (clip != null && isPaused) 
        {
            clip.start();
            isPaused = false;
        }
        isPaused=true;
    }
}
