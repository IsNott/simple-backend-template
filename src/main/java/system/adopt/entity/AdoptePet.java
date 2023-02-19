package system.adopt.entity;

import lombok.Data;


// 领养宠物信息
@Data
public class AdoptePet {
    private Long catalogId; // 分类id
    private String name; // 宠物姓名
    private String area; // 地区
    private String gender; // 性别
}
