package com.deltags.service;

import com.deltags.entity.UserScheme;

public interface GunsmithCodecService {
    String encode(UserScheme scheme);
    UserScheme decode(String gunsmithCode);
}
