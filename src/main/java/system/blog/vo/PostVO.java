package system.blog.vo;

import lombok.Data;
import system.blog.entity.BlogPost;

@Data
public class PostVO extends BlogPost {

    private String nickName;
}
