package com.ACMD.app.Engine_Layer.GameEngine;
import java.util.HashMap;

public class main3 {

        public static void main(String[] args)
        {
            HashMap<Integer,entry> hm = new HashMap<Integer,entry>();
            hm.put( 4,null );
            hm.put( 5, new entry("chiave","valore") );
            hm.put( 4, new entry("portachiavi","valore") );
    
            System.out.println( hm );
            System.out.println( hm.containsKey(4) );
    
        System.out.println("  ----  ");
    
            HashMap<entry,Integer> hm2 = new HashMap<entry,Integer>();
            hm2.put( null,4 );
            hm2.put( new entry("chiave","valore"),5 );
            hm2.put( new entry("portachiavi","valore"),4 );
    
            System.out.println( hm2 );
            System.out.println( hm2.containsKey(null) );
            System.out.println( hm2.containsKey( new entry("chive","valore") ) );
            System.out.println( hm2.containsKey( new entry("chive",""      ) ) );
            System.out.println( hm2.containsKey( new entry("chive",null    ) ) );
    
        System.out.println("  ----  ");
    
            System.out.println( (new entry("chiave","valore")) == (new entry("chiave","valore")) );
            System.out.println( (new entry("chiave","valore")) == (new entry("chiave","")) );
            System.out.println( (new entry("chiave","valore")).hashCode() );
            System.out.println( (new entry("chiave","")).hashCode() );
    
            hm2.put( new entry("chiave","chiave") , 17 );
            System.out.println( hm2.containsKey( new entry("chiave","chiave") ) );
    
        System.out.println("  ---- da Alex ----  ");
    
            HashMap<entry, Integer> map = new HashMap<>();
            
            entry e = new entry("cio","cio");
            entry e2 = new entry("ciao","ciao");
    
            map.put(e, 1);
            map.put(e2, 2);
    
            System.out.println(map.containsKey(new entry("cio","pinco")));
            System.out.println(map.containsKey(new entry("ciao","cia")));
        }
    }
    
    class entry
    {
        public String s1,s2;
        public entry(String s1, String s2)
        {
            this.s1= s1;
            this.s2= s2;
        }
    
        public String toString(){return s1+" "+s2;}
        @Override
        public int hashCode()   {return s1.hashCode();} //la seconda stringa non centra
        @Override
        public boolean equals(Object obj)
        {
            entry s= (entry)obj;
            //return s1 == s.s1;
            return s1.equals(s.s1);
        }
         /* 
    class entry{        //di Alex
        public String s1;
        public String s2;
    
        public entry(String s1, String s2){
            this.s1 = s1;
            this.s2 = s2;
        }
    
        @Override
        public boolean equals(Object o){
            entry e = (entry)o;
            return s1 == e.s1;
        }
    
        @Override
        public int hashCode(){
            return s1.hashCode();
        }*/
    
    

}
