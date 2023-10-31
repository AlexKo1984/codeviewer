package com.ank.codeviewer.clientservice;

import com.ank.codeviewer.controller.AuthenticationSystemUserWindowController;
import com.ank.codeviewer.controller.SystemUserPropertyWindowController;
import com.ank.codeviewer.model.Model;
import javafx.stage.Window;
import lombok.Getter;
import lombok.Setter;

/**
 * Формы для регистрации, входа и выхода пользователя
 */
@Getter
@Setter
public class ClientSystemUserService {
    public void beginAuthenticationSystemUser(Window owner) {
        AuthenticationSystemUserWindowController controller = Model.getInstance().getViewFactory().getAuthenticationSystemUserWindowController(owner);
        controller.setSystemUserService(Model.getInstance().getSystemUserService());
        controller.setDialogs(Model.getInstance().getDialogs());
        controller.show();
    }
    public void beginRegistratioNewSystemUser(Window owner) {
        Model model = Model.getInstance();

        SystemUserPropertyWindowController controller = model.getViewFactory().getSystemUserPropertyWindowController(owner);
        controller.setSystemUserService(model.getSystemUserService());
        controller.setDialogs(model.getDialogs());
        controller.setMode(SystemUserPropertyWindowController.Mode.REGISTRATION);
        controller.show();
    }
    public void informationSystemUser(Window owner) {
        Model model = Model.getInstance();

        SystemUserPropertyWindowController controller = model.getViewFactory().getSystemUserPropertyWindowController(owner);
        controller.setSystemUserService(model.getSystemUserService());
        controller.setDialogs(model.getDialogs());
        controller.setMode(SystemUserPropertyWindowController.Mode.INFO);
        controller.show();
    }
    /**
     * Выход пользователя из системы
     * @param owner
     */
    public void exitSystemUser(Window owner, boolean askQuestion) {
        boolean yes = !askQuestion;

        if (askQuestion)
                yes = Model.getInstance().getDialogs().questionYesNo("Выход пользователя из системы", "Выйти?");

        if (yes)
            Model.getInstance().getSystemUserService().exitSystemUser();
    }
}
