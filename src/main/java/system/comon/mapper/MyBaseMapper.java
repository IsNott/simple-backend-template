package system.comon.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyBaseMapper {

    List<Map<String, Object>> selectList(@Param(value = "sql") String sql);
}
