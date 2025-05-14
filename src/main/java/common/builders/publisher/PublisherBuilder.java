package common.builders.publisher;

import api.models.publisher.PublisherRequest;
import utils.RandomUtils;

public class PublisherBuilder {

    public static PublisherRequest generatePublisherRequest() {
        return PublisherRequest.builder()
                .publisherName(RandomUtils.generateAlphabeticalString(10))
                .publisherEmail(RandomUtils.generateEmail())
                .build();
    }
}
