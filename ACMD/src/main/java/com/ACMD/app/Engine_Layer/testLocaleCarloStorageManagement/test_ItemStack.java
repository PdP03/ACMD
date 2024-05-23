
//? come fare singleton su 

package com.ACMD.app.Engine_Layer.testLocaleCarloStorageManagement;

import com.ACMD.app.Engine_Layer.StorageManagement.*;

//TROVARE UN MODO PER DIRE DI LANCIARE UN MESSAGGIO QUANDO COMPILA
//necessario togliere abstract da classe Item

public class test_ItemStack
{
	public test_ItemStack()
	{
		System.out.println("Test item");
	 //_costruttori

	 	ItemStack i1 = new ItemStack("primo item",  ItemType.ARMA, 10);
		ItemStack i2 = new ItemStack("secondo item",ItemType.POZIONE,1,3);

	 //_metodi      : se si vuole automatizzare meglio si possono fare dei controlli che se il valore atteso non è quello giusto, allora lancia una eccezione

		System.out.println( i1.getQuantity() );
		i1.addQuantity();    System.out.println( i1.getQuantity() );
		i1.removeQuantity(); System.out.println( i1.getQuantity() );
		System.out.println( i1 );

	 //_eccezioni

		try { ItemStack ia = new ItemStack("", ItemType.ARMA,10);  } catch(IllegalArgumentException e) { System.out.println("Primo costruttore"); }
		try { ItemStack ib = new ItemStack("j",ItemType.ARMA,-1);  } catch(IllegalArgumentException e) { System.out.println("Primo costruttore"); }
		try { ItemStack ic = new ItemStack("k",ItemType.ARMA,0,0); } catch(IllegalArgumentException e) { System.out.println("Primo costruttore"); }

		ItemStack id = new ItemStack("f",ItemType.ARMA,1,1);
		try { id.removeQuantity(); } catch( noItem_Exception e ) { System.out.println("questo non si dovrebbe attivare"); }
		try { id.removeQuantity(); } catch( noItem_Exception e ) { System.out.println("Tutti item rimossi"); }

        System.out.println("Test completato");
	}
}


