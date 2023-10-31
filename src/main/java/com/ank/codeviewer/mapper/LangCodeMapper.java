package com.ank.codeviewer.mapper;;

import com.ank.codeviewer.dto.langcode.LangCodeDto;
import com.ank.codeviewer.dto.langcode.ListLangCogeDto;
import com.ank.codeviewer.model.LangCode;

import java.util.ArrayList;
import java.util.List;


public class LangCodeMapper {
    public List<LangCode> mapToLangCode(ListLangCogeDto langCodeDtos) {
        List<LangCode> result = new ArrayList<>();

        for (LangCodeDto langCodeDto: langCodeDtos) {
            result.add(mapToLangCode(langCodeDto));
        }

        return result;
    }
    public LangCode mapToLangCode(LangCodeDto langCodeDto) {
        return new LangCode(langCodeDto.getId(), langCodeDto.getName(), langCodeDto.getTitle());
    }
}
