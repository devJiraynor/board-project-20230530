package com.jihoon.board.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jihoon.board.dto.request.auth.SignInRequestDto;
import com.jihoon.board.dto.request.auth.SignUpRequestDto;
import com.jihoon.board.dto.response.auth.SignInResponseDto;
import com.jihoon.board.dto.response.auth.SignUpResponseDto;

// controller : 인증 컨트롤러 //
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  
  // API : 회원가입 메서드 //
  @PostMapping("/sign-up")
  public ResponseEntity<? super SignUpResponseDto> signUp(
    @RequestBody @Valid SignUpRequestDto requestBody
  ) {
    SignUpResponseDto response = SignUpResponseDto.success();
    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  // API : 로그인 메서드 //
  @PostMapping("/sign-in")
  public ResponseEntity<? super SignInResponseDto> signIn(
    @RequestBody @Valid SignInRequestDto requestBody
  ) {
    SignInResponseDto result = SignInResponseDto.success("aaaa");
    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(result);
  }

}
