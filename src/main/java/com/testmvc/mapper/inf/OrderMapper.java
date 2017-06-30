package com.testmvc.mapper.inf;

import com.testmvc.domain.Order;

import java.util.List;


/**
 * The Interface OrderMapper.
 *
 */
public interface OrderMapper {

  List<Order> getOrdersByUsername(String username);

  Order getOrder(int orderId);

  void insertOrder(Order order);

  void insertOrderStatus(Order order);

}
