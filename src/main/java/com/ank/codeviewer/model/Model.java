package com.ank.codeviewer.model;

import com.ank.codeviewer.clientservice.ClientCommentService;
import com.ank.codeviewer.clientservice.ClientPostService;
import com.ank.codeviewer.clientservice.ClientSystemUserService;
import com.ank.codeviewer.mapper.*;
import com.ank.codeviewer.sender.HttpRequestBuilder;
import com.ank.codeviewer.sender.RequestStructureBuilder;
import com.ank.codeviewer.service.*;
import com.ank.codeviewer.transport.*;
import com.ank.codeviewer.util.HttpPathBuilder;
import com.ank.codeviewer.view.Dialogs;
import com.ank.codeviewer.view.PaneControllerBuilder;
import com.ank.codeviewer.view.ViewFactory;
import com.ank.codeviewer.view.WindowControllerBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Getter;
import org.modelmapper.ModelMapper;

@Getter
public class Model {
    private static Model model;
    private final ViewFactory viewFactory;
    /**
     * JSON маппер
     */
    private final ObjectMapper objectMapper;
    /**
     * Создает структуру запроса
     */
    private final RequestStructureBuilder requestStructureBuilder;
    /**
     * Сервис отправки запросов
     */
    private final SenderService senderService;
    /**
     * Построитель пути запроса
     */
    private final HttpPathBuilder httpPathBuilder;
    /**
     * Построитель запроса
     */
    private final HttpRequestBuilder httpRequestBuilder;
    private final PostMapper postMapper;
    /**
     * Получение данных о постах
     */
    private final SenderPostService senderPostService;
    private final SenderLandCodeService senderLandCodeService;
    private final LandCodeService landCodeService;
    private final SenderSystemUserService senderSystemUserService;
    private final ClientSystemUserService clientSystemUserService;
    private final SystemUserService systemUserService;
    private final PostService postService;
    private final ClientPostService clientPostService;
    private final UserMapper userMapper;
    private final GradePostMapper gradePostMapper;
    private final SenderGradePostService senderGradePostService;
    private final GradePostService gradePostService;
    private final ClientCommentService clientCommentService;
    private final SenderCommentPostService senderCommentPostService;
    private final CommentPostService commentPostService;
    /**
     * Тестовый сервис
     */
    private final SenderTestService senderTestService;
    private final ObjectProperty<SystemUser> systemUser;
    private final Dialogs dialogs;
    private Model() {
        String title = "Code viewer";
        String serverAddress = "";//http://localhost:8080";

        dialogs = new Dialogs(title);
        viewFactory = new ViewFactory("Code viewer", new WindowControllerBuilder(title, null), new PaneControllerBuilder());

        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        httpPathBuilder = new HttpPathBuilder();
        httpRequestBuilder = new HttpRequestBuilder(serverAddress, objectMapper, httpPathBuilder);
        senderService = new SenderService(httpRequestBuilder, objectMapper);
        requestStructureBuilder = new RequestStructureBuilder();

        clientPostService = new ClientPostService();

        senderSystemUserService = new SenderSystemUserService(senderService, requestStructureBuilder, new SystemUserMapper());
        systemUser = new SimpleObjectProperty<>();
        systemUserService = new SystemUserService(senderSystemUserService);
        //systemUserService.setSystemUser(SystemUser.createUnregisteredUser());
        clientSystemUserService = new ClientSystemUserService();

        userMapper = new UserMapper();
        LangCodeMapper langCodeMapper = new LangCodeMapper();
        postMapper = new PostMapper(userMapper, langCodeMapper);

        senderPostService = new SenderPostService(senderService, requestStructureBuilder, postMapper);
        postService = new PostService(senderPostService);
        senderLandCodeService = new SenderLandCodeService(senderService, requestStructureBuilder, langCodeMapper);
        landCodeService = new LandCodeService(senderLandCodeService);
        senderTestService = new SenderTestService(senderService, requestStructureBuilder);

        gradePostMapper = new GradePostMapper();
        senderGradePostService = new SenderGradePostService(senderService, requestStructureBuilder, gradePostMapper);
        gradePostService = new GradePostService(senderGradePostService);

        senderCommentPostService = new SenderCommentPostService(senderService, requestStructureBuilder, new CommentPostMapper());
        commentPostService = new CommentPostService(senderCommentPostService);
        clientCommentService = new ClientCommentService();
    }

    public static synchronized Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void refreshServerAddress(String serverAddress) {
        httpRequestBuilder.setServerAddress(serverAddress);
    }
    public String getServerAddress() {
        return httpRequestBuilder.getServerAddress();
    }
    public SystemUser getSystemUser() {
        return systemUser.get();
    }

    public ObjectProperty<SystemUser> systemUserProperty() {
        return systemUser;
    }

    public void setSystemUser(SystemUser systemUser) {
        this.systemUser.set(systemUser);
    }
}
