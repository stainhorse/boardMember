package com.sample.memberCommand;

import com.sample.member.MemberDAO;
import com.sample.member.MemberDTO;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MemberDeleteCommand implements MemberCommand{
    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        String id = request.getParameter("id");

        if(id.equals("admin")){
            model.addAttribute("msg", "관리자 계정은 삭제할 수 없습니다.");
            model.addAttribute("url", "/board/list");
        }else{
            MemberDAO dao = new MemberDAO();
            dao.delete(id);
        }



    }
}
