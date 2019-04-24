package app.utils;

import java.util.Objects;

public interface StringUtils {

    static boolean isBlank(final String str) {
        return Objects.nonNull(str) && str.trim().length() > 0;
    }
}
