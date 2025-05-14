package ui;

import api.models.post.PostRequest;
import api.models.publisher.PublisherRequest;
import common.builders.post.PostBuilder;
import common.builders.publisher.PublisherBuilder;
import common.enums.LeftNavigationItem;
import common.enums.PostColumns;
import common.enums.PostStatus;
import common.enums.PublisherColumns;
import org.testng.annotations.Test;
import ui.pages.HomePage;
import ui.pages.LeftNavigationMenu;
import ui.pages.post.CreateNewPostPage;
import ui.pages.post.PostPage;
import ui.pages.publisher.CreateNewPublisherPage;
import ui.pages.publisher.PublisherPage;
import utils.PropertyUtils;

import static org.assertj.core.api.Assertions.assertThat;

public class PostsTests extends BaseTest {

    private HomePage homePage;
    private PublisherPage publisherPage;
    private PostPage postPage;
    private LeftNavigationMenu leftNavigationMenu;
    private CreateNewPublisherPage createNewPublisherPage;
    private CreateNewPostPage createNewPostPage;

    @Test
    public void editPostStatusTest() {
        String login = PropertyUtils.getLogin();
        String password = PropertyUtils.getPassword();

        homePage = loginPage.logIn(login, password);
        leftNavigationMenu = homePage.openNavigationMenu();
        publisherPage = leftNavigationMenu.openPageFromNavigationMenu(LeftNavigationItem.PUBLISHER);
        createNewPublisherPage = publisherPage.openCreatePublisherForm();

        PublisherRequest publisherRequest = PublisherBuilder.generatePublisherRequest();
        publisherPage = createNewPublisherPage.createNewPublisher(publisherRequest);

        String createdPublisherName = publisherPage.getLatestPublisherCellValue(PublisherColumns.NAME);
        String createdPublisherEmail = publisherPage.getLatestPublisherCellValue(PublisherColumns.EMAIL);
        assertThat(createdPublisherName).as("Verify created publisher name").isEqualTo(publisherRequest.getPublisherName());
        assertThat(createdPublisherEmail).as("Verify created publisher email").isEqualTo(publisherRequest.getPublisherEmail());

        postPage = leftNavigationMenu.openPageFromNavigationMenu(LeftNavigationItem.POST);
        createNewPostPage = postPage.clickCreateNewPost();
        PostRequest postRequest = PostBuilder.generatePostRequest(PostStatus.ACTIVE);
        postPage = createNewPostPage.createPost(postRequest, createdPublisherEmail);

        String createdPostTitle = postPage.getLatestPostCellValue(PostColumns.TITLE);
        String createdPostContent = postPage.getLatestPostCellValue(PostColumns.CONTENT);
        String createdPostStatus = postPage.getLatestPostCellValue(PostColumns.STATUS);
        assertThat(createdPostTitle).as("Verify created post title").isEqualTo(postRequest.getTitle());
        assertThat(createdPostContent).as("Verify crated post content").isEqualTo(postRequest.getContent());
        assertThat(createdPostStatus).as("Verify created post status").isEqualTo(postRequest.getStatus());

        createNewPostPage = postPage.editRecordByTitle(postRequest);
        createNewPostPage.selectPostStatus(PostStatus.REMOVED.getValue());
        createNewPostPage.savePost();
        createdPostStatus = postPage.getLatestPostCellValue(PostColumns.STATUS);
        assertThat(createdPostStatus).as("Verify created post status").isEqualTo(PostStatus.REMOVED.getValue());
    }
}
