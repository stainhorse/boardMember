package com.sample.memberCommand;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MemberCommand {

    void execute(Model model);

}
