package com.deltags.vo;

import lombok.Data;

@Data
public class WeaponVO {
    private Long id;
    private String name;
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
}
