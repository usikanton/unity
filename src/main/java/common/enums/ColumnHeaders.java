package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ColumnHeaders {

    ID("#"),
    NAME("Name"),
    TITLE("Title");
    //TODO: add all the rest headers

    @Getter
    private final String headerColumnName;
}
