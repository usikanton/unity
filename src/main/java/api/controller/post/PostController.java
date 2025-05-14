package api.controller.post;

import api.clients.Client;
import api.models.post.PostRequest;
import api.models.post.PostSingleResponse;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.is;

public class PostController extends Client {

    public static PostSingleResponse postCreatePOST(PostRequest postRequest) {
        Map<String, String> formParams = getFormParams(postRequest);
        String basePath = "Post/actions/new";
        return given(getRequestSpecification())
                .basePath(basePath)
                .formParams(formParams)
                .post()
                .then()
                .statusCode(describedAs(logMessage(BASE_URI, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(PostSingleResponse.class);
    }

    public static PostSingleResponse postByIdGET(int id) {
        String basePath = String.format("Post/records/%s/show", id);
        return given(getRequestSpecification())
                .basePath(basePath)
                .get()
                .then()
                .statusCode(describedAs(logMessage(BASE_URI, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(PostSingleResponse.class);
    }

    public static Object postsAllGET() {
        String basePath = "Post/actions/list";
        return given(getRequestSpecification())
                .basePath(basePath)
                .get()
                .then()
                .statusCode(describedAs(logMessage(BASE_URI, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(Object.class);
    }

    public static PostSingleResponse postEditByIdPOST(PostRequest postUpdateRequest, int id) {
        Map<String, String> formParams = getFormParams(postUpdateRequest);
        String basePath = String.format("Post/records/%s/edit", id);
        return given(getRequestSpecification())
                .basePath(basePath)
                .formParams(formParams)
                .post()
                .then()
                .statusCode(describedAs(logMessage(BASE_URI, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(PostSingleResponse.class);
    }
}
