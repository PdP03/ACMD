package test.java.com.ACMD.app.Engine_Layer.Mappa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.io.IOException;
import java.util.ArrayList;


import org.junit.Assert;

import org.junit.Test;


import com.ACMD.app.Engine_Layer.Entita.MType;
import com.ACMD.app.Engine_Layer.Entita.Monster;
import com.ACMD.app.Engine_Layer.Entita.MonsterFactory;
import com.ACMD.app.Engine_Layer.Mappa.Coordinates;
import com.ACMD.app.Engine_Layer.Mappa.Direction;
import com.ACMD.app.Engine_Layer.Mappa.MapGraph;


public class MapGraphTest {

    @Test
    public void testGetAllMonster() 
    {
        MapGraph m=new MapGraph();
        int i=0;
        ArrayList<Monster> monsters = new ArrayList<Monster>();
        MonsterFactory mf=new MonsterFactory();
        monsters.add(mf.create(MType.MOSTRO_MARINO));
        monsters.add(mf.create(MType.BOSS_DRAGO));
        monsters.add(mf.create(MType.COBOLDO));
        monsters.add(mf.create(MType.MAGO_OSCURO));
        monsters.add(mf.create(MType.ARMATURA));       
        boolean isEqual=true;
        ArrayList<Monster> expected = m.getAllMonster();
        for(Monster mo:monsters)
        {
           if(!expected.get(i).equals(monsters.get(i))) isEqual=false;
        }
        Assert.assertTrue(isEqual);
    }

    @Test
    public void testGetChestAt() 
    {
        MapGraph m = new MapGraph(); 
        Assert.assertNotNull(m.getChestAt(new Coordinates(8,7)));
    }

    
    @Test
    public void testGetChestAtNonStanza() 
    {
        MapGraph m = new MapGraph(); 
        Assert.assertNull(m.getChestAt(new Coordinates(1,19)));
    }


    @Test //(expected=ArithmeticException.class)
    public void testGetDirections() 
    {
        MapGraph m=new MapGraph(); 
        Coordinates c = new Coordinates(8,13);
        Coordinates[] cArray= {c,null,null,null};
        Assert.assertEquals(m.getDirections(new Coordinates(1,19)), cArray);
    }

    @Test
    public void testGetDirectionsInvalid() 
    {
        MapGraph m=new MapGraph(); 
        Coordinates c = new Coordinates(8,13);
        Coordinates[] cArray= {null,c,null,null};
        boolean isEqual=true;
        Coordinates[] expected = m.getDirections(new Coordinates(1,19));
        for(int i=0; i<cArray.length; i++)
        {
            if(cArray[i] != expected[i]) isEqual=false;
        }
        Assert.assertFalse(isEqual);
    }

    @Test
    public void testGetIconOf()
    {
        MapGraph m=new MapGraph(); 
        Assert.assertEquals(MapGraph.getIconOf(new Coordinates(1,19)), "Mappa_Definitiva.png");
    }
    @Test
    public void testGetIconOfStanza()
    {
        MapGraph m=new MapGraph(); 
        Assert.assertEquals(m.getIconOf(new Coordinates(8,7)), "MostroMarino.png");
    }
    @Test
    public void testGetIconOfStanzaFail()
    {
        MapGraph m=new MapGraph(); 
        boolean isEqual=m.getIconOf(new Coordinates(8,7)).equals( "Mappa_Kobold.png");
        Assert.assertFalse(isEqual);
    }


    @Test
    public void testGetMonsterAt() 
    {
        MapGraph m=new MapGraph(); 
        Monster monst= m.getMonsterAt(new Coordinates(8,7));

        Assert.assertEquals(monst, new MonsterFactory().create(MType.MOSTRO_MARINO));
    }

    @Test
    public void testGetMonsterAtFail() 
    {
        MapGraph m=new MapGraph(); 
        Monster monst= m.getMonsterAt(new Coordinates(8,7));

        Assert.assertNotEquals(monst, new MonsterFactory().create(MType.ARMATURA));
    }


