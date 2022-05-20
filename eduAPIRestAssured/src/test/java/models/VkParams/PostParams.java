package models.VkParams;

import lombok.Data;

@Data
public class PostParams {
    private String message;
    private String attachment;
    private int post_id;
}
