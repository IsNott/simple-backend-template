package system.comon;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import system.comon.mapper.MyBaseMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class CommonUtils {

    public static String getHttpRequestBody(HttpServletRequest request){
        StringBuilder req = null;
        try {
            BufferedReader reader = request.getReader();
            req = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                req.append(line);
            }
        } catch (IOException e) {
            log.error("getHttpRequestBody error:{}",e.getMessage(),e);
        }
        return req.toString();
    }

}
