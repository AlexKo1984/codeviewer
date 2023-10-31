package com.ank.codeviewer.transport;

import com.ank.codeviewer.dto.post.PagePostForListDto;
import com.ank.codeviewer.dto.post.PostDto;
import com.ank.codeviewer.mapper.PostMapper;
import com.ank.codeviewer.model.Page;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.model.post.Post;
import com.ank.codeviewer.model.post.PostForList;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.RequestStructureBuilder;
import com.ank.codeviewer.sender.ResponseStructure;
import com.ank.codeviewer.sender.enums.RequestToServer;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class SenderPostService {
    private final SenderService sender;
    private final RequestStructureBuilder builder;
    private final PostMapper postMapper;

    public Page<PostForList> getPagePosts(int numberPage, int sizePage, int idLangCode, String subString){
        RequestStructure structure = builder.build(RequestToServer.GET_PAGE_POSTS);
        structure.addParam("from", numberPage);//Номер станицы
        structure.addParam("size", sizePage);//Размер страницы
        structure.addParam("id-lang-code", idLangCode);//ид языка кода
        structure.addParam("sub-str", subString);//Подстрока поиска в коде

        ResponseStructure<PagePostForListDto> responseStructure = sender.send(structure,  PagePostForListDto.class);

        PagePostForListDto dto = responseStructure.getDtoObject();
        Page<PostForList> page = new Page<>(
                dto.getTotalPage(),
                postMapper.mapToPostForList(dto.getContent()),
                dto.getPageNumber(),
                dto.getPageSize(),
                dto.getTotalPage());

        return page;
    }

    public ResponseService<Post> addPost(Post post) {
        RequestStructure structure = builder.build(RequestToServer.CREATE_POST);
        structure.setDtoToJson(postMapper.mapToInputPostDto(post));

        ResponseStructure<PostDto> responseStructure = sender.send(structure,  PostDto.class);

        ResponseService<Post> result = new ResponseService<>(responseStructure.isOk(),
                responseStructure.getErrorMessage(),
                postMapper.mapToPost(responseStructure.getDtoObject()));

        return result;
    }

    public ResponseService<Post> findPost(int postId) {
        RequestStructure structure = builder.build(RequestToServer.FIND_POST);
        structure.addPathParam("id", String.valueOf(postId));

        ResponseStructure<PostDto> responseStructure = sender.send(structure,  PostDto.class);

        ResponseService<Post> result = new ResponseService<>(responseStructure.isOk(),
                responseStructure.getErrorMessage(),
                postMapper.mapToPost(responseStructure.getDtoObject()));

        return result;
    }

    public ResponseService<Post> updatePost(Post post) {
        RequestStructure structure = builder.build(RequestToServer.UPDATE_POST);
        structure.addPathParam("id", String.valueOf(post.getId()));
        structure.setDtoToJson(post);

        ResponseStructure<PostDto> responseStructure = sender.send(structure,  PostDto.class);

        ResponseService<Post> result = new ResponseService<>(responseStructure.isOk(),
                responseStructure.getErrorMessage(),
                postMapper.mapToPost(responseStructure.getDtoObject()));

        return result;
    }

    public ResponseService<Object> deletePost(int postId) {
        RequestStructure structure = builder.build(RequestToServer.DELETE_POST);
        structure.addPathParam("postId", String.valueOf(postId));

        ResponseStructure<Object> responseStructure = sender.send(structure,  Object.class);

        ResponseService<Object> result = new ResponseService<>(responseStructure.isOk(),
                responseStructure.getErrorMessage(),
                null);

        return result;
    }
}
