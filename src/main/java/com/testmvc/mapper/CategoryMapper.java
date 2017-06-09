package com.testmvc.mapper;

import com.testmvc.domain.Category;

import java.util.List;


/**
 * The Interface CategoryMapper.
 *
 */
public interface CategoryMapper {

  List<Category> getCategoryList();

  Category getCategory(String categoryId);

}
