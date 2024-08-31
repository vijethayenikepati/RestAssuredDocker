package com.api.pojos.response;

import com.api.pojos.common.ItemData;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetItemResponse {
    private String id;
    private String name;
    private ItemData data;
}
