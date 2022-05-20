package models.VkParams;

import lombok.Data;

@Data
public class LikeParams {
    private String type;
    private int owner_id;
    private int item_id;
}