    @Test
    public void testGetNodes() {
        MapGraph m=new MapGraph(); 
        Assert.assertNotNull(m.getNodes());
    }

    @Test
    public void testGetPathImage() {
        MapGraph m=new MapGraph();
        Assert.assertEquals(m.getPathImage(new Coordinates(1,19)), "Mappa_Definitiva.png");
    }
    @Test
    public void testGetPathImageStanza() {
        MapGraph m=new MapGraph();
        Assert.assertEquals(m.getPathImage(new Coordinates(2,4)), "ArmorMap.png");
    }
    @Test
    public void testGetPathImageStanzaFail() {
        MapGraph m=new MapGraph();
        Assert.assertNotEquals(m.getPathImage(new Coordinates(2,4)), "Mappa_Kobold.png");
    }
    @Test
    public void testGetPlayerPos() {
        MapGraph m=new MapGraph();
        Assert.assertEquals(new Coordinates(1,19), m.getPlayerPos());
        Assert.assertNotNull(new Coordinates(1,19));
    }
    


    @Test
    public void testGetPlayerPositionOf() {
        MapGraph m=new MapGraph();
        Assert.assertEquals(m.getPlayerPositionOf(new Coordinates(2,4)), new Coordinates(2,4) );
    }


    @Test
    public void testIsInChamber() {
        MapGraph m=new MapGraph();
        Assert.assertEquals(m.isInChamber(new Coordinates(1,19)), true);
    }

    @Test 
    public void testIsStanza() 
    {
        MapGraph m=new MapGraph();
        Assert.assertEquals(m.isStanza(new Coordinates(1,19)), false);
        Assert.assertEquals(m.isStanza(new Coordinates(8,7)), true);
        Assert.assertEquals(m.isStanza(new Coordinates(1,1)), false);
        
    }

    @Test
    public void testIsValidDirectionTo()
    {
        MapGraph m = new MapGraph(); 
        try{assertEquals(m.isValidDirectionTo(new Coordinates(1,19), Direction.NORTH), true);}catch(IOException e){}
        try{assertEquals(m.isValidDirectionTo(new Coordinates(1,19), Direction.SOUTH),false);}catch(IOException e){}
        try{m.isValidDirectionTo(new Coordinates(1,1), Direction.SOUTH);}catch(IOException e){assertNotNull(e);}

        
    }


    @Test
    public void testSetDragon()
    {
        MapGraph m = new MapGraph(); 
        assertEquals(m.getIconOf(new Coordinates(1,19)), "Mappa_Definitiva.png");
        m.setDragon();
        assertEquals(m.getIconOf(new Coordinates(1,19)), "Mappa_Drago.png");
        assertEquals(m.getIconOf(new Coordinates(8,7)), "MostroMarino.png");
    }

    @Test
    public void testSetFreeRoomAt() {
        MapGraph m=new MapGraph();
        m.setFreeRoomAt(new Coordinates(8,7));
        Assert.assertEquals( true, m.isFreeRoomAt(new Coordinates(8,7)));
        Assert.assertNotEquals( true, m.isFreeRoomAt(new Coordinates(7,7)));
        Assert.assertNotEquals( true, m.isFreeRoomAt(new Coordinates(2,4)));
    }

    @Test
    public void testSetPlayerPos() 
    {
        MapGraph m = new MapGraph();
        m.setPlayerPos(new Coordinates(2,2));
        assertEquals(m.getPlayerPos(),new Coordinates( 2,2));
        Assert.assertNotEquals(m.getPlayerPos(),new Coordinates( 4,2));}   

    @Test
    public void testValidDirectionOf() 
    {
        MapGraph m = new MapGraph();
        ArrayList<Direction> expected = new ArrayList<>();
        expected.add(Direction.NORTH);
        Assert.assertEquals(m.validDirectionOf(new Coordinates(1,19)), expected);
        expected.remove(0);
        Assert.assertEquals(m.validDirectionOf(new Coordinates(1,1)), expected);
    }
}
