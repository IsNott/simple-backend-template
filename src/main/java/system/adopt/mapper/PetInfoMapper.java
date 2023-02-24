package system.adopt.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import system.adopt.entity.PetInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2023-02-19
 */
@Mapper
public interface PetInfoMapper extends BaseMapper<PetInfo> {

    @Update("update pet_info set adopt_flag = '1',adopter_id = null,adopter_phone = null,adopter_name = null,adopter_area = null,adopt_time = null where id = ${id}")
    void setPet2UnAdopt(@Param("id") Long id);
}
