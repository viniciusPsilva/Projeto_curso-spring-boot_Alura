package br.com.alura.forum.model.DTO;

public class TokenDTO {

    private String token;
    private String authenticationType;

    public TokenDTO(String token, String authenticationType) {
        this.token = token;
        this.authenticationType = authenticationType;
    }

    public String getToken() {
        return token;
    }

    public String getAuthenticationType() {
        return authenticationType;
    }

}
