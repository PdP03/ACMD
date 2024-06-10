package test.java.com.ACMD.app.Kernel_Layer.Prompt;

import org.junit.Test;
import org.junit.Assert;

import com.ACMD.app.Kernel_Layer.Prompt.Prompt;

public class private_ParseInputPrompt
{
    @Test
    public void controlloSplit()
    {//controllo del metodo che splitta le stringhe: essendo privato utilizzo un metodo di appoggio

        print("\\n finale, separato da spazio \n");

        Assert.assertEquals(3, Prompt.DEBUG("Salve a tutti").size() );
        Assert.assertEquals(2, Prompt.DEBUG("Doppio  Spazio").size() );
        Assert.assertEquals(2, Prompt.DEBUG("Triplo   Spazio").size() );
        
        Assert.assertEquals(2, Prompt.DEBUG(" Spazio iniziale").size() );
        Assert.assertEquals(3, Prompt.DEBUG("  Doppio spazio iniziale").size() );
        Assert.assertEquals(2, Prompt.DEBUG("Spazio finale ").size() );
        Assert.assertEquals(3, Prompt.DEBUG("Doppio spazio finale  ").size() );

        Assert.assertEquals(6, Prompt.DEBUG("\\n finale, separato da spazio \n").size() ); //perché \n\0 assieme non riesce a dividere
        Assert.assertEquals(2, Prompt.DEBUG("\\n finale\n").size() );
    }

    void print(String s)    //utile per vedere cosa stamperebbe : debug console
    {
        System.out.println("\n"+s);
        String[] ary = s.split(" ");
        System.out.println("Dimensione: "+ary.length);
        
        for(int i=0; i<ary.length; i++)
         System.out.println( ( ary[i] != "\0" && ary[i] != "\n" && ary[i] != "" ) ? ary[i] : "c\\0");
    }
}

/*
 * print( "Salve a tutti" );
        print( "Doppio  Spazio" );
        print( "Triplo   Spazio");
        print( " Spazio iniziale");

        print( "  Doppio spazio iniziale" );
        print( "Spazio finale ");
        print( "Doppio spazio finale  ");
        print( "\\n finale separato da spazio \n" );

        print( "\\n finale\n");

    System.out.println(" ### Seconde prove ###");

        System.out.println( removeDoubleSpaces("Salve a tutti").size() );
        System.out.println( removeDoubleSpaces("Doppio  Spazio").size() );
        System.out.println( removeDoubleSpaces("Triplo   Spazio").size() );
        System.out.println( removeDoubleSpaces(" Spazio iniziale").size() );

        System.out.println( removeDoubleSpaces("  Doppio spazio iniziale").size() );
        System.out.println( removeDoubleSpaces("Spazio finale ").size() );
        System.out.println( removeDoubleSpaces("Doppio spazio finale  ").size() );
        System.out.println( removeDoubleSpaces("\\n finale separato da spazio \n").size() );

        System.out.println( removeDoubleSpaces("\\n finale\n").size() );
    }

    void print(String s)
    {
        System.out.println("\n"+s);

        String[] ary = s.split(" ");
        System.out.println("Dimensione: "+ary.length);
        for(int i=0; i<ary.length; i++)
         System.out.println(ary[i]);

        System.out.println(" ----- ");
    }

    static Vector<String> removeDoubleSpaces(String str)
    {
        String[] sAry = str.split(" ");             //possono rimanere spazi doppi
        Vector<String> s2= new Vector<String>();

        //_rimuovo tutte istanze vuote
        for(int i=0; i<sAry.length; i++)
        {
            System.out.print( sAry[i].length() );
            if( sAry[i]!="" && sAry[i]!='\n') s2.add( sAry[i] );
        }
        
        return s2;
    }
}   
 * 
 */
