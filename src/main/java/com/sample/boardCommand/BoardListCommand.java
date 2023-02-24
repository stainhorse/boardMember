package com.sample.boardCommand;

import com.sample.board.BoardDAO;
import com.sample.board.BoardDTO;
import org.springframework.ui.Model;

import java.util.ArrayList;

public class BoardListCommand implements BoardCommand {
    @Override
    public void execute(Model model) {

        BoardDAO dao = new BoardDAO();
        ArrayList<BoardDTO> dto = dao.list();
        model.addAttribute("list", dto);

    }
}
