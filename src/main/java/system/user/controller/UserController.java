package system.user.controller;


import system.comon.Result;
import system.comon.SystemParam;
import system.user.entity.User;
import system.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 *
 *
 * @author jobob
 * @since 2023-02-17
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("login")
    public Result login(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getPassword, password);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            return Result.fail();
        }
        user = SystemParam.storeLoginUser(user);
        return Result.okData(user);
    }

    @RequestMapping("logout")
    public Result logout(long id){
        SystemParam.getSession().removeAttribute(id+"");
        return Result.ok();
    }

    @RequestMapping("signUp")
    public Result sign(String username,String password,String realName,String area){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if(!Objects.isNull(user)) {
            return Result.fail("用户名已存在");
        }
        User user1 = new User().setPassword(password).setUsername(username).setUserType("0");
        userMapper.insert(user1);
        return Result.ok();
    }

    @RequestMapping("update")
    public Result update(User user) {
        if(Objects.isNull(user)){
            return Result.fail("user对象为空");
        }
        userMapper.updateById(user);
        return Result.ok();
    }

    @RequestMapping("signUpAdmin")
    public Result signUpAdmin(String username,String password){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if(!Objects.isNull(user)) {
            return Result.fail("用户名已存在");
        }
        User admin = new User().setPassword(password).setUsername(username).setUserType("1");
        userMapper.insert(admin);
        return Result.ok();
    }

    @RequestMapping("removeUserById")
    public Result removeUserById(long userId) {
        User selectOne = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        if(Objects.isNull(selectOne)){
            return Result.fail("要移除的账户不存在");
        }
        userMapper.deleteById(userId);
        return Result.ok();
    }

    @RequestMapping("selectById")
    public Result selectById(long userId){
        User user = userMapper.selectById(userId);
        if(Objects.isNull(user)){
            return Result.fail("用户不存在");
        }
        return Result.okData(user);
    }

    @RequestMapping("userList")
    public Result uesrList(){
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>());
        return Result.okData(users);
    }
}

