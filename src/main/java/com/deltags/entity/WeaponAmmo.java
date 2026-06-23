package com.deltags.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("weapon_ammo")
public class WeaponAmmo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long weaponId;
    private Long ammoId;
}
