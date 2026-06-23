package com.deltags.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("ammo")
public class Ammo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String caliber;
    private String gameId;
    private String description;
    private String statsEffect;
    private Integer sortOrder;
    private Integer isActive;
}
