package com.ssafy.ssafygo.model.service;

import com.ssafy.ssafygo.model.dto.Store;

public interface StoreService {
    // 가맹점 조회
    public Store findByUid(String uid);
}
