package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;

import java.util.List;

/**
 * @author xjsaber
 */
public interface ICategoryService {

    /**
     * 查询分类只包含同级
     * @param categoryId 分类id
     * @return
     */
    ServerResponse<Category> getCategory(Integer categoryId);

    /**
     * 增加分类
     * @param categoryId 父类id
     * @param categoryName 分类名称
     * @return
     */
    ServerResponse<Category> addCategory(Integer categoryId, String categoryName);

    /**
     * 修改分类名称
     * @param categoryId 分类id
     * @param categoryName 分类名称
     * @return
     */
    ServerResponse<String> setCategoryName(Integer categoryId, String categoryName);

    /**
     * 深度获得分类
     * @param categoryId 分类编号
     * @return
     */
    ServerResponse<List<Category>> getChildrenParallelCategory(Integer categoryId);

    /**
     * 深度获得子类同级的分类
     * @param categoryId 分类编号
     * @return
     */
    ServerResponse<List<Integer>> selectCategoryAndChildrenById(Integer categoryId);
}
