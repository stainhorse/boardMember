package com.sample.memberCommand;

import com.sample.member.MemberDAO;
import com.sample.member.MemberDTO;
import com.sample.memberCommand.MemberCommand;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class MemberLoginCommand implements MemberCommand {

    @Override
    public void execute(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        MemberDTO dto = new MemberDTO();
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        //session.setAttribute("loginSession", dto.getId());

        MemberDAO dao = new MemberDAO();
        int result = dao.memberLogin(id, pw);
        if (result == 1) {
            model.addAttribute("id", id);
            dto = dao.memberDetail(id);
            //session.setAttribute("loginedMember", dto);
            session.setAttribute("loginName", dto.getName());
        } else {
            model.addAttribute("msg", "로그인 실패");
            model.addAttribute("url", "login");
        }
    }
}