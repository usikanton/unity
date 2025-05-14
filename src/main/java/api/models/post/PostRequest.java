package api.models.post;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostRequest {

    private Integer id;
    private String title;
    private String content;
    private List<SomeJsonItem> someJson;
    private String status;
    private Boolean published;
    private Integer publisher;
}
