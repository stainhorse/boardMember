package com.sample.boardCommand;

import com.sample.db.BDao;
import com.sample.db.BDto;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BoardReplyViewCommand implements BoardCommand {
    @Override
    public void execute(Model model) {

        Map<String, Object> map = model.asMap();
        HttpServletRequest request = (HttpServletRequest) map.get("request");
        String id = request.getParameter("id");
        BDao dao = new BDao();
        BDto dto = dao.reply_view(id);
        model.addAttribute("reply_view", dto);

    }
}
