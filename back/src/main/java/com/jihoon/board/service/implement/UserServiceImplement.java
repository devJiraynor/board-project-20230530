package com.jihoon.board.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jihoon.board.dto.request.user.PatchUserNicknameRequestDto;
import com.jihoon.board.dto.request.user.PatchUserProfileRequestDto;
import com.jihoon.board.dto.response.user.PatchUserNicknameResponseDto;
import com.jihoon.board.dto.response.user.PatchUserProfileResponseDto;
import com.jihoon.board.service.UserService;

@Service
public class UserServiceImplement implements UserService {

  @Override
  public ResponseEntity<?> getUser(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUser'");
  }

  @Override
  public ResponseEntity<?> getSignInUser() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getSignInUser'");
  }

  @Override
  public ResponseEntity<? super PatchUserNicknameResponseDto> patchUserNickname(String email,
      PatchUserNicknameRequestDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'patchUserNickname'");
  }

  @Override
  public ResponseEntity<? super PatchUserProfileResponseDto> patchUserProfile(String email,
      PatchUserProfileRequestDto dto) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'patchUserProfile'");
  }
  
}
