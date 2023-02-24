package com.sample.controller;

import com.sample.dao.MemberDao;
import com.sample.member.MemberDAO;
import com.sample.member.MemberDTO;
import com.sample.memberCommand.MemberLoginCommand;
import com.sample.memberCommand.*;
import com.sample.util.Constant;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class MemberController {

    MemberCommand command = null;

    private JdbcTemplate template;

    @Autowired
    private SqlSession sqlSession;

    //빈 주입
    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
        Constant.template = this.template;
    }

    //유저 목록 불러오기
    @RequestMapping("/member/list")
    public String list(HttpServletRequest request, Model model){
        System.out.println("유저목록 호출됨");
        HttpSession session = request.getSession();
        String check = (String) session.getAttribute("id");
        if(check.equals("admin")){
            MemberDao dao = sqlSession.getMapper(MemberDao.class);
            model.addAttribute("list", dao.list());
            return "/member/member_list";
        }else{
            model.addAttribute("msg", "접근 권한 없음");
            model.addAttribute("url", "/board/list");
            return "alert";
        }
    }

    //회원가입 페이지
    @RequestMapping("/member/join")
    public String join(){
        System.out.println("회원가입 페이지 호출됨");
        return "/member/joinForm";
    }


    @RequestMapping(value="/member/joinAction", method = RequestMethod.POST)
    public String joinAction(HttpServletRequest request, Model model){

        System.out.println("회원가입 호출됨");
        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
        int serial = Integer.parseInt(request.getParameter("serial"));
        int birth = Integer.parseInt(request.getParameter("birth"));
        String id = request.getParameter("id");

        int result = memberDao.idChk(id);
        System.out.println(result);
        try {
            if(result == 1){
                model.addAttribute("msg", "사용할 수 없는 아이디입니다.");
                model.addAttribute("url", "join");
                return "alert";
            }else{
                memberDao.join(request.getParameter("id"), request.getParameter("pw"), request.getParameter("mail"), request.getParameter("name"), serial, birth, request.getParameter("hobby"), request.getParameter("intro"), request.getParameter("address"));
            }
        }catch (Exception e){
            throw new RuntimeException();
        }

        memberDao.join(id, request.getParameter("pw"), request.getParameter("mail"), request.getParameter("name"), serial, birth, request.getParameter("hobby"), request.getParameter("intro"), request.getParameter("address"));
        return "/member/loginForm";

    }

    //로그인
    @RequestMapping("/member/login")
    public String memberLogin(HttpServletRequest request, Model model) {
        return "member/loginForm";
    }

    @RequestMapping(value = "/member/loginAction", method = RequestMethod.POST)
    public String MemberLoginAction(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        System.out.println("로그인 요청됨");
        String  page="/board/list";

        MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
        MemberDTO member = memberDao.memberLogin(request.getParameter("id"), request.getParameter("pw"));
        String loginId = member.getId();
        session.setAttribute("id", loginId);

        return "redirect:"+page;

       /* model.addAttribute("request", request);
        command = new MemberLoginCommand();
        command.execute(model);
        Map<String,Object> map = model.asMap();
        String check = (String) map.get("id");
        if(check != null) {
            session.setAttribute("id", check);
            page="/board/list";
        }else{
            return "alert";
        }
        return "redirect:"+page;*/
    }


    //유저 상세보기
    @RequestMapping(value="/member/detail", method = RequestMethod.GET)
    public String memberDetail(Model model, HttpServletRequest request){

        System.out.println("유저 상세보기 호출됨");
        MemberDao dao = sqlSession.getMapper(MemberDao.class);
        MemberDTO dto = dao.memberDetail(request.getParameter("id"));
        model.addAttribute("memberDetail", dto);

        return "/member/member_info";

    }


    @RequestMapping(value = "/member/deleteAction", method = RequestMethod.GET)
    public String MemberDelete(HttpServletRequest request, Model model) {
        System.out.println("유저 삭제 요청됨");


        String id = request.getParameter("id");

        if(id.equals("admin")){
            model.addAttribute("msg", "관리자 계정은 삭제할 수 없습니다.");
            model.addAttribute("url", "/board/list");
            return "alert";
        }else{
            MemberDao dao = sqlSession.getMapper(MemberDao.class);
            dao.delete(request.getParameter("id"));
            return "redirect:/member/logout";
        }

       /* model.addAttribute("request", request);
        command = new MemberDeleteCommand();
        command.execute(model);

        Map<String,Object> map = model.asMap();
        String check = (String) map.get("msg");
        if(check != null) {
            return "alert";
        }else{
            command = new MemberListCommand();
            command.execute(model);

            return "redirect:/member/logout";
        }*/
    }

    @RequestMapping("/member/logout")
    public String goLogoutAction() throws Exception {
        return "member/logout";
    }

    @RequestMapping(value="/member/modify", method = RequestMethod.GET)
    public String memberModify(Model model, HttpServletRequest request){
        System.out.println("member_modify()");
        model.addAttribute("request", request);
        command = new MemberModifyCommand();
        command.execute(model);

        return "/member/member_modify";
    }

    @RequestMapping(value="/member/modifyAction")
    public String modifyAction(Model model, HttpServletRequest request){
        System.out.println("modifyAction()");
        model.addAttribute("request", request);
        command = new MemberModifyActionCommand();
        command.execute(model);

        return "redirect:/board/list";

    }

}
