package com.ACMD.app.Graphic_Layer.GUI;

import java.awt.*;

import javax.swing.*;


public class GridFrame{

    public static void main (String[] args)
    {
        JFrame j=new JFrame();
        j.setSize(800,800);
        j.setLayout(new GridLayout(20,20));
        for(int i=0; i <20; i++){
            for(int ii=0; ii<20; ii++)
            {
                String s=""+i+","+ii;
                j.add(new JLabel(s));
            }
        }
        j.setVisible(true);
    }
}