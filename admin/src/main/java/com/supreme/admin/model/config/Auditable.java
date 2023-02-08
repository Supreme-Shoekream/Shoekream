package com.supreme.admin.model.config;

import java.time.LocalDateTime;

public interface Auditable {

    void setCreatedAt(LocalDateTime createdAt);
    void setModifiedAt(LocalDateTime modifiedAt);
}
