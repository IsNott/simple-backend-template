package system.user.controller;


import org.apache.commons.lang3.StringUtils;
import system.comon.Result;
import system.comon.SystemParam;
import system.user.entity.User;
import system.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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

    // 获取验证码
    @RequestMapping("captcha")
    public Result captcha(){
        HttpSession session = SystemParam.getSession();
        Object captcha = session.getAttribute("captcha");
        if(captcha != null){
            session.removeAttribute("captcha");
        }
        Random random = new Random();
        int i = random.nextInt(6666);
        session.setAttribute("captcha",String.valueOf(i));
        return Result.okData(String.valueOf(i));
    }

    // 登录
    @RequestMapping("login")
    public Result login(String username, String password,String captcha) {
        if(Objects.isNull(captcha)){
            return Result.fail("验证码不能为空");
        }

        String captcha1 = (String)SystemParam.getSession().getAttribute("captcha");
        if(Objects.isNull(captcha1)){
            return Result.fail("没有获取验证码");
        }
        if(!captcha.equals(captcha1)){
            return Result.fail("验证码错误");
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username).eq(User::getPassword, password);
        User user = userMapper.selectOne(wrapper);
        if (Objects.isNull(user)) {
            return Result.fail("账号或密码错误！");
        }
        user = SystemParam.storeLoginUser(user);
        return Result.okData(user);
    }

    // 登出
    @RequestMapping("logout")
    public Result logout(long id){
        // 移除会话里的对象id
        SystemParam.getSession().removeAttribute(id+"");
        return Result.ok();
    }

    // 注册
    @RequestMapping("signUp")
    public Result sign(String username,String password,String userType,String nickName){
        if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(userType) || StringUtils.isEmpty(nickName)){
            return Result.fail("账号、密码、用户类型、昵称其中一项不能为空");
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().eq(User::getUsername, username);
        User user = userMapper.selectOne(wrapper);
        if(!Objects.isNull(user)) {
            return Result.fail("用户名已存在");
        }
        User user1 = new User().setPassword(password).setUsername(username).setUserType(userType).setNickName(nickName);
        userMapper.insert(user1);
        return Result.ok();
    }

    // 更新user
    @RequestMapping("update")
    public Result update(User user) {
        if(Objects.isNull(user)){
            return Result.fail("user对象为空");
        }
        userMapper.updateById(user);
        return Result.ok();
    }

    // 注册管理员
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

    // 移除用户
    @RequestMapping("removeUserById")
    public Result removeUserById(long userId) {
        User selectOne = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getId, userId));
        if(Objects.isNull(selectOne)){
            return Result.fail("要移除的账户不存在");
        }
        userMapper.deleteById(userId);
        return Result.ok();
    }

    // 查询账号信息
    @RequestMapping("selectById")
    public Result selectById(long userId){
        User user = userMapper.selectById(userId);
        if(Objects.isNull(user)){
            return Result.fail("用户不存在");
        }
        return Result.okData(user);
    }

    // 查询账号列表
    @RequestMapping("userList")
    public Result uesrList(){
        List<User> users = userMapper.selectList(new LambdaQueryWrapper<User>());
        return Result.okData(users);
    }
}

