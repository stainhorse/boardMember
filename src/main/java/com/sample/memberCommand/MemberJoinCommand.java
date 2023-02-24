package com.sample.memberCommand;

import com.sample.member.MemberDAO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class MemberJoinCommand implements MemberCommand{

    @Override
    public void execute(Model model) {
        int result = 0;

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        HttpSession session = request.getSession();

        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String mail = request.getParameter("mail");
        String name = request.getParameter("name");
        int serial = Integer.parseInt(request.getParameter("serial"));
        int birth = Integer.parseInt(request.getParameter("birth"));
        String hobby = request.getParameter("hobby");
        String intro = request.getParameter("intro");
        String address = request.getParameter("address");

        MemberDAO dao = new MemberDAO();
        result =  dao.join(id, pw, mail, name, serial, birth, hobby, intro, address);
        if(result == -1){
            model.addAttribute("msg", "이미 존재하는 아이디입니다.");
            model.addAttribute("url", "join");
        }
    }
}
