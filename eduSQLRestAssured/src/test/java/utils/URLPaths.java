package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum URLPaths {
    USERS("/users"),
    POSTS("/posts");

    @Getter
    private final String value;
}
