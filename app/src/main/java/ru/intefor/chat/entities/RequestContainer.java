package ru.intefor.chat.entities;

import java.util.UUID;

import ru.intefor.chat.utils.DateUtil;

public class RequestContainer<T> {
    private String token;
    private String requestId;
    private long ts;
    private T payload;

    public RequestContainer() {
        requestId = UUID.randomUUID().toString();
        ts = DateUtil.now();
    }

    public RequestContainer(T payload) {
        requestId = UUID.randomUUID().toString();
        ts = DateUtil.now();
        this.payload = payload;
    }
}
