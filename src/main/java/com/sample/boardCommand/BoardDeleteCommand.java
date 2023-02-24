package com.sample.boardCommand;

import com.sample.board.BoardDAO;
import com.sample.board.BoardDTO;
import com.sample.member.MemberDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class BoardDeleteCommand implements BoardCommand {
    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        int num = Integer.parseInt(request.getParameter("num"));

        String loginName = (String) request.getSession().getAttribute("loginName");

        BoardDAO dao = new BoardDAO();
        BoardDTO dto = dao.boardDetail(num);

      /*  if(Objects.equals(loginId, dto.getBoard_name())){*/
        if(loginName.equals(dto.getBoard_name())){
            dao.delete(num);
        }else{
            model.addAttribute("msg", "삭제 권한 없음");
            model.addAttribute("url", "/board/list");
        }
    }
}
