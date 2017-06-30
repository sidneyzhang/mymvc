package com.testmvc.mapper.inf;

import com.testmvc.domain.LineItem;

import java.util.List;


/**
 * The Interface LineItemMapper.
 *
 */
public interface LineItemMapper {

  List<LineItem> getLineItemsByOrderId(int orderId);

  void insertLineItem(LineItem lineItem);

}
