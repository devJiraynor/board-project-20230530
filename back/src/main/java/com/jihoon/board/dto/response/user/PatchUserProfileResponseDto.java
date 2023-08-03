package com.jihoon.board.dto.response.user;

import com.jihoon.board.common.response.ResponseCode;
import com.jihoon.board.common.response.ResponseMessage;
import com.jihoon.board.dto.response.ResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchUserProfileResponseDto extends ResponseDto {
  
  private PatchUserProfileResponseDto(String code, String message) {
    super(code, message);
  }

  public static PatchUserProfileResponseDto success() {
    PatchUserProfileResponseDto result = new PatchUserProfileResponseDto(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    return result;
  }

  public static ResponseDto noExistedUser() {
    ResponseDto result = new ResponseDto(ResponseCode.NO_EXISTED_USER, ResponseMessage.NO_EXISTED_USER);
    return result;
  }

}