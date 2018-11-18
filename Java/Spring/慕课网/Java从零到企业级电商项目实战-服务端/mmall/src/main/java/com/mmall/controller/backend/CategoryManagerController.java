package com.mmall.controller.backend;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.Category;
import com.mmall.pojo.User;
import com.mmall.service.ICategoryService;
import com.mmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author xjsaber
 */
@Controller
@RequestMapping("/manager/category/")
public class CategoryManagerController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryService iCategoryService;

    @ResponseBody
    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    public ServerResponse<Category> getCategory(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId){
        return iCategoryService.getCategory(categoryId);
    }

    @ResponseBody
    @RequestMapping(value = "add_category.do", method = RequestMethod.GET)
    public ServerResponse<Category> addCategory(HttpSession session, String categoryName, @RequestParam(value = "categoryId", defaultValue = "0") int categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }
        // 校验一下是否是管理员
        ServerResponse<User> response = iUserService.checkAdminRole(user);
        if (response.isSuccess()){
            return iCategoryService.addCategory(categoryId, categoryName);
        } else {
            return ServerResponse.createByErrorMessage("无权操作");
        }
    }

    @ResponseBody
    @RequestMapping(value = "get_category.do", method = RequestMethod.GET)
    public ServerResponse getChildrenParallelCategory(HttpSession session, @RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }

        if (iUserService.checkAdminRole(user).isSuccess()){
            // 查询子节点的category信息，并且不递归，保持平级
            return iCategoryService.getChildrenParallelCategory(categoryId);
        }
        else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }

    @ResponseBody
    @RequestMapping(value = "get_deep_category.do", method = RequestMethod.GET)
    public ServerResponse getCategoryAndDeepChildrenCategory(HttpSession session, @RequestParam(value = "categoryId" ,defaultValue = "0") Integer categoryId){
        User user = (User)session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "用户未登录，请登录");
        }

        if (iUserService.checkAdminRole(user).isSuccess()){
            // 查询当前节点的id和递归字节点的id
            return iCategoryService.selectCategoryAndChildrenById(categoryId);
        }
        else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员权限");
        }
    }
}
