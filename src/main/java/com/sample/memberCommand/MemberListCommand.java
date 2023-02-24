package com.sample.memberCommand;

import com.sample.member.MemberDTO;
import com.sample.member.MemberDAO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class MemberListCommand implements MemberCommand {
    @Override
    public void execute(Model model) {

        MemberDAO dao = new MemberDAO();
        ArrayList<MemberDTO> member = dao.list();
        model.addAttribute("list", member);

    }
}
