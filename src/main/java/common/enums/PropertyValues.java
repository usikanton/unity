package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PropertyValues {

    LOGIN("loginValue"),
    PASSWORD("passwordValue");

    @Getter
    private final String propertyValue;
}
