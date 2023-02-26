package system.blog.vo;

import lombok.Data;

/**
 * 发送博客人信息
 */

@Data
public class PosterInfoVO {
    private Long id; // 用户id
    private Long typeId; // 博客分类id
    private String context; // 内容
    private String title; // 标题
    private String coverPath; // 封面路径
}
