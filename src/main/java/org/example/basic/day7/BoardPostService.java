package org.example.basic.day7;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardPostService {
    // DB 대신
    List<BoardPost> boardPosts = new ArrayList<>();
    private Long nextPostId = 1L;
    private Long nextCommentId = 1L;

    public BoardPostDto createBoardPost(BoardPostDto boardPostDto) {
        BoardPost boardPost = convertToBoardPostEntity(boardPostDto);
    }

    private static BoardPost convertToBoardPostEntity(BoardPostDto boardPostDto) {
        BoardPost boardPost = new BoardPost();
        boardPost.setTitle(boardPostDto.getTitle());
        boardPost.setContent(boardPostDto.getContent());
        boardPost.setAuthor(boardPostDto.getAuthor());

        if(boardPost.getComments() != null) {
            boardPostDto.getComments().forEach(commentDto -> {
                Comment comment = convertToCommentEntity(commentDto);
                comment.setBoardPost(boardPost);
                boardPost.addComment(comment);
            });
        }

        return boardPost;
    }
    private static BoardPostDto convertToBoardPostDto(BoardPost boardPost) {
        BoardPostDto boardPostDto = new BoardPostDto();
        boardPostDto.setId(boardPost.getId());
        boardPostDto.setTitle(boardPost.getTitle());
        boardPostDto.setAuthor(boardPost.getAuthor());
        boardPostDto.setCreateAt(boardPost.getCreateAt());
        boardPostDto.setUpdateAt(boardPost.getUpdateAt());

        if(boardPost.getComments() != null) {
            boardPostDto.setComments(
                    boardPost.getComments().stream().map(this::convertToCommentDto)
                            .collect(Collectors.toList())
            );
        }
        return boardPostDto;
    }
}
