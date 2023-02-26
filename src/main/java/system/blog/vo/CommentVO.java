package system.blog.vo;

import lombok.Data;
import system.blog.entity.BlogComment;

/**
 * 评论对象VO
 */

@Data
public class CommentVO extends BlogComment {
   private String senderName; // 发送评论人昵称
}
