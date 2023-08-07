package com.jihoon.board.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jihoon.board.dto.request.auth.SignInRequestDto;
import com.jihoon.board.dto.request.auth.SignUpRequestDto;
import com.jihoon.board.dto.response.auth.SignInResponseDto;
import com.jihoon.board.dto.response.auth.SignUpResponseDto;
import com.jihoon.board.repository.UserRepository;
import com.jihoon.board.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

  private final UserRepository userRepository;

  @Override
  public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'signIn'");
  }

  @Override
  // method: 회원가입 메서드 //
  public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
    SignUpResponseDto responseBody = null;

    String email = dto.getEmail();
    String nickname = dto.getNickname();
    String telNumber = dto.getTelNumber();

    // description: 이메일 중복 확인 //
    boolean hasEmail = userRepository.existsById(email);
    if (hasEmail) return SignUpResponseDto.existedEmail();

    // todo: 닉네임 중복 확인 //
    boolean hasNickname = userRepository.existsByNickname(nickname);
    if (hasNickname) return SignUpResponseDto.existedNickname();

    // todo: 전화번호 중복 확인 //

    // todo: Entity 생성 //

    // todo: 데이터베이스에 저장 //

    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
  }
  
}
