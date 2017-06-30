package com.testmvc.service;

import com.testmvc.domain.Item;
import com.testmvc.domain.LineItem;
import com.testmvc.domain.Order;
import com.testmvc.domain.Sequence;
import com.testmvc.mapper.inf.ItemMapper;
import com.testmvc.mapper.inf.LineItemMapper;
import com.testmvc.mapper.inf.OrderMapper;
import com.testmvc.mapper.inf.SequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Class OrderService.
 *
 */
@Service
public class OrderService {

    @Autowired(required = false)
  private ItemMapper itemMapper;
    @Autowired(required = false)
  private OrderMapper orderMapper;
    @Autowired(required = false)
  private SequenceMapper sequenceMapper;
    @Autowired(required = false)
  private LineItemMapper lineItemMapper;

  /**
   * Insert order.
   *
   * @param order the order
   */
  @Transactional
  public void insertOrder(Order order) {
    order.setOrderId(getNextId("ordernum"));
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = order.getLineItems().get(i);
      String itemId = lineItem.getItemId();
      Integer increment = new Integer(lineItem.getQuantity());
      Map<String, Object> param = new HashMap<String, Object>(2);
      param.put("itemId", itemId);
      param.put("increment", increment);
      itemMapper.updateInventoryQuantity(param);
    }

    orderMapper.insertOrder(order);
    orderMapper.insertOrderStatus(order);
    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = order.getLineItems().get(i);
      lineItem.setOrderId(order.getOrderId());
      lineItemMapper.insertLineItem(lineItem);
    }
  }

  /**
   * Gets the order.
   *
   * @param orderId the order id
   * @return the order
   */
  @Transactional
  public Order getOrder(int orderId) {
    Order order = orderMapper.getOrder(orderId);
    order.setLineItems(lineItemMapper.getLineItemsByOrderId(orderId));

    for (int i = 0; i < order.getLineItems().size(); i++) {
      LineItem lineItem = order.getLineItems().get(i);
      Item item = itemMapper.getItem(lineItem.getItemId());
      item.setQuantity(itemMapper.getInventoryQuantity(lineItem.getItemId()));
      lineItem.setItem(item);
    }

    return order;
  }

  /**
   * Gets the orders by username.
   *
   * @param username the username
   * @return the orders by username
   */
  public List<Order> getOrdersByUsername(String username) {
    return orderMapper.getOrdersByUsername(username);
  }

  /**
   * Gets the next id.
   *
   * @param name the name
   * @return the next id
   */
  public int getNextId(String name) {
    Sequence sequence = new Sequence(name, -1);
    sequence = sequenceMapper.getSequence(sequence);
    if (sequence == null) {
      throw new RuntimeException(
          "Error: A null sequence was returned from the database (could not get next " + name + " sequence).");
    }
    Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
    sequenceMapper.updateSequence(parameterObject);
    return sequence.getNextId();
  }

}
