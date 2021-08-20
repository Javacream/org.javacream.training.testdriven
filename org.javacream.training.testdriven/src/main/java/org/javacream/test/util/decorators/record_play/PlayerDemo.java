package org.javacream.test.util.decorators.record_play;

import org.javacream.store.api.StoreService;

public class PlayerDemo {

	public static void main(String[] args) {
		StoreService storeService = XmlPlayer.createPlayer(StoreService.class, "store.xml");
		
		System.out.println(storeService.getStock("books", "0-123-12345-1-de"));
	
	}

}
