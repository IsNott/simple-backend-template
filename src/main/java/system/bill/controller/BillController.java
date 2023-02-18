package system.bill.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import system.bill.entity.Bill;
import system.bill.mapper.BillMapper;
import system.comon.Result;
import system.user.entity.User;
import system.user.mapper.UserMapper;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jobob
 * @since 2023-02-18
 */
@RestController
@RequestMapping("/bill/")
public class BillController {

    @Resource
    private BillMapper billMapper;
    @Resource
    private UserMapper userMapper;

    @RequestMapping("sendBill")
    public Result sendBill(long userId,String sum,String type,String name,String phone){
        Bill bill = new Bill();
        bill.setBillTime(LocalDateTime.now());
        bill.setBillSum(sum);
        bill.setUserId(userId);
        bill.setPersonName(name);
        bill.setPhone(phone);
        bill.setBillStatus("0");
        bill.setBillType(type);
        bill.setCancelFlag("0");
        billMapper.insert(bill);
        return Result.ok();
    }

    @RequestMapping("update")
    public Result update(Bill bill){
        billMapper.updateById(bill);
        return Result.ok();
    }

    @RequestMapping("auditBill")
    public Result audit(String status,long billId,String remark){
        Bill bill = billMapper.selectById(billId);
        if(Objects.isNull(bill)){
            return Result.fail("没有对应的报账信息");
        }
        bill.setBillStatus(status);
        bill.setAuditTime(LocalDateTime.now());
        bill.setAuditRemark(remark);
        billMapper.updateById(bill);
        return Result.ok();
    }

    @RequestMapping("cancelBill")
    public Result audit(long billId){
        Bill bill = billMapper.selectById(billId);
        if(Objects.isNull(bill)){
            return Result.fail("没有对应的报账信息");
        }
        bill.setCancelFlag("1");
        return Result.ok();
    }

    @RequestMapping("billList")
    public Result bills(){
        List<Bill> bills = billMapper.selectList(new LambdaQueryWrapper<Bill>());
        return Result.okData(bills);
    }

    @RequestMapping("getBillByUserId")
    public Result selectBill(long userId,String status){
        User user = userMapper.selectById(userId);
        if(user == null){
            return Result.fail("找不到用户");
        }
        LambdaQueryWrapper<Bill> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Bill::getUserId,userId).eq(StringUtils.isNotBlank(status),Bill::getBillStatus,status);
        List<Bill> bills = billMapper.selectList(wrapper);
        return Result.okData(bills);
    }
}

