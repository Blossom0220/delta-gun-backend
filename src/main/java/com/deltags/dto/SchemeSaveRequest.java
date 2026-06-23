package com.deltags.dto;

import lombok.Data;

@Data
public class SchemeSaveRequest {
    private Long weaponId;
    private String schemeName;
    private String configJson;
}
