package system.act.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import system.act.entity.Active;
import system.act.mapper.ActiveMapper;
import system.comon.Result;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2023-03-23
 */
@RestController
@RequestMapping("/act/")
public class ActiveController {

    @Resource
    private ActiveMapper activeMapper;

    // 创建活动
    @RequestMapping("create")
    public Result create(Active active){
        if(Objects.isNull(active) || active.getActCreater() == null){
            return Result.fail("活动创建人不能为空");
        }
        active.setAuditStatus("0");
        activeMapper.insert(active);
        return Result.ok();
    }

    @RequestMapping("updateById")
    public Result updateById(Active active){
        if(Objects.isNull(active)){
            return Result.fail("活动不能为空");
        }
        Active activeInDb = activeMapper.selectById(active.getId());
        if(activeInDb == null){
            return Result.fail("没有找到活动");
        }
        activeMapper.updateById(active);
        return Result.ok();
    }

    @RequestMapping("auditAct")
    public Result auditAct(String activeId){
        if(StringUtils.isEmpty(activeId)){
            return Result.fail("活动id不能为空");
        }
        Active active = activeMapper.selectById(Long.parseLong(activeId));
        if(active == null){
            return Result.fail("没有找到活动");
        }
        active.setAuditStatus("1");
        activeMapper.updateById(active);
        return Result.ok();
    }

    @RequestMapping("list")
    public Result list(String status){
        LambdaQueryWrapper<Active> wrapper = new LambdaQueryWrapper<Active>();
        wrapper.eq(Active::getAuditStatus,status);
        List<Active> actives = activeMapper.selectList(wrapper);
        return Result.okData(actives);
    }

    @RequestMapping("getById")
    public Result getById(String id){
        Active actives = activeMapper.selectById(Long.parseLong(id));
        return Result.okData(actives);
    }

    @RequestMapping("delById")
    public Result delById(String id){
        if(StringUtils.isEmpty(id)){
            return Result.fail("活动id不能为空");
        }
        if(id.contains(",")){
            List<String> ids = Arrays.asList(id.split(","));
            ArrayList<Long> longs = new ArrayList<>();
            for (String s : ids) {
                longs.add(Long.parseLong(s));
            }
            activeMapper.deleteBatchIds(longs);
        }else {
            activeMapper.deleteById(Long.parseLong(id));
        }
        return Result.ok();
    }

}

