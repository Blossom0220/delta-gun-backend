package com.deltags.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("weapon")
public class Weapon {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String gameId;
    private String category;
    private String caliber;
    private String imageUrl;
    private String description;
    private Integer damage;
    private Integer rangeStat;
    private Integer recoilControl;
    private Integer handling;
    private Integer fireRate;
    private Integer magazineCapacity;
    private Integer muzzleVelocity;
    private Integer sortOrder;
    private Integer isActive;
}
