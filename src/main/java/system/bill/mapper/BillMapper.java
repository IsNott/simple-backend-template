package system.bill.mapper;

import org.apache.ibatis.annotations.Mapper;
import system.bill.entity.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2023-02-18
 */
@Mapper
public interface BillMapper extends BaseMapper<Bill> {

}
