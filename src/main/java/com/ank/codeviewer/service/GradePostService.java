package com.ank.codeviewer.service;

import com.ank.codeviewer.model.GradePost;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.transport.SenderGradePostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GradePostService {
    private final SenderGradePostService service;

    public ResponseService<GradePost> findByUserIdAndPostId(int userId, int postId) {
        return service.findByUserIdAndPostId(userId, postId);
    }

    public ResponseService<GradePost> createGradePost(int userId, int postId, int value) {
        return service.createGradePost(new GradePost(userId, postId, value));
    }
}


