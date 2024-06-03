package com.ACMD.app.Graphic_Layer.GUI;

import javax.swing.Icon;
import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.FontMetrics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class MyRoundButton extends JButton
{
    public MyRoundButton(String label)
    {
        super(label);
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
        setPreferredSize(size);
        setContentAreaFilled(false);
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
}
