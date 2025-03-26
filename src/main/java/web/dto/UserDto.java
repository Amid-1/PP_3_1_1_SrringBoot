package web.dto;

import javax.validation.constraints.*;

public class UserDto {
    private Long id;

    @NotBlank(message = "Имя не может быть пустым")
    private String username;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String lastName;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 6, message = "Пароль должен быть не менее 6 символов")
    private String password;

    public UserDto() {}

    public UserDto(String username, String lastName, String email, String password) {
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public UserDto(Long id, String username, String lastName, String email, String password) {
        this.id = id;
        this.username = username;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
