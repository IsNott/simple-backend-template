package system.adopt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 宠物领养记录信息
 * </p>
 *
 * @author jobob
 * @since 2023-02-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PetInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long catalogId; // 分类id

    private String petName; // 宠物姓名

    private LocalDateTime releaseTime;  // 记录发布时间

    /**
     * 0-未领养 1-领养
     */
    private String adoptFlag;

    private String area; // 宠物地区

    /**
     * 0-女生 1-男生
     */
    private String petGender;

    private Long adopterId; // 领养人账号id

    private String adopterPhone; // 领养人手机号

    private String adopterName; // 领养人姓名

    private String adopterArea; // 领养人地区

    private LocalDateTime adoptTime; // 领养时间


}
