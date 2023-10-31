package com.ank.codeviewer.mapper;

import com.ank.codeviewer.dto.post.InputPostDto;
import com.ank.codeviewer.dto.post.PostDto;
import com.ank.codeviewer.dto.post.PostForListDto;
import com.ank.codeviewer.model.post.Post;
import com.ank.codeviewer.model.post.PostForList;
import com.ank.codeviewer.util.ConvertNumber;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PostMapper {
    private final UserMapper userMapper;
    private final LangCodeMapper langCodeMapper;

    public PostForList mapToPostForList(PostForListDto postDto) {
        return PostForList.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .dateChange(postDto.getDateChange())
                .grade(ConvertNumber.toAvgGrade(postDto.getGrade()))
                .userLogin(postDto.getUserLogin())
                .userId(postDto.getUserId())
                .build();
        //return modelMapper.map(postDto, PostForList.class);
    }
    public Post mapToPost(PostDto postDto) {
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .description(postDto.getDescription())
                .code(postDto.getCode())
                .dateChange(postDto.getDateChange())
                .avgGrade(
                        ConvertNumber.toAvgGrade(postDto.getAvgGrade()))
                .user(userMapper.mapToShortUserDto(postDto.getOwner()))
                .langCode(langCodeMapper.mapToLangCode(postDto.getLangCode()))
                .build();
        //return modelMapper.map(postDto, PostForList.class);
    }
    public InputPostDto mapToInputPostDto(Post post) {
        return InputPostDto.builder()
                .title(post.getTitle())
                .description(post.getDescription())
                .code(post.getCode())
                .userId(post.getUser().getId())
                .langCodeId(post.getLangCode().getId())
                .build();
        //return modelMapper.map(postDto, PostForList.class);
    }
    public List<PostForList> mapToPostForList(Iterable<PostForListDto> postForListDtos) {
        List<PostForList> result = new ArrayList<>();

        for (PostForListDto post : postForListDtos) {
            result.add(mapToPostForList(post));
        }

        return result;
    }
}
