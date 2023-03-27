package system.donate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Donate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 捐赠人
     */
    private Long doneterId;

    /**
     * 捐赠时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime donateTime;

    /**
     * 捐赠类型
     */
    private String donateType;

    /**
     * 捐赠地址
     */
    private String donateDestination;

    /**
     * 捐赠人联系电话
     */
    private String donaterPhone;

    /**
     * 接收人地址
     */
    private String receiveDestination;

    /**
     * 接收人电话
     */
    private String receiverPhone;

    /**
     * 接收人姓名
     */
    private String receiverName;


}
