package org.javacream.store.api;

import org.javacream.test.SpecifiedBy;

@SpecifiedBy(url="https://github.com/Javacream/org.javacream.training.testdriven/blob/integrata_18.8.2021/specs/storeservice.txt")
public interface StoreService {

	int getStock(String category, String id);

}