package api.models.publisher;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PublisherParams {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("email")
    private String email;
    @JsonProperty("name")
    private String name;
}
