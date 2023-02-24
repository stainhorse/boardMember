package com.sample.boardCommand;

import com.sample.board.BoardDAO;
import com.sample.board.BoardDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BoardModifyActionCommand implements BoardCommand {

    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int num = Integer.parseInt(request.getParameter("num"));
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        System.out.println(subject);
        System.out.println(content);

        String loginName = (String) request.getSession().getAttribute("loginName");
        BoardDAO dao = new BoardDAO();
        BoardDTO dto = dao.boardDetail(num);

        if(loginName.equals(dto.getBoard_name())){
            dao.modify(num, subject, content);
        }else{
            model.addAttribute("msg", "수정 권한 없음");
            model.addAttribute("url", "/board/list");
        }


    }



}