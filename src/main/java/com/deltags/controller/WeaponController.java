package com.deltags.controller;

import com.deltags.common.Result;
import com.deltags.entity.Weapon;
import com.deltags.entity.WeaponSlot;
import com.deltags.service.WeaponService;
import com.deltags.mapper.WeaponSlotMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WeaponController {

    private final WeaponService weaponService;
    private final WeaponSlotMapper weaponSlotMapper;

    @GetMapping("/api/weapons")
    public Result<List<Weapon>> listWeapons() {
        return Result.ok(weaponService.listWeapons());
    }

    @GetMapping("/api/weapons/{id}")
    public Result<Weapon> getWeapon(@PathVariable Long id) {
        return Result.ok(weaponService.getWeaponById(id));
    }

    @GetMapping("/api/weapons/{id}/slots")
    public Result<List<WeaponSlot>> getWeaponSlots(@PathVariable Long id) {
        return Result.ok(weaponSlotMapper.selectList(
            new LambdaQueryWrapper<WeaponSlot>()
                .eq(WeaponSlot::getWeaponId, id)
                .orderByAsc(WeaponSlot::getSortOrder)
        ));
    }
}
