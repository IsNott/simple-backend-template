package system.comon;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class FileUtils {

    public static String upload(MultipartFile file) {
        if (file == null) {
            return null;
        }

        //获取文件后缀
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1, file.getOriginalFilename().length());

        String savePath = System.getProperty("user.dir") + "\\src\\main\\resources\\static";
        File savePathFile = new File(savePath);
        if (!savePathFile.exists()) {
            //若不存在改目录，则创建目录
            savePathFile.mkdir();
        }
        String filename = new Date().getTime() + "." + suffix;
        log.info("保存图片的路径:{}", savePath + "\\" + filename);
        File upload = new File(savePath + "\\" + filename);
        try {
            file.transferTo(upload);
        } catch (IOException e) {
            log.error("文件上传出错：{}",e.getMessage());
        }
        return filename;
    }
}
