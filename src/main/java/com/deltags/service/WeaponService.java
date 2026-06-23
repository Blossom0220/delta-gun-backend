package com.deltags.service;

import com.deltags.entity.Weapon;
import java.util.List;

public interface WeaponService {
    List<Weapon> listWeapons();
    Weapon getWeaponById(Long id);
}
