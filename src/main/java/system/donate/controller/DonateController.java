package system.donate.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import system.act.entity.Active;
import system.comon.Result;
import system.donate.entity.Donate;
import system.donate.mapper.DonateMapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2023-03-21
 */
@RestController
@RequestMapping("/donate/")
public class DonateController {

    @Resource
    private DonateMapper donateMapper;

    @RequestMapping("create")
    public Result create(Donate donate){
        if(Objects.isNull(donate)){
         return Result.fail("捐赠不能为空");
        }
        donate.setDonateTime(LocalDateTime.now());
        donate.setAuditStatus("0");
        donateMapper.insert(donate);
        return Result.ok();
    }

    @RequestMapping("audit")
    public Result auditAct(String id){
        if(StringUtils.isEmpty(id)){
            return Result.fail("捐赠id不能为空");
        }
        Donate donate = donateMapper.selectById(Long.parseLong(id));
        if(donate == null){
            return Result.fail("没有找到活动");
        }
        donate.setAuditStatus("1");
        donateMapper.updateById(donate);
        return Result.ok();
    }

    @RequestMapping("list")
    public Result list(String id,String donaterId){
        LambdaQueryWrapper<Donate> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.isNotBlank(id),Donate::getId,id)
                .eq(StringUtils.isNotBlank(donaterId),Donate::getDoneterId,donaterId);
        List<Donate> donates = donateMapper.selectList(wrapper);
        return Result.okData(donates);
    }

    @RequestMapping("update")
    public Result update(Donate donate){
        if(Objects.isNull(donate)){
            return Result.fail("更新对象不能为空");
        }
        donateMapper.updateById(donate);
        return Result.ok();
    }

    @RequestMapping("getById")
    public Result update(String donateId){
        if(StringUtils.isEmpty(donateId)){
            return Result.fail("捐赠id不能为空");
        }
        Donate donate = donateMapper.selectById(Long.parseLong(donateId));
        return Result.okData(donate);
    }

    @RequestMapping("delById")
    public Result delById(String id){
        if(StringUtils.isEmpty(id)){
            return Result.fail("删除id不能为空");
        }
       if(id.contains(",")){
           List<String> ids = Arrays.asList(id.split(","));
           donateMapper.deleteBatchIds(ids);
       }else {
           donateMapper.deleteById(Long.parseLong(id));
       }
        return Result.ok();
    }
}

