package com.deltags.controller;

import com.deltags.common.Result;
import com.deltags.entity.Attachment;
import com.deltags.mapper.AttachmentMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentMapper attachmentMapper;

    @GetMapping("/api/attachments")
    public Result<List<Attachment>> listBySlot(@RequestParam String slotType) {
        return Result.ok(attachmentMapper.selectList(
            new LambdaQueryWrapper<Attachment>()
                .eq(Attachment::getSlotType, slotType)
                .eq(Attachment::getIsActive, 1)
        ));
    }
}
