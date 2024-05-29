package com.ACMD.app.Graphic_Layer.GUI;

import com.ACMD.app.Engine_Layer.Mappa.*;


import java.io.IOException;


public class TestFrame{
    public static void main(String[] args) throws IOException{
      /*  JFrame j=new JFrame();
        JLabel background = new JLabel();
        JLabel[] arr = new JLabel[400];
        int cont =0;
        j.setSize(800,800);

        j.setLayout(new BorderLayout());
        background.setLayout(new GridLayout(20,20));
        for(int i=0; i <20; i++){
            for(int ii=0; ii<20; ii++)
            {
                String s;
                if(i==5 && ii==5)
                {
                     s="HHHH";
                }
                else{ s=""+i+","+ii;}
                arr[cont++] = new JLabel(s);
            }
        }

        for(int i=0; i<400; i++) background.add(arr[i]);

        

        j.setVisible(true);
        JButton b=new JButton ("Swap");
        background.add(b);
        j.add(background);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)            {
                //Here goes the action (method) you want to execute when clicked
                j.remove(background);
                for(int i=0; i <20; i++){
                    for(int ii=0; ii<20; ii++)
                    {
                        String s;
                        if(i==5 && ii==5)
                        {
                             s="HHHH";
                        }
                        else{ s=""+i+","+ii;}
                        arr[cont++] = new JLabel(s);
                    }
                }
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
            }
        });
        */
     //   new MyTree();
      MapGraph m2= new MapGraph();
      MapGraph.printAllIcons();
      new GameFrame().setVisible(true); 
        }   

}

/*     */

    