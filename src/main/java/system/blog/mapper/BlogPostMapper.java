package system.blog.mapper;

import org.apache.ibatis.annotations.Mapper;
import system.blog.entity.BlogPost;
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
public interface BlogPostMapper extends BaseMapper<BlogPost> {

}
