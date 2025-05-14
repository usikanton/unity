package common.builders.post;

import api.models.post.PostRequest;
import api.models.post.SomeJsonItem;
import common.enums.PostStatus;
import utils.RandomUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PostBuilder {

    private static final String DATE_PATTERN = "yyyy/MM/dd HH:mm";

    public static PostRequest generateDummyPostRequest() {
        return PostRequest.builder()
                .content(RandomUtils.generateAlphanumericString(50))
                .title(RandomUtils.generateAlphanumericString(10))
                .status(PostStatus.ACTIVE.getValue())
                .published(true)
                .build();
    }

    public static PostRequest generatePostRequest(PostStatus status) {
        return PostRequest.builder()
                .content(RandomUtils.generateAlphanumericString(50))
                .title(RandomUtils.generateAlphanumericString(10))
                .someJson(List.of(generateSomeJson()))
                .status(status.getValue())
                .published(true)
                .build();
    }

    public static SomeJsonItem generateSomeJson() {
        return SomeJsonItem.builder()
                .number(Integer.valueOf(RandomUtils.generateNumericString(5)))
                .string(RandomUtils.generateAlphabeticalString(5))
                .bool(RandomUtils.randomBoolean())
                .date(LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN)))
                .build();
    }
}
