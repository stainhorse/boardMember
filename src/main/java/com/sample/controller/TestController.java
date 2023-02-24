package com.sample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

@RestController
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/")
    public String test(Model model) {
        logger.info("Test Controller");
        model.addAttribute("data", "data");

        Connection conn = null;

        try{
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/postgresql");
            conn = ds.getConnection();
            System.out.println("<h3>c.conn</h3>");
        }catch(Exception e) {
            System.out.println("<h3>fuck!.</h3>");
            e.printStackTrace();
        }

        return "home";
    }
}
