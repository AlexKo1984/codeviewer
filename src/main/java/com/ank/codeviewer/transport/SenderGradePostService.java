package com.ank.codeviewer.transport;

import com.ank.codeviewer.dto.GradePostDto;
import com.ank.codeviewer.mapper.GradePostMapper;
import com.ank.codeviewer.model.GradePost;
import com.ank.codeviewer.model.ResponseService;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.RequestStructureBuilder;
import com.ank.codeviewer.sender.ResponseStructure;
import com.ank.codeviewer.sender.enums.RequestToServer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SenderGradePostService {
    private final SenderService sender;
    private final RequestStructureBuilder builder;
    private final GradePostMapper mapper;
    public ResponseService<GradePost> findByUserIdAndPostId(int userId, int postId) {
        RequestStructure structure = builder.build(RequestToServer.FIND_GRADE);
        structure.addPathParam("userId", String.valueOf(userId));
        structure.addPathParam("postId", String.valueOf(postId));


        ResponseStructure<GradePostDto> responseStructure = sender.send(structure, GradePostDto.class);

        ResponseService<GradePost> result = new ResponseService<>(responseStructure.isOk(),
                responseStructure.getErrorMessage(), null);

        if (responseStructure.isOk()){
            result.setObject(mapper.mapToGradePost(responseStructure.getDtoObject()));
        }

        return result;
    }

    public ResponseService<GradePost> createGradePost(GradePost gradePost) {
        RequestStructure structure = builder.build(RequestToServer.CREATE_GRADE_POST);
        structure.setDtoToJson(mapper.mapToGradePostDto(gradePost));

        ResponseStructure<GradePostDto> responseStructure = sender.send(structure,  GradePostDto.class);

        ResponseService<GradePost> result = new ResponseService<>(responseStructure.isOk(),
                responseStructure.getErrorMessage(),
                null);

        return result;
    }
}
