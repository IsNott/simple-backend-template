package system.adopt.mapper;

import org.apache.ibatis.annotations.Mapper;
import system.adopt.entity.PetCatalog;
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
public interface PetCatalogMapper extends BaseMapper<PetCatalog> {

}
