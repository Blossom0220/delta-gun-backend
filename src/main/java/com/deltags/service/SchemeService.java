package com.deltags.service;

import com.deltags.dto.SchemeSaveRequest;
import com.deltags.entity.UserScheme;
import java.util.List;

public interface SchemeService {
    List<UserScheme> listMySchemes(Long userId);
    Long saveScheme(Long userId, SchemeSaveRequest request);
}
