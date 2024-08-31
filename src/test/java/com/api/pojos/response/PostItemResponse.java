package com.api.pojos.response;

import com.api.pojos.common.ItemData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostItemResponse {
    private String id;
    private String name;
    private ItemData data;
    private String createdAt;
}
