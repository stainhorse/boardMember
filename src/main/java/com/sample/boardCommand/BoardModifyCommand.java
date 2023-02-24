package com.sample.boardCommand;

import com.sample.board.BoardDAO;
import com.sample.board.BoardDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
public class BoardModifyCommand implements BoardCommand {

    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int num = Integer.parseInt(request.getParameter("num"));

        BoardDAO dao = new BoardDAO();
        BoardDTO dto = dao.boardDetail(num);
        model.addAttribute("boardModify", dto);

    }



}