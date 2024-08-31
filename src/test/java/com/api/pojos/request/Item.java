package com.api.pojos.request;

import com.api.pojos.common.ItemData;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @JsonProperty("name")
    private String name;
    @JsonProperty("data")
    private ItemData data;
}
