package com.ank.codeviewer.service;

import com.ank.codeviewer.dto.langcode.ListLangCogeDto;
import com.ank.codeviewer.model.LangCode;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.ResponseStructure;
import com.ank.codeviewer.sender.enums.RequestToServer;
import com.ank.codeviewer.transport.SenderLandCodeService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class LandCodeService {
    private final SenderLandCodeService sender;
    public List<LangCode> getAllLandCode(){
        return sender.getAllLandCode();
    }
}
