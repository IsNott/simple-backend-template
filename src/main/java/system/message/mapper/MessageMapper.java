package system.message.mapper;

import system.message.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2023-02-18
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {

}
