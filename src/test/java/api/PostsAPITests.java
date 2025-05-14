package api;

import api.controller.post.PostController;
import api.controller.publisher.PublisherController;
import api.models.post.PostRequest;
import api.models.post.PostSingleResponse;
import api.models.publisher.PublisherRequest;
import api.models.publisher.PublisherResponse;
import common.builders.post.PostBuilder;
import common.builders.publisher.PublisherBuilder;
import common.enums.PostStatus;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostsAPITests {

    @Test
    public void editPostStatusTest() {
        PublisherRequest publisherRequest = PublisherBuilder.generatePublisherRequest();
        PublisherResponse publisherResponse = PublisherController.publisherCreatePOST(publisherRequest);
        assertThat(publisherResponse.getRecord().getParams().getName()).as("Verify publisher name").isEqualTo(publisherRequest.getPublisherName());
        assertThat(publisherResponse.getRecord().getParams().getEmail()).as("Verify publisher email").isEqualTo(publisherRequest.getPublisherEmail());

        PostRequest postRequest = PostBuilder.generateDummyPostRequest();
        PostSingleResponse postSingleResponse = PostController.postCreatePOST(postRequest);
        assertThat(postSingleResponse.getRecord().getParams().getTitle()).as("Verify post title").isEqualTo(postRequest.getTitle());
        assertThat(postSingleResponse.getRecord().getParams().getContent()).as("Verify post content").isEqualTo(postRequest.getContent());
        assertThat(postSingleResponse.getRecord().getParams().getStatus()).as("Verify post status").isEqualTo(postRequest.getStatus());
        assertThat(postSingleResponse.getRecord().getParams().getPublished()).as("Verify post status").isEqualTo(postRequest.getPublished());
        assertThat(postSingleResponse.getRecord().getParams().getPublisher()).as("Verify post status").isEqualTo(publisherResponse.getRecord().getParams().getId());

        postRequest.setStatus(PostStatus.REMOVED.getValue());
        int postId = postSingleResponse.getRecord().getParams().getId();
        postRequest.setId(postId);
        postSingleResponse = PostController.postEditByIdPOST(postRequest, postId);
        assertThat(postSingleResponse.getRecord().getParams().getStatus()).as("Verify post status").isEqualTo(postRequest.getStatus());
    }
}
