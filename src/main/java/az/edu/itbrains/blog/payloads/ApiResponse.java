package az.edu.itbrains.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private HttpStatus httpStatus;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}