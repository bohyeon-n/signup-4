package com.codesquad.signup4.exception;

import com.codesquad.signup4.dto.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BadRequestException.class)
    public Result unauthorizedException() {
        System.out.println("-------------------------------");
        Result result = Result.fail("요청을 잘못 보내셨습니다. ");
        System.out.println(result);
        return result;
    }
}
