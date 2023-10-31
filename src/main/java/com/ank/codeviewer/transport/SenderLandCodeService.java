package com.ank.codeviewer.transport;

import com.ank.codeviewer.dto.langcode.ListLangCogeDto;
import com.ank.codeviewer.mapper.LangCodeMapper;
import com.ank.codeviewer.model.LangCode;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.RequestStructureBuilder;
import com.ank.codeviewer.sender.ResponseStructure;
import com.ank.codeviewer.sender.enums.RequestToServer;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SenderLandCodeService {
    private final SenderService sender;
    private final RequestStructureBuilder builder;
    private final LangCodeMapper langCodeMapper;

    public List<LangCode> getAllLandCode(){
        RequestStructure structure = builder.build(RequestToServer.GET_ALL_LANGCODE);

        ResponseStructure<ListLangCogeDto> responseStructure = sender.send(structure, ListLangCogeDto.class);

        return langCodeMapper.mapToLangCode(responseStructure.getDtoObject());
    }
}
