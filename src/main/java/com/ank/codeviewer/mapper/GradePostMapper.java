package com.ank.codeviewer.mapper;

import com.ank.codeviewer.dto.GradePostDto;
import com.ank.codeviewer.model.GradePost;

public class GradePostMapper {
    public GradePostDto mapToGradePostDto(GradePost gradePost) {
        //return modelMapper.map(gradePost, GradePostDto.class);
        return new GradePostDto(gradePost.getUserId(), gradePost.getPostId(), gradePost.getValue());
    }

    public GradePost mapToGradePost(GradePostDto gradePostDto) {
        return new GradePost(gradePostDto.getUserId(), gradePostDto.getPostId(), gradePostDto.getValue());
    }
}
