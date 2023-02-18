package system.comon;

import system.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author Nott
 * @Date 2023/2/17
 */

@Slf4j
public class SystemParam {
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public static HttpSession getSession(){
        HttpServletRequest request = getRequest();
        HttpSession session = request.getSession();
        return session;
    }

    public static ServletContext getServletContext()  {
        ServletContext servletContext = getSession().getServletContext();
        return servletContext;
    }

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

    public static User checkLogin(String userId) throws MyException{
        HttpSession session = getSession();
        User attribute = (User)session.getAttribute(userId);
        if(attribute != null){
           return attribute;
        }else {
           throw new MyException("未登录",-999L);
        }
    }

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
