package com.ank.codeviewer.service;

import com.ank.codeviewer.model.Page;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.post.Post;
import com.ank.codeviewer.model.post.PostForList;
import com.ank.codeviewer.transport.SenderPostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostService {
    private final SenderPostService senderPostService;

    public ResponseService<Post> addPost(Post post) {
        return senderPostService.addPost(post);
    }

    public ResponseService<Post> findPost(int postId) {
        return senderPostService.findPost(postId);
    }

    public ResponseService<Post> updatePost(Post post) {
        return senderPostService.updatePost(post);
    }

    public ResponseService<Object> deletePost(int postId) {
        return senderPostService.deletePost(postId);
    }
    public Page<PostForList> getPagePosts(int numberPage, int sizePage, int idLangCode, String subString){
        return senderPostService.getPagePosts(numberPage, sizePage, idLangCode, subString);
    }
}


