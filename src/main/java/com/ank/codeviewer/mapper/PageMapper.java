package com.ank.codeviewer.mapper;

import com.ank.codeviewer.dto.PageDto;
import com.ank.codeviewer.dto.post.PostForListDto;
import com.ank.codeviewer.model.Page;
import com.ank.codeviewer.model.post.PostForList;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

@RequiredArgsConstructor
public class PageMapper {
    private final ModelMapper modelMapper;
//
//    public <T, V> Page<T> mapToPage(PageDto<V e> pageDto) {
//        return modelMapper.map(postDto, PostForList.class);
//    }
}
