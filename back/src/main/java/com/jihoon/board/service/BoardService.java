package com.jihoon.board.service;

import org.springframework.http.ResponseEntity;

import com.jihoon.board.dto.request.board.PatchBoardRequestDto;
import com.jihoon.board.dto.request.board.PostBoardRequestDto;
import com.jihoon.board.dto.request.board.PostCommentRequestDto;
import com.jihoon.board.dto.request.board.PutFavoritRequestDto;
import com.jihoon.board.dto.response.board.DeleteBoardResponseDto;
import com.jihoon.board.dto.response.board.GetCurrentBoardResponseDto;
import com.jihoon.board.dto.response.board.PatchBoardResponseDto;
import com.jihoon.board.dto.response.board.PostBoardResponseDto;
import com.jihoon.board.dto.response.board.PostCommentResponseDto;
import com.jihoon.board.dto.response.board.PutFavoriteResponseDto;

public interface BoardService {
  
  // method : Top3 게시물 불러오기 메서드 //
  ResponseEntity<?> getTop3();
  // method : 최신 게시물 리스트 불러오기 메서드 //
  ResponseEntity<? super GetCurrentBoardResponseDto> getCurrentBoard();
  // method : 게시물 불러오기 메서드 //
  ResponseEntity<?> getBoard(Integer boardNumber);
  // method : 검색 게시물 리스트 불러오기 //
  ResponseEntity<?> getSearchBoardList(String searchWord);
  // method : 특정 게시물의 좋아요 리스트 불러오기 //
  ResponseEntity<?> getFavoritList(Integer boardNumber);
  // method : 특정 게시물의 댓글 리스트 불러오기 //
  ResponseEntity<?> getCommentList(Integer boardNumber);
  // method : 특정 유저의 게시물 리스트 불러오기 //
  ResponseEntity<?> getUserList(String email);
  // method : 게시물 작성 메서드 //
  ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto);
  // method : 댓글 작성 메서드 //
  ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardNumber, PostCommentRequestDto dto);
  // mehtod : 좋아요 기능 메서드 //
  ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, PutFavoritRequestDto dto);
  // method : 게시물 수정 메서드 //
  ResponseEntity<? super PatchBoardResponseDto> patchBoard(Integer boardNumber, PatchBoardRequestDto dto);
  // method : 게시물 삭제 메서드 //
  ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email);

}
