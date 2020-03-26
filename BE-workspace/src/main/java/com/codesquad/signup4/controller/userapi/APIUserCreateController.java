package com.codesquad.signup4.controller.userapi;

import com.codesquad.signup4.domain.User;
import com.codesquad.signup4.domain.UserRepository;
import com.codesquad.signup4.dto.Result;
import com.codesquad.signup4.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class APIUserCreateController {

    private static final Logger log = LoggerFactory.getLogger(APIUserCreateController.class);

    @Autowired
    UserRepository userRepository;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @PostMapping("/create")
    public Result create(User newUser) {

        if (!newUser.isValid()) {
            throw new BadRequestException("사용자 가입할 수 없습니다.");
        }
        userRepository.save(newUser);
        return Result.ok();
    }

    @GetMapping("/duplicate/checkID")
    public Result checkId(@RequestParam String id) {
        checkNull(id);

        if (userRepository.findByUserID(id) != null) {
            return Result.fail("중복된 아이디입니다.");
        }

        return Result.ok();
    }

    @GetMapping("/duplicate/checkEmail")
    public Result checkEmail(@RequestParam String email) {
        checkNull(email);

        if (userRepository.findByEmail(email) != null) {
            return Result.fail("중복된 이메일입니다.");
        }
        return Result.ok();
    }

    @GetMapping("/duplicate/checkMobile")
    public Result checkMobile(@RequestParam String mobileNumber) {
        checkNull(mobileNumber);

        if (userRepository.findByMobile(mobileNumber) != null) {
            return Result.fail("중복된 번호입니다.");
        }
        return Result.ok();
    }

    public void checkNull(String auditee) {
        if (auditee == null || auditee.equals("")) {
            throw new BadRequestException("올바른 값을 입력해주세요.");
        }
    }
}
