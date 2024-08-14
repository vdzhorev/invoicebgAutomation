package api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Credential {

    private String email;
    private String password;
    private String domain;

}
