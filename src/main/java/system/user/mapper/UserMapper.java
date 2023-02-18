package system.user.mapper;

import system.user.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2023-02-17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
