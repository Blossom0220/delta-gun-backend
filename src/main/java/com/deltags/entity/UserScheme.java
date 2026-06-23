package com.deltags.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@TableName("user_scheme")
public class UserScheme {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Long weaponId;
    private String schemeName;
    private String configJson;
    private String gunsmithCode;
    private Integer isPublic;
    private Integer likeCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
