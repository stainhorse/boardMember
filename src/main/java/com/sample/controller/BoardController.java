package com.sample.controller;


import com.sample.board.BoardDTO;
import com.sample.boardCommand.*;
import com.sample.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

@Controller
public class BoardController {

    BoardCommand command = null;

    private JdbcTemplate template;

    @Autowired
    public void setTemplate(JdbcTemplate template){
        this.template = template;
        Constant.template = this.template;
    }

    @RequestMapping("/board/list")
    public String list(Model model){
        System.out.println("list()");
        command = new BoardListCommand();
        command.execute(model);
        return "/board/qna_board_list";
    }

    @RequestMapping(value="/board/write", method = RequestMethod.GET)
    public String write(){
        return "/board/qna_board_write";
    }

    @RequestMapping(value = "/board/writeAction", method = RequestMethod.POST)
    public String writeAction(HttpServletRequest request, Model model){

        System.out.println("write()");
        model.addAttribute("request", request);
        command = new BoardWriteCommand();
        command.execute(model);

        command = new BoardListCommand();
        command.execute(model);

        return "/board/qna_board_list";
    }


    @RequestMapping(value="/board/delete", method = RequestMethod.GET)
    public String delete(Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.println("delete()");
        model.addAttribute("request", request);
        command = new BoardDeleteCommand();
        command.execute(model);

        Map<String,Object> map = model.asMap();
        String check = (String) map.get("msg");
        if(check != null) {
            return "alert";
        }else{
            command = new BoardListCommand();
            command.execute(model);
            return "/board/qna_board_list";
        }
    }

    @RequestMapping(value="/board/modifyAction")
    public String modifyAction(Model model, HttpServletRequest request){

        System.out.println("modifyAction()");
        model.addAttribute("request", request);
        command = new BoardModifyActionCommand();
        command.execute(model);

        /*return "redirect:list";*/
        Map<String,Object> map = model.asMap();
        String check = (String) map.get("msg");
        if(check != null) {
            return "alert";
        }else{
            command = new BoardListCommand();
            command.execute(model);
            return "/board/qna_board_list";
        }

    }


    @RequestMapping(value="/board/detail", method = RequestMethod.GET)
    public String board_detail(Model model, HttpServletRequest request){

        System.out.println("board_detail()");

        model.addAttribute("request", request);
        command = new BoardDetailCommand();
        command.execute(model);

        return "/board/qna_board_view";
    }



    @RequestMapping(value="/board/modify", method = RequestMethod.GET)
    public String boardDetail(Model model, HttpServletRequest request){

        System.out.println("board_modify()");

        model.addAttribute("request", request);
        command = new BoardModifyCommand();
        command.execute(model);

        return "/board/qna_board_modify";
    }




    @RequestMapping(value = "/board/reply")
    public String reply(HttpServletRequest request, Model model){

        System.out.println("reply()");
        model.addAttribute("request", request);

        command = new BoardReplyCommand();
        command.execute(model);

        return "/board/qna_board_reply";

    }

    @RequestMapping(value="/board/replyAction")
    public String replyAction(Model model, HttpServletRequest request){
        System.out.println("replyAction()");
        model.addAttribute("request", request);
        command = new BoardReplyActionCommand();
        command.execute(model);

        return "redirect:list";

    }
}
