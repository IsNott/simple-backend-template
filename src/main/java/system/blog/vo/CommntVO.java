package system.blog.vo;

import lombok.Data;
import system.blog.entity.BlogComment;

import java.time.LocalDateTime;

@Data
public class CommntVO extends BlogComment {
   private String senderName;
}
