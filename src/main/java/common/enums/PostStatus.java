package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PostStatus {

    ACTIVE("ACTIVE"),
    REMOVED("REMOVED");

    @Getter
    private final String value;
}
