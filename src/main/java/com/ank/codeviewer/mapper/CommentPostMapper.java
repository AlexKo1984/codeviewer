package com.ank.codeviewer.mapper;

import com.ank.codeviewer.dto.CommentPostDto;
import com.ank.codeviewer.dto.InputCommentPostDto;
import com.ank.codeviewer.dto.post.InputPostDto;
import com.ank.codeviewer.dto.post.PostForListDto;
import com.ank.codeviewer.model.CommentPost;
import com.ank.codeviewer.model.post.PostForList;

import java.util.ArrayList;
import java.util.List;

public class CommentPostMapper {
    public CommentPostDto mapToCommentPostDto(CommentPost commentPost) {
        return CommentPostDto.builder()
                .id(commentPost.getId())
                .dateCreate(commentPost.getDateCreate())
                .userLogin(commentPost.getUserLogin())
                .postId(commentPost.getPostId())
                .text(commentPost.getText())
                .build();
    }
    public CommentPost mapToCommentPost(CommentPostDto commentPost) {
        return CommentPost.builder()
                .id(commentPost.getId())
                .dateCreate(commentPost.getDateCreate())
                .userLogin(commentPost.getUserLogin())
                .postId(commentPost.getPostId())
                .text(commentPost.getText())
                .build();
    }
    public List<CommentPost> mapToCommentForList(List<CommentPostDto> commentPostDtos) {
        List<CommentPost> result = new ArrayList<>();

        for (CommentPostDto commentPostDto : commentPostDtos) {
            result.add(mapToCommentPost(commentPostDto));
        }

        return result;
    }
}
