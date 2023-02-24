package com.sample.boardCommand;

import com.sample.board.BoardDAO;
import com.sample.board.BoardDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BoardReplyActionCommand implements BoardCommand {

    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

        int num = Integer.parseInt(request.getParameter("num"));
        String name = request.getParameter("name");
        String pass = request.getParameter("password");
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String re_ref = request.getParameter("re_ref");
        String re_lev = request.getParameter("re_lev");
        String re_seq = request.getParameter("re_seq");

        BoardDAO dao = new BoardDAO();
        dao.reply(num, name, pass, subject, content, re_ref, re_lev, re_seq);

    }



}