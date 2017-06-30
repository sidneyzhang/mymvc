package com.testmvc.mapper.inf;

import com.testmvc.domain.Item;

import java.util.List;
import java.util.Map;


/**
 * The Interface ItemMapper.
 *
 */
public interface ItemMapper {

  void updateInventoryQuantity(Map<String, Object> param);

  int getInventoryQuantity(String itemId);

  List<Item> getItemListByProduct(String productId);

  Item getItem(String itemId);

}
