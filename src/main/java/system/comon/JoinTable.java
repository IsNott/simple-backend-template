package system.comon;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Controller
@ResponseBody
public @interface JoinTable {

    /**
     * 1 一对一
     * 2 一对多
     * @return
     */
    int mode() default 1;

    String tableName();

    String field();

    Class voClass();

    Class joinClass();
}
