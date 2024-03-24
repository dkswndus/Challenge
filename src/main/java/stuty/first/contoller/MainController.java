package stuty.first.contoller;


import org.apache.catalina.User;
import org.apache.coyote.http11.HttpOutputBuffer;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import stuty.first.dto.UserDTO;
import stuty.first.service.MainService;

import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor

public class
MainController {
    private  final MainService mainService;

    //회원가입
    @GetMapping("/user/register")
    public String register(){
        return "register";
    }

    @PostMapping("/user/register")
    public String register(@ModelAttribute UserDTO request){
        mainService.register(request);
        return "login";

    }

    // 로그인
    @GetMapping("/user/login")
    public String loginForm(){
        return "login";

    }
    @PostMapping("/user/login")
    public String login(@ModelAttribute UserDTO request, HttpSession session) {
        Optional<UserDTO> loginResult = mainService.login(request);
        if(loginResult.isPresent()){
            // 로그인 성공
            session.setAttribute("loginUserId", loginResult.get().getUserId());
            return "main";
        } else {
            // 로그인 실패
            return "login";
        }
    }


    // 회원 정보 보기
    @GetMapping("/user/view")
    public String viewUserInfo(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loginUserId");
        UserDTO userDTO = mainService.findByUserId(userId);
        model.addAttribute("user", userDTO);
        return "view";
    }


    //회원 정보 수정
    @GetMapping("/user/update")
    public String updateForm(HttpSession session, Model model){
        String userId = (String) session.getAttribute("loginUserId");
        UserDTO userDTO = mainService.updateForm(userId);
        model.addAttribute("updateUser", userDTO);
        return "update";
    }
    // 회원정보 수정  후 업데이트
    @PostMapping("/user/update")
    public String update(@ModelAttribute UserDTO userDTO){
        mainService.update(userDTO);

        return "main";
    }

    @PostMapping("/user/delete/{id}")
    public String delete(@PathVariable Long id) {
        mainService.deleteById(id);
        return "initial";
    }


}
