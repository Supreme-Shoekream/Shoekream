package com.supreme.shoekream.model.config;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getModifiedAt();

    void setCreatedAt(LocalDateTime createdAt);
    void setModifiedAt(LocalDateTime modifiedAt);
}
