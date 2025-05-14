package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum SortingDirection {

    ASC("asc"),
    DESC("desc");

    @Getter
    private final String sortingDirection;
}
