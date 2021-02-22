package demo.spring.models.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BaseResponse implements Serializable {
    private String message;

    public BaseResponse(String message) {
        this.message = message;
    }
}
