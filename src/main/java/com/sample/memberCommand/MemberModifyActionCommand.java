package com.sample.memberCommand;

import com.sample.member.MemberDAO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MemberModifyActionCommand implements MemberCommand {
    @Override
    public void execute(Model model) {
        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");

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
        dao.modify(id, pw, mail, name, serial, birth, hobby, intro, address);

    }



}