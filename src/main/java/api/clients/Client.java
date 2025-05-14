package api.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import common.enums.URLs;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import utils.PropertyUtils;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.basic;
import static io.restassured.config.EncoderConfig.encoderConfig;

public abstract class Client {

    protected static ThreadLocal<RequestSpecification> requestSpecification = new ThreadLocal<>();
    protected static final String BASE_URI = URLs.SERVICE.getUrl();

    protected static RequestSpecification getRequestSpecification() {
        if (Objects.isNull(requestSpecification.get())) {
            configureClient();
        }
        return requestSpecification.get();
    }

    private static void configureClient() {
        requestSpecification.set(generateDefaultBuilder()
                .setBaseUri(BASE_URI)
                .build()
                .given());
    }

    private static RestAssuredConfig generateDefaultConfig() {
        HttpClientConfig httpClientConfig = HttpClientConfig.httpClientConfig()
                .setParam("http.connection.timeout", 300000)
                .setParam("http.connection.socket", 300000);
        ObjectMapperConfig objectMapperConfig = ObjectMapperConfig.objectMapperConfig()
                .jackson2ObjectMapperFactory((_class, s) -> JsonMapper.builder()
                        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                        .build());
        return RestAssuredConfig.config()
                .httpClient(httpClientConfig)
                .objectMapperConfig(objectMapperConfig)
                .encoderConfig(encoderConfig().encodeContentTypeAs("multipart/form-data", ContentType.TEXT));
    }

    protected static RequestSpecBuilder generateDefaultBuilder() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setConfig(generateDefaultConfig())
                .setContentType(ContentType.MULTIPART)
                .setAccept(ContentType.ANY)
                .setAuth(basic(PropertyUtils.getLogin(), PropertyUtils.getPassword()))
                .addFilter(new AllureRestAssured());
        return requestSpecBuilder;
    }

    protected static String logMessage(String baseUri, Method method, int statusCode) {
        return String.format("%s. %s request to %s failed due to incorrect status code", statusCode, method.name(), baseUri);
    }

    protected static <T> Map<String, String> getFormParams(T request) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(request, new TypeReference<>() {
        });
    }
}
