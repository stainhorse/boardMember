package com.sample.memberCommand;

import com.sample.member.MemberDAO;
import com.sample.member.MemberDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MemberModifyCommand implements MemberCommand{
    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        String id = request.getParameter("id");

        MemberDAO dao = new MemberDAO();
        MemberDTO dto = dao.memberDetail(id);
        model.addAttribute("memberDetail", dto);

    }
}
