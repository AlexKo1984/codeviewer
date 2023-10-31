package com.ank.codeviewer.transport;

import com.ank.codeviewer.dto.TestConnectDto;
import com.ank.codeviewer.sender.RequestStructure;
import com.ank.codeviewer.sender.RequestStructureBuilder;
import com.ank.codeviewer.sender.ResponseStructure;
import com.ank.codeviewer.sender.enums.RequestToServer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SenderTestService {
    private final SenderService sender;
    private final RequestStructureBuilder builder;

    public boolean isExistConnect(){
        RequestStructure structure = builder.build(RequestToServer.GET_TEST_CONNECT);

        ResponseStructure<TestConnectDto> responseStructure = sender.send(structure, TestConnectDto.class);

        return responseStructure.isOk();
    }
}
