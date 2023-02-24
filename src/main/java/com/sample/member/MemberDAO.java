package com.sample.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sample.util.Constant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.sample.member.MemberDAO;
import com.sample.member.MemberDTO;

public class MemberDAO {

    JdbcTemplate template;
    public MemberDAO(){this.template = Constant.template; }

    public ArrayList<MemberDTO> list() {

        String query = "select id, name, mail from users";
        return (ArrayList<MemberDTO>) template.query(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));

    }


    public int join(String id, String pw, String mail, String name, int serial, int birth, String hobby, String intro, String address) {

        try {
            this.template.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection con)
                        throws SQLException {
                    String query = "insert into users (id, pw, mail, name, serial, birth, hobby, intro, address) values (?, ?, ?, ?, ?,  ?, ?, ?, ?)";
                    PreparedStatement pstmt = con.prepareStatement(query);
                    pstmt.setString(1, id);
                    pstmt.setString(2, pw);
                    pstmt.setString(3, mail);
                    pstmt.setString(4, name);
                    pstmt.setInt(5, serial);
                    pstmt.setInt(6, birth);
                    pstmt.setString(7, hobby);
                    pstmt.setString(8, intro);
                    pstmt.setString(9, address);
                    return pstmt;
                }
            });
            return 1;
        }catch(Exception e){
            e.printStackTrace();
        }return -1;
    }

    public MemberDTO memberDetail(String id) {
        String query = "select * from users where id = '" + id + "'";
        return template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class));
    }

    public int memberLogin(String id, String pw) {
        String query = "select * from users where id = ? and pw = ?";
        try {
            MemberDTO dto = template.queryForObject(query, new BeanPropertyRowMapper<MemberDTO>(MemberDTO.class),id, pw);
            assert dto != null; //?
            if(dto.getPw().equals(pw)){
                return 1;   //성공
            }else{
                return 0; //실패
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -2;
    }

    public void modify(String id, String pw, String mail, String name, int serial, int birth, String hobby, String intro, String address) {
        String query = "update users set pw = ?, mail = ?, name = ?, serial = ?, birth = ?, hobby = ?, intro = ?, address = ? where id = ?";
        this.template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, pw);
                ps.setString(2, mail);
                ps.setString(3, name);
                ps.setInt(4, serial);
                ps.setInt(5, birth);
                ps.setString(6, hobby);
                ps.setString(7, intro);
                ps.setString(8, address);
                ps.setString(9, id);
            }
        });

    }

    public void delete(final String id) {

        String query = "delete from users where id = ?";
        this.template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, id);
            }
        });
    }




}
