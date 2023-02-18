package system.message.controller;


import system.comon.Result;
import system.message.entity.Message;
import system.message.mapper.MessageMapper;
import system.message.vo.MessageVO;
import system.user.entity.User;
import system.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
 * @since 2023-02-18
 */
@RestController
@RequestMapping("/message/")
public class MessageController {

    @Resource
    private MessageMapper messageMapper;
    @Resource
    private UserMapper userMapper;

    @RequestMapping("addMsg")
    public Result addMsg(long userId,String context){
        Message message = new Message();
        message.setSenderId(userId);
        message.setContext(context);
        message.setSendTime(LocalDateTime.now());
        messageMapper.insert(message);
        return Result.ok();
    }

    @RequestMapping("msgList")
    public Result msgList(){
        List<Message> allMsg = messageMapper.selectList(new QueryWrapper<Message>());
        return Result.okData(allMsg);
    }

    @RequestMapping("delMsgById")
    public Result delMsgById(String msgId){
        if(msgId.contains(",")){
            List<String> msgIds = Arrays.asList(msgId.split(","));
            for (String id : msgIds) {
                messageMapper.deleteById(Long.parseLong(id));
            }
        }else {
            messageMapper.deleteById(msgId);
        }
        return Result.ok();
    }

    @RequestMapping("respMsg")
    public Result respMsg(String respContext,long msgId){
        Message message = messageMapper.selectById(msgId);
        if(Objects.isNull(message)){
            return Result.fail("信息不存在");
        }
        message.setRespTime(LocalDateTime.now());
        message.setRespContext(respContext);
        messageMapper.updateById(message);
        return Result.ok();
    }

    @RequestMapping("viewMsgById")
    public Result viewMsgById(long msgId){
        Message message = messageMapper.selectById(msgId);
        if(message == null){
            return Result.fail("信息不存在");
        }
        Long senderId = message.getSenderId();
        User sender = userMapper.selectById(senderId);
        MessageVO vo = new MessageVO();
        vo.setMessage(message);
        vo.setSenderInfo(sender);
        return Result.okData(vo);
    }
}

