package com.recipebook.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

public class DateTimeUtil {

    private static final String TIMEZONE_HEADER_NAME = "Timezone-Val";

    private DateTimeUtil() {
    }

    public static OffsetDateTime now() {
        var zone = ZoneId.systemDefault();
        if (Objects.nonNull(RequestContextHolder.getRequestAttributes())) {
            zone = zoneinfo()
                    .map(ZoneId::of)
                    .orElse(ZoneId.systemDefault());
        }
        return OffsetDateTime.ofInstant(Instant.now(), zone);
    }

    public static Optional<String> zoneinfo() {
        if (Objects.nonNull(RequestContextHolder.getRequestAttributes())) {
            var request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                    .getRequest();
            return Optional.ofNullable(request.getHeader(TIMEZONE_HEADER_NAME));
        }
        return Optional.empty();
    }

}
