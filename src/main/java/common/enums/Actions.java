package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Actions {

    SHOW("show"),
    EDIT("edit"),
    DELETE("delete");

    @Getter
    private final String action;
}
