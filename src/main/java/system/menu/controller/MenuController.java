package system.menu.controller;


import system.comon.Result;
import system.menu.entity.Menu;
import system.menu.entity.MenuView;
import system.menu.mapper.MenuMapper;
import system.menu.mapper.MenuViewMapper;
import system.user.entity.User;
import system.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2023-02-17
 */
@RestController
@RequestMapping("/menu/")
public class MenuController {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private MenuViewMapper menuViewMapper;
    @Resource
    private UserMapper userMapper;

    // 根据用户id获取当前用户可浏览菜单
    @RequestMapping("getMenu")
    public Result getMenu(long userId) {
        User user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            return Result.fail("用户不存在");
        }
        LambdaQueryWrapper<MenuView> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(MenuView::getUserType, user.getUserType());
        List<MenuView> menuViews = menuViewMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(menuViews)) {
            return Result.fail("该用户类型没有可浏览菜单");
        }
        ArrayList<Menu> menus = new ArrayList<>();
        for (MenuView view : menuViews) {
            Long menuId = view.getMenuId();
            Menu menu = menuMapper.selectById(menuId);
            if (!Objects.isNull(menu)) {
                menus.add(menu);
            }
        }
        return Result.okData(menus);
    }

    // 添加菜单，userType 0-普通用户可见 1-管理员可见 all-全部用户可见
    @RequestMapping("addMenu")
    public Result addMenu(Menu menu, String userType) {
        if (StringUtils.isEmpty(userType)) {
            return Result.fail("userType为空");
        }
        menuMapper.insert(menu);
        ArrayList<MenuView> menuViews = new ArrayList<>();
        if ("all".equals(userType)) {
            MenuView menuView = new MenuView().setUserType("0");
            MenuView menuView1 = new MenuView().setUserType("1");
            menuViews.add(menuView);
            menuViews.add(menuView1);
        } else {
            MenuView menuView = new MenuView().setUserType(userType);
            menuViews.add(menuView);
        }
        for (MenuView menuView : menuViews) {
            menuViewMapper.insert(menuView);
        }
        return Result.ok();
    }

    // 删除菜单 多个以","分隔
    @RequestMapping("delMenuById")
    public Result delMenuById(String menuId) {
        if (menuId.contains(",")) {
            List<String> menuIds = Arrays.asList(menuId.split(","));
            for (String id : menuIds) {
                menuViewMapper.delete(new LambdaQueryWrapper<MenuView>().eq(MenuView::getMenuId, id));
                menuMapper.deleteById(Long.parseLong(id));
            }
        } else {
            menuViewMapper.delete(new LambdaQueryWrapper<MenuView>().eq(MenuView::getMenuId, menuId));
            menuMapper.deleteById(Long.parseLong(menuId));
        }
        return Result.ok();
    }

    // 更新菜单
    @RequestMapping("update")
    public Result update(Menu menu) {
        menuMapper.updateById(menu);
        return Result.ok();
    }

}

