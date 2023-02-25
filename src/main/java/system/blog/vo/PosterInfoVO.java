package system.blog.vo;

import lombok.Data;

@Data
public class PosterInfoVO {
    private Long id;
    private String context;
    private String title;
    private String coverPath;
}
