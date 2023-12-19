package com.example.eKart.response;

import com.example.eKart.dto.SignUpDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
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
