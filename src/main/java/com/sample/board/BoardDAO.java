package com.sample.board;

import com.sample.member.MemberDTO;
import com.sample.util.Constant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BoardDAO {

    JdbcTemplate template;

    public BoardDAO() {
        this.template = Constant.template;
    }

    public ArrayList<BoardDTO> list() {

        String query = "select * from board";
        return (ArrayList<BoardDTO>) template.query(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));

    }

    public void write(final String boardName, final String boardPass,final String boardSubject,final String boardContent,final String boardFile) {
        this.template.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                String query ="insert into board values (nextval('board_seq'), ?, ?, ?, ?, ?,currval('board_seq'), 0, 0, 0, NOW())";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, boardName);
                pstmt.setString(2, boardPass);
                pstmt.setString(3, boardSubject);
                pstmt.setString(4, boardContent);
                pstmt.setString(5, boardFile);
                return pstmt;
            }
        });
    }

    public BoardDTO boardDetail(int num) {
        upHit(num);
        String query = "select * from board where board_num = '" + num + "'";
        return template.queryForObject(query, new BeanPropertyRowMapper<BoardDTO>(BoardDTO.class));
    }

    private void upHit(final int num) {
        String query = "update board set board_readcount = board_readcount + 1 where board_num = ?";
        this.template.update(query, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, num);
            }
        });

    }


    public void delete(final int num) {
        String query = "delete from board where board_num = ?";
        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, num);
            }
        });

    }

    public void modify(int num, String subject, String content) {
        System.out.println(num);
        System.out.println(subject);
        String query = "update board set board_subject = ?, board_content = ? where board_num = ?";

        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, subject);
                ps.setString(2, content);
                ps.setInt(3, num);
            }
        });



    }

    public void reply(int num, String name, String pass, String subject, String content, String reRef, String reLev, String reSeq) {

        replyShape(reRef, reLev);

        this.template.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                String query ="insert into board (BOARD_NUM, BOARD_NAME, BOARD_PASS, BOARD_SUBJECT, BOARD_CONTENT, BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ, BOARD_READCOUNT, BOARD_DATE) values (nextval('board_seq'), ?, ?, ?, ?, ?, ?, ?, 0, NOW())";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, pass);
                pstmt.setString(3, subject);
                pstmt.setString(4, content);
                pstmt.setInt(5, Integer.parseInt(reRef));
                pstmt.setInt(6, Integer.parseInt(reLev)+1);
                pstmt.setInt(7, Integer.parseInt(reSeq)+1);
                return pstmt;
            }
        });

    }

    private void replyShape(String BOARD_RE_REF, String BOARD_RE_LEV) {
        String query = "update board set BOARD_RE_LEV = BOARD_RE_LEV + 1 where BOARD_RE_REF = ? and BOARD_RE_LEV > ?";

        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, Integer.parseInt(BOARD_RE_REF));
                ps.setInt(2, Integer.parseInt(BOARD_RE_LEV));
            }
        });
    }
/*    // 글 조회수 증가 SQL
    public void upHit(int BOARD_NUM) {
        String query = "UPDATE board SET BOARD_READCOUNT = BOARD_READCOUNT + 1 WHERE BOARD_NUM = ?";
        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, BOARD_NUM);
            }
        });
    }*/

}
