package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum URLs {

    BASE("http://localhost:3000"),
    SERVICE("http://localhost:3000/admin/api/resources/");

    @Getter
    private final String url;
}
