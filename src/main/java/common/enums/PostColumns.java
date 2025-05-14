package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PostColumns {

    TITLE("title"),
    ID("id"),
    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt"),
    CONTENT("content"),
    SOME_JSON("someJson"),
    STATUS("status"),
    PUBLISHED("published");

    @Getter
    private final String propertyName;
    }
