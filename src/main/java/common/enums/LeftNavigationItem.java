package common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum LeftNavigationItem {

    PUBLISHER("Publisher"),
    PROFILE("Profile"),
    POST("Post");

    @Getter
    private final String value;
}
