package com.deltags.service.impl;

import com.deltags.entity.UserScheme;
import com.deltags.entity.WeaponSlot;
import com.deltags.mapper.WeaponSlotMapper;
import com.deltags.service.GunsmithCodecService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 改枪码编解码服务实现
 * 改枪码格式：Base64(weapon_id:slot1_id:slot2_id:...:config_hash)
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GunsmithCodecServiceImpl implements GunsmithCodecService {

    private final WeaponSlotMapper weaponSlotMapper;
    private final ObjectMapper objectMapper;

    private static final String SEPARATOR = ":";
    private static final String VERSION_PREFIX = "v1";

    @Override
    public String encode(UserScheme scheme) {
        try {
            StringBuilder sb = new StringBuilder(VERSION_PREFIX);
            sb.append(SEPARATOR);
            sb.append(scheme.getWeaponId());

            JsonNode configNode = objectMapper.readTree(scheme.getConfigJson());
            if (configNode.has("slots") && configNode.get("slots").isArray()) {
                for (JsonNode slot : configNode.get("slots")) {
                    if (slot.has("attachmentId")) {
                        sb.append(SEPARATOR);
                        sb.append(slot.get("attachmentId").asLong());
                    }
                }
            }

            String encoded = sb.toString();
            return Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(encoded.getBytes(StandardCharsets.UTF_8));
        } catch (JsonProcessingException e) {
            log.error("改枪码编码失败", e);
            throw new RuntimeException("改枪码编码失败", e);
        }
    }

    @Override
    public UserScheme decode(String gunsmithCode) {
        try {
            String decoded = new String(
                    Base64.getUrlDecoder().decode(gunsmithCode),
                    StandardCharsets.UTF_8
            );

            String[] parts = decoded.split(SEPARATOR);
            if (parts.length < 2) {
                throw new IllegalArgumentException("无效的改枪码格式");
            }

            String version = parts[0];
            if (!VERSION_PREFIX.equals(version)) {
                throw new IllegalArgumentException("不支持的改枪码版本: " + version);
            }

            Long weaponId = Long.parseLong(parts[1]);

            UserScheme scheme = new UserScheme();
            scheme.setWeaponId(weaponId);

            Map<String, Object> config = new HashMap<>();
            config.put("weaponId", weaponId);

            if (parts.length > 2) {
                var slots = new java.util.ArrayList<Map<String, Object>>();
                for (int i = 2; i < parts.length; i++) {
                    Long attachmentId = Long.parseLong(parts[i]);
                    Map<String, Object> slotMap = new HashMap<>();
                    slotMap.put("attachmentId", attachmentId);
                    slots.add(slotMap);
                }
                config.put("slots", slots);
            }

            scheme.setConfigJson(objectMapper.writeValueAsString(config));
            return scheme;
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            log.error("改枪码解码失败", e);
            throw new RuntimeException("改枪码解码失败", e);
        }
    }
}
