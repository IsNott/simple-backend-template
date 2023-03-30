package system.user.controller;


import org.springframework.format.annotation.DateTimeFormat;
import system.comon.Result;
import system.comon.SystemParam;
import system.user.entity.User;
import system.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @author jobob
 * @since 2023-02-17
 */
@RestController
@RequestMapping("/user/")
public class UserController {

    @Resource
    private UserMapper userMapper;

    // 登录
    @RequestMapping("login")
    public Result login(String username, String password) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username).eq(User::getPassword, password);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            return Result.fail();
        }
        user = SystemParam.storeLoginUser(user);
        return Result.okData(user);
    }

    // 登出
    @RequestMapping("logout")
    public Result logout(long id) {
        // 移除会话里的对象id
        SystemParam.getSession().removeAttribute(id + "");
        return Result.ok();
    }

    // 注册
    @RequestMapping("signUp")
    public Result sign(String username, String password, String gender, String birth, String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (!Objects.isNull(user)) {
            return Result.fail("用户名已存在");
        }

        User user1 = new User()
                .setPassword(password)
                .setUsername(username)
                .setUserType("0")
                .setBirth(LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .setGender(gender)
                .setPhone(phone);
        userMapper.insert(user1);
        return Result.ok();
    }

    // 更新user
    @RequestMapping("update")
    public Result update(User user) {
        if (Objects.isNull(user)) {
            return Result.fail("user对象为空");
        }
        userMapper.updateById(user);
        return Result.ok();
    }

    // 注册管理员
    @RequestMapping("signUpAdmin")
    public Result signUpAdmin(String username, String password, String gender, String birth, String phone) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if (!Objects.isNull(user)) {
            return Result.fail("用户名已存在");
        }
        User admin = new User()
                .setPassword(password)
                .setUsername(username)
                .setUserType("1").
                setBirth(LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .setGender(gender)
                .setPhone(phone);
        userMapper.insert(admin);
        return Result.ok();
    }

    // 移除用户
    @RequestMapping("removeUserById")
    public Result removeUserById(long id) {
        User selectOne = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, id));
        if (Objects.isNull(selectOne)) {
            return Result.fail("要移除的账户不存在");
        }
        userMapper.deleteById(id);
        return Result.ok();
    }

    // 查询账号信息
    @RequestMapping("selectById")
    public Result selectById(long userId) {
        User user = userMapper.selectById(userId);
        if (Objects.isNull(user)) {
            return Result.fail("用户不存在");
        }
        return Result.okData(user);
    }

    // 查询账号列表
    @RequestMapping("userList")
    public Result uesrList() {
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>());
        return Result.okData(users);
    }
}

