package com.ank.codeviewer.transport;

import com.ank.codeviewer.dto.CommentPostDto;
import com.ank.codeviewer.dto.InputCommentPostDto;
import com.ank.codeviewer.dto.PageCommentPostDto;
import com.ank.codeviewer.mapper.CommentPostMapper;
import com.ank.codeviewer.model.CommentPost;
import com.ank.codeviewer.model.Page;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.RequestStructureBuilder;
import com.ank.codeviewer.sender.ResponseStructure;
import com.ank.codeviewer.sender.enums.RequestToServer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SenderCommentPostService {
    private final SenderService sender;
    private final RequestStructureBuilder builder;
    private final CommentPostMapper mapper;

    public ResponseService<CommentPost> addCommentPost(InputCommentPostDto dto) {
        RequestStructure structure = builder.build(RequestToServer.CREATE_COMMENT_POST);
        structure.setDtoToJson(dto);

        ResponseStructure<CommentPostDto> responseStructure = sender.send(structure,  CommentPostDto.class);

        ResponseService<CommentPost> result = new ResponseService<>(responseStructure.isOk(),
                responseStructure.getErrorMessage(),
                null);

        return result;
    }

    public Page<CommentPost> getPageCommentPost(int postId, int numberPage, int sizePage) {
        RequestStructure structure = builder.build(RequestToServer.GET_PAGE_COMMENTS_POST);
        structure.addPathParam("postId", String.valueOf(postId));
        structure.addParam("from", numberPage);//Номер станицы
        structure.addParam("size", sizePage);//Размер страницы

        ResponseStructure<PageCommentPostDto> responseStructure = sender.send(structure,  PageCommentPostDto.class);

        PageCommentPostDto dto = responseStructure.getDtoObject();
        Page<CommentPost> page = new Page<>(
                dto.getTotalPage(),
                mapper.mapToCommentForList(dto.getContent()),
                dto.getPageNumber(),
                dto.getPageSize(),
                dto.getTotalPage());

        return page;
    }
}
