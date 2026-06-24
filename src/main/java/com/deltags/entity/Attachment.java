package com.deltags.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("attachment")
public class Attachment {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String gameId;
    private String slotType;
    private String rarity;
    private String imageUrl;
    private String statsEffect;
    private Integer isActive;
}
