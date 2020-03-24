package com.codesquad.signup4.controller;

import com.codesquad.signup4.domain.User;
import com.codesquad.signup4.domain.UserRepository;
import com.codesquad.signup4.dto.Result;
import com.codesquad.signup4.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class APIUserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/create")
    public Result create(User newUser) {
//        Todo 일단 이렇게 작성만 해놓음
        if(newUser.getUserId() == null) {
            throw new BadRequestException();
        }
        return Result.ok();
    }

    @GetMapping("/checkId")
    public Result checkId(@RequestParam String id) {
        if(id == null) {
            throw new BadRequestException();
        }

        if(id.equals("testUserId")) {
            return Result.fail("중복된 아이디입니다.");
        }

        return Result.ok();
    }

    @GetMapping("/checkMobile")
    public Result checkMobile(@RequestParam String mobileNumber) {
        if(mobileNumber == null) {
            throw new BadRequestException();
        }

        if(mobileNumber.equals("01012341234")) {
            return Result.fail("중복된 번호입니다.");
        }

        return Result.ok();
    }
}
