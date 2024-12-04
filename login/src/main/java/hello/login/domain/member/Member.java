package hello.login.domain.member;

import lombok.Data;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Data
public class Member {


    private Long id;
    @NotEmpty
    private String loginId; // 로그인 ID
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    @NotEmpty
    private String password2;

    @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
    public boolean isPasswordEqual() {
        return password !=null && password.equals(password2);
    }

}
