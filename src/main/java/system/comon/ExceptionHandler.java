package system.comon;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Controller
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler({NullPointerException.class, MyException.class})
    public Result exceptionHandler(Exception e) {
        log.error("error : {}", e.getMessage(), e);
        return Result.fail(StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : "出错了");
    }
}
