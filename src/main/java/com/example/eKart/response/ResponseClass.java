package com.example.eKart.response;

import com.example.eKart.dto.SignUpDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseClass {

    private HttpStatus code;
    private String message;

    private Object data;

    public ResponseClass(HttpStatus code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public ResponseClass(HttpStatus code, String message) {
        this.code=code;
        this.message=message;
    }
}
