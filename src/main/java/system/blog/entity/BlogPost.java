package system.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2023-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 正文
     */
    private String context;

    /**
     * 发送人id
     */
    private Long userId;

    /**
     * 发送时间
     */
    private LocalDateTime sendTime;

    /**
     * 点赞数
     */
    private Long likes;

    /**
     * 标题
     */
    private String title;

    /**
     * 封面图片路径
     */
    private String coverPath;

    /**
     * 是否推荐
     */
    private String recommendFlag;

    /**
     * 浏览量
     */
    private Long postView;


}
