package system.comon;

import system.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 后台会话管理
 * @author
 * @Date 2023/2/17
 */

@Slf4j
public class SystemParam {
    // 获得当前访问上下文的Request对象，每次前端访问后端视都有一个Request，目的就是拿到这个Request
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    // 拿到访问对象后，获得Request里的会话（session）后面用来存储登录信息，目前登录信息也就是在登出时使用
    public static HttpSession getSession(){
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        return session;
    }

    // 存储User对象的id到session作为登录标识
    public static User storeLoginUser(User user){
        HttpSession session = getSession();
        User attribute = (User)session.getAttribute(user.getId()+"");
        if(attribute != null){
            log.info("{}用户已登录",attribute.getUsername());
            return attribute;
        }else {
            log.info("{}用户登录成功",user.getUsername());
            session.setAttribute(user.getId()+"",user);
        }
        return user;
    }

    // 到Session中检查用户是否已经登录
    public static User checkLogin(String userId) throws MyException{
        HttpSession session = getSession();
        User attribute = (User)session.getAttribute(userId);
        if(attribute != null){
           return attribute;
        }else {
           throw new MyException("未登录",-999L);
        }
    }

    // 检查用户是否是管理员，未启用
    public static boolean checkIsAdmin(String userId) throws MyException{
        HttpSession session = getSession();
        User attribute = checkLogin(userId);
        if( "1".equals(attribute.getUserType())){
            return true;
        }else {
            throw new MyException("无权限操作",-999L);
        }
    }



}
