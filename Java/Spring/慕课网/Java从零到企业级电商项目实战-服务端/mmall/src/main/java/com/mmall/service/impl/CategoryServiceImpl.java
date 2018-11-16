package com.mmall.service.impl;

import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.dao.CategoryMapper;
import com.mmall.service.ICategoryService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmall.pojo.Category;

/**
 * @author xjsaber
 */
@Service("categoryService")
public class CategoryServiceImpl implements ICategoryService {

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
    public ServerResponse<Category> getDeepCategory(Integer categoryId) {
        return null;
    }
}
