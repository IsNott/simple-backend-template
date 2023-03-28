package system.message.vo;

import system.message.entity.Message;
import system.user.entity.User;
import lombok.Data;

/**
 * @author
 * @Date 2023/2/18
 */


@Data
public class MessageVO {
    private Message message;
    private User senderInfo;
}
