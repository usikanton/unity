package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PublisherColumns {

    NAME("name"),
    EMAIL("email"),
    ID("id");

    @Getter
    private final String columnName;
}
