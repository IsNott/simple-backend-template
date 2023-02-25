package system.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import system.blog.entity.BlogComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2023-02-25
 */
@Mapper
public interface BlogCommentMapper extends BaseMapper<BlogComment> {

}
