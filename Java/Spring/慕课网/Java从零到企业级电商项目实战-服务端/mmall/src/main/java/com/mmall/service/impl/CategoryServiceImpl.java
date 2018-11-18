package com.mmall.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.pojo.Category;
import com.mmall.service.ICategoryService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author xjsaber
 */
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ServerResponse<Category> getCategory(Integer categoryId) {
        Category category = categoryMapper.selectCategory(categoryId);
        if (category != null){
            return ServerResponse.createBySuccess(category);
        }
        return ServerResponse.createByErrorMessage("没有这个分类");
    }

    @Override
    public ServerResponse<Category> addCategory(Integer categoryId, String categoryName) {
        if (categoryId == null  || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "添加品类参数错误");
        }
        Category addCategory = new Category();
        addCategory.setName(categoryName);
        addCategory.setId(categoryId);
        addCategory.setStatus(true);

        int rowCount = categoryMapper.insert(addCategory);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("添加分类成功");
        }
        return ServerResponse.createByErrorMessage("添加分类失败");
    }

    @Override
    public ServerResponse<String> setCategoryName(Integer categoryId, String categoryName) {
        if (categoryId == null  || StringUtils.isBlank(categoryName)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ILLEGAL_ARGUMENT.getCode(), "更新品类参数错误");
        }
        Category updateCategory = new Category();
        updateCategory.setName(categoryName);
        updateCategory.setId(categoryId);
        updateCategory.setStatus(true);

        int rowCount = categoryMapper.updateByPrimaryKey(updateCategory);
        if (rowCount > 0){
            return ServerResponse.createBySuccessMessage("更新分类名字成功");
        }
        return ServerResponse.createByErrorMessage("更新分类名字失败");
    }

    @Override
    public ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId){
        List<Category> categoryList = categoryMapper.selectChildrenParallelCategory(categoryId);
        if (CollectionUtils.isEmpty(categoryList)){
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categoryList);
    }

    @Override
    public ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId){
        Set<Category> categorySet = Sets.newHashSet();
        findChildCategory(categorySet, categoryId);

        List<Integer> categoryIdList = Lists.newArrayList();
        if (categoryId != null){
            for (Category categoryItem: categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(categoryIdList);
    }

    /**
     * 递归算法，算出子节点
     * @return
     */
    private Set<Category> findChildCategory(Set<Category> categorySet, Integer categoryId){
        Category category = categoryMapper.selectByPrimaryKey(categoryId);
        if (category != null){
            categorySet.add(category);
        }
        List<Category> categoryList = categoryMapper.selectChildrenParallelCategory(categoryId);
        for (Category categoryItem: categoryList){
            findChildCategory(categorySet, categoryItem.getId());
        }
        return categorySet;
    }
}
