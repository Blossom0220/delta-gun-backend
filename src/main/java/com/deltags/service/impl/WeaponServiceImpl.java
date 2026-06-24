package com.deltags.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.deltags.entity.Weapon;
import com.deltags.mapper.WeaponMapper;
import com.deltags.service.WeaponService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeaponServiceImpl implements WeaponService {

    private final WeaponMapper weaponMapper;

    @Override
    public List<Weapon> listWeapons() {
        return weaponMapper.selectList(
            new LambdaQueryWrapper<Weapon>()
                .eq(Weapon::getIsActive, 1)
                .orderByAsc(Weapon::getSortOrder)
        );
    }

    @Override
    public Weapon getWeaponById(Long id) {
        return weaponMapper.selectById(id);
    }
}
