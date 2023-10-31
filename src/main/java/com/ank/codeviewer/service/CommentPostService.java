package com.ank.codeviewer.service;

import com.ank.codeviewer.dto.InputCommentPostDto;
import com.ank.codeviewer.model.*;
import com.ank.codeviewer.transport.SenderCommentPostService;
import lombok.RequiredArgsConstructor;

/**
 * Обработка комментариев постов
 */
@RequiredArgsConstructor
public class CommentPostService {
    private final SenderCommentPostService sender;

    public ResponseService<CommentPost> addCommentPost(String text, int userId, int postId) {
        return sender.addCommentPost(new InputCommentPostDto(text, userId, postId));
    }

    public Page<CommentPost> getPageCommentPost(int postId, int numberPage, int sizePage) {
        return sender.getPageCommentPost(postId, numberPage, sizePage);
    }
}
