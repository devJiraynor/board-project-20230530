package com.jihoon.board.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jihoon.board.dto.request.board.PatchBoardRequestDto;
import com.jihoon.board.dto.request.board.PostBoardRequestDto;
import com.jihoon.board.dto.request.board.PostCommentRequestDto;
import com.jihoon.board.dto.request.board.PutFavoritRequestDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.board.BoardListResponseDto;
import com.jihoon.board.dto.response.board.DeleteBoardResponseDto;
import com.jihoon.board.dto.response.board.GetCurrentBoardResponseDto;
import com.jihoon.board.dto.response.board.GetSearchBoardResponseDto;
import com.jihoon.board.dto.response.board.GetTop3ResponseDto;
import com.jihoon.board.dto.response.board.PatchBoardResponseDto;
import com.jihoon.board.dto.response.board.PostBoardResponseDto;
import com.jihoon.board.dto.response.board.PostCommentResponseDto;
import com.jihoon.board.dto.response.board.PutFavoriteResponseDto;
import com.jihoon.board.entity.BoardEntity;
import com.jihoon.board.entity.BoardViewEntity;
import com.jihoon.board.entity.CommentEntity;
import com.jihoon.board.entity.FavoriteEntity;
import com.jihoon.board.entity.resultSet.BoardListResultSet;
import com.jihoon.board.repository.BoardRepository;
import com.jihoon.board.repository.BoardViewRepository;
import com.jihoon.board.repository.CommentRepository;
import com.jihoon.board.repository.FavoriteRepository;
import com.jihoon.board.repository.UserRepository;
import com.jihoon.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

  private final UserRepository userRepository;
  private final BoardRepository boardRepository;
  private final CommentRepository commentRepository;
  private final FavoriteRepository favoriteRepository;
  private final BoardViewRepository boardViewRepository;

  @Override
  public ResponseEntity<? super GetTop3ResponseDto> getTop3() {
    
    List<BoardListResponseDto> top3 = null;

    try {
      // description: 좋아요 순으로 상위 3개 게시물 조회 //
      List<BoardViewEntity> boardViewEntities = boardViewRepository.findTop3ByOrderByFavoriteCountDesc();

      // description: entity를 dto 형태로 변환 //
      top3 = BoardListResponseDto.copyEntityList(boardViewEntities);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetTop3ResponseDto.success(top3);

  }

  @Override
  public ResponseEntity<? super GetCurrentBoardResponseDto> getCurrentBoard() {
    
    List<BoardListResponseDto> boardList = null;

    try {

      // description: 최신 게시물 리스트 불러오기 //
      List<BoardListResultSet> resultSets = boardRepository.getCurrentList();

      // description: 검색 결과를 ResponseDto 형태로 변환 //
      boardList = BoardListResponseDto.copyList(resultSets);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetCurrentBoardResponseDto.success(boardList);

  }

  @Override
  public ResponseEntity<?> getBoard(Integer boardNumber) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getBoard'");
  }

  @Override
  public ResponseEntity<? super GetSearchBoardResponseDto> getSearchBoard(String searchWord) {
    
    List<BoardListResponseDto> boardList = null;

    try {

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return GetSearchBoardResponseDto.success(boardList);

  }

  @Override
  public ResponseEntity<?> getFavoritList(Integer boardNumber) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getFavoritList'");
  }

  @Override
  public ResponseEntity<?> getCommentList(Integer boardNumber) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCommentList'");
  }

  @Override
  public ResponseEntity<?> getUserList(String email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getUserList'");
  }

  @Override
  public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto) {
    
    String writerEmail = dto.getWriterEmail();

    try {
      // description: 작성자 이메일이 존재하는 이메일인지 확인 //
      boolean hasUser = userRepository.existsByEmail(writerEmail);
      if (!hasUser) return PostBoardResponseDto.noExistedUser();

      // description: entity 생성 //
      BoardEntity boardEntity = new BoardEntity(dto);

      // description: 데이터베이스에 저장 //
      boardRepository.save(boardEntity);

    } catch(Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PostBoardResponseDto.success();

  }

  @Override
  public ResponseEntity<? super PostCommentResponseDto> postComment(Integer boardNumber, PostCommentRequestDto dto) {
    
    String userEmail = dto.getUserEmail();

    try {
      // description: boardNumber가 null 인지 확인 //
      // todo: (추후 controller로 이동) //
      if (boardNumber == null) return PostCommentResponseDto.noExistedBoard();

      // description: 존재하는 회원인지 확인 //
      boolean hasUser = userRepository.existsByEmail(userEmail);
      if (!hasUser) return PostCommentResponseDto.noExistedUser();

      // description: 존재하는 게시물인지 확인 //
      boolean hasBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!hasBoard) return PostCommentResponseDto.noExistedBoard();

      // description: entity 생성 //
      CommentEntity commentEntity = new CommentEntity(boardNumber, dto);

      // description: 데이터베이스 저장 //
      commentRepository.save(commentEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PostCommentResponseDto.success();

  }

  @Override
  public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(Integer boardNumber, PutFavoritRequestDto dto) {
    
    String userEmail = dto.getUserEmail();

    try {

      // description: boardNumber가 null 인지 확인 //
      // todo: (추후 controller로 이동) //
      if (boardNumber == null) return PutFavoriteResponseDto.noExistedBoard();

      // description: 존재하는 회원인지 확인 //
      boolean hasUser = userRepository.existsByEmail(userEmail);
      if (!hasUser) return PutFavoriteResponseDto.noExistedUser();

      // description: 존재하는 게시물인지 확인 //
      boolean hasBoard = boardRepository.existsByBoardNumber(boardNumber);
      if (!hasBoard) return PutFavoriteResponseDto.noExistedBoard();

      // description: 해당 유저가 해당 게시물에 좋아요 했는지 확인 //
      boolean isFovorite = favoriteRepository.existsByUserEmailAndBoardNumber(userEmail, boardNumber);
      
      // description: entity 생성 //
      FavoriteEntity favoriteEntity = new FavoriteEntity(boardNumber, userEmail);
        
      // description: 이미 좋아요 했을 때 //
      if (isFovorite) favoriteRepository.delete(favoriteEntity);
      // description: 아직 좋아요 하지 않았을 때 //
      else favoriteRepository.save(favoriteEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PutFavoriteResponseDto.success();

  }

  @Override
  public ResponseEntity<? super PatchBoardResponseDto> patchBoard(Integer boardNumber, PatchBoardRequestDto dto) {
    
    String userEmail = dto.getUserEmail();

    try {
      // todo: 추후 controller로 이동 //
      if (boardNumber == null) return PatchBoardResponseDto.noExistedBoard();

      // description: 존재하는 유저인지 확인 //
      boolean hasUser = userRepository.existsByEmail(userEmail);
      if (!hasUser) return PatchBoardResponseDto.noExistedUser();

      // description: 존재하는 게시물인지 확인 //
      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null) return PatchBoardResponseDto.noExistedBoard();

      // description: 작성자 이메일과 입력받은 이메일이 같은지 확인 //
      boolean equalWriter = boardEntity.getWriterEmail().equals(userEmail);
      if (!equalWriter) return PatchBoardResponseDto.noPermission();

      // description: 수정 //
      boardEntity.patch(dto);
      // description: 데이터베이스에 저장 //
      boardRepository.save(boardEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return PatchBoardResponseDto.success();

  }

  @Override
  public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardNumber, String email) {
    
    try {

      // todo: 추후 controller로 이동 //
      if (boardNumber == null) return DeleteBoardResponseDto.noExistedBoard();

      // todo: 추후 controller로 이동 //
      if (email == null) return DeleteBoardResponseDto.noExistedUser();

      // description: 존재하는 유저인지 확인 //
      boolean hasUser = userRepository.existsByEmail(email);
      if (!hasUser) return DeleteBoardResponseDto.noExistedUser();

      // description: 존재하는 게시물인지 확인 //
      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null) return DeleteBoardResponseDto.noExistedBoard();

      // description: 게시물 작성자 이메일과 입력받은 이메일이 같은지 확인 //
      boolean equalWriter = boardEntity.getWriterEmail().equals(email);
      if (!equalWriter) return DeleteBoardResponseDto.noPermission();

      // description: 댓글 데이터 삭제 //
      commentRepository.deleteByBoardNumber(boardNumber);

      // description: 좋아요 데이터 삭제 //
      favoriteRepository.deleteByBoardNumber(boardNumber);

      // description: 게시물 삭제 //
      boardRepository.delete(boardEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.databaseError();
    }

    return DeleteBoardResponseDto.success();

  }
  
}
