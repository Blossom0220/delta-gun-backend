package com.deltags.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.deltags.dto.SchemeSaveRequest;
import com.deltags.entity.UserScheme;
import com.deltags.mapper.UserSchemeMapper;
import com.deltags.service.SchemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchemeServiceImpl implements SchemeService {

    private final UserSchemeMapper schemeMapper;

    @Override
    public List<UserScheme> listMySchemes(Long userId) {
        return schemeMapper.selectList(
            new LambdaQueryWrapper<UserScheme>()
                .eq(UserScheme::getUserId, userId)
                .orderByDesc(UserScheme::getUpdatedAt)
        );
    }

    @Override
    public Long saveScheme(Long userId, SchemeSaveRequest request) {
        UserScheme entity = new UserScheme();
        entity.setUserId(userId);
        entity.setWeaponId(request.getWeaponId());
        entity.setSchemeName(request.getSchemeName());
        entity.setConfigJson(request.getConfigJson());
        entity.setIsPublic(0);
        entity.setLikeCount(0);
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        schemeMapper.insert(entity);
        return entity.getId();
    }
}
