package api.controller.publisher;

import api.clients.Client;
import api.models.publisher.PublisherRequest;
import api.models.publisher.PublisherResponse;
import io.restassured.http.Method;
import org.apache.http.HttpStatus;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.is;

public class PublisherController extends Client {

    public static PublisherResponse publisherCreatePOST(PublisherRequest publisherRequest) {
        Map<String, String> formParams = getFormParams(publisherRequest);
        String basePath = "Publisher/actions/new";
        return given(getRequestSpecification())
                .basePath(basePath)
                .log().all()
                .formParams(formParams)
                .post()
                .then()
                .log().all()
                .statusCode(describedAs(logMessage(basePath, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(PublisherResponse.class);
    }

    public static PublisherResponse publisherByIdGET(int id) {
        String basePath = String.format("Publisher/records/%s/show", id);
        return given(getRequestSpecification())
                .basePath(basePath)
                .get()
                .then()
                .statusCode(describedAs(logMessage(basePath, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(PublisherResponse.class);
    }

    public static Object publishersAllGET() {
        String basePath = "Publisher/actions/list";
        return given(getRequestSpecification())
                .basePath(basePath)
                .get()
                .then()
                .statusCode(describedAs(logMessage(basePath, Method.POST, HttpStatus.SC_OK), is(HttpStatus.SC_OK)))
                .extract().as(Object.class);
    }
}
