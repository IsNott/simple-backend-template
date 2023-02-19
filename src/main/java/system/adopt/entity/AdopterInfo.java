package system.adopt.entity;

import lombok.Data;

// 领养人信息
@Data
public class AdopterInfo {
    private Long id; // 账号id
    private String phone; // 手机号
    private String area; // 地区
    private String name; // 姓名
}
