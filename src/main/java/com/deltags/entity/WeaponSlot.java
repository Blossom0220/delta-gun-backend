package com.deltags.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("weapon_slot")
public class WeaponSlot {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long weaponId;
    private String gameSlotId;
    private String slotType;
    private String slotName;
    private Integer sortOrder;
}
