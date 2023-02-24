package com.sample.boardCommand;

import com.sample.board.BoardDAO;
import com.sample.board.BoardDTO;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BoardWriteCommand implements BoardCommand {

    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        String name = request.getParameter("name");
        String pass = request.getParameter("password");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String file = request.getParameter("file");

        BoardDAO dao = new BoardDAO();
        dao.write(name, pass, subject, content, file);
    }
}









