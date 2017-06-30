package com.testmvc.mapper.inf;

import com.testmvc.domain.Product;

import java.util.List;


/**
 * The Interface ProductMapper.
 *
 */
public interface ProductMapper {

  List<Product> getProductListByCategory(String categoryId);

  Product getProduct(String productId);

  List<Product> searchProductList(String keywords);

}
