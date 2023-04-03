package system.message.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 留言对象
 * </p>
 *
 * @author jobob
 * @since 2023-02-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 留言信息
    private String context;

    // 回复信息
    private String respContext;

    // 发送人id
    private Long senderId;

    // 发送时间
    private LocalDateTime sendTime;

    // 回复时间
    private LocalDateTime respTime;

    // 审核状态
    private String auditStatus;


}
