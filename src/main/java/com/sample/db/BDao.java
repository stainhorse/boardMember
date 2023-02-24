package com.sample.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sample.util.Constant;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;


import com.sample.db.BDao;
import com.sample.db.BDto;

public class BDao {

    JdbcTemplate template;

    public BDao() {
        this.template = Constant.template;
    }

    public void write(final String name, final String title, final String content) {

        //콜백함수로 구현함 아래랑 같은 코드임
//        this.template.update(con -> {
//            String query = "insert into mvc_board (id, name, title, content, hit, _group, step, indent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
//            PreparedStatement pstmt = con.prepareStatement(query);
//            pstmt.setString(1, name);
//            pstmt.setString(2, title);
//            pstmt.setString(3, content);
//            return pstmt;
//        });

        this.template.update(new PreparedStatementCreator() {

            @Override
            public PreparedStatement createPreparedStatement(Connection con)
                    throws SQLException {
                String query = "insert into mvc_board (id, name, title, content, hit, _group, step, indent) values (nextval('mvc_board_seq'), ?, ?, ?, 0, currval('mvc_board_seq'), 0, 0 )";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, name);
                pstmt.setString(2, title);
                pstmt.setString(3, content);
                return pstmt;
            }
        });

    }

    public ArrayList<BDto> list() {

        String query = "select id, name, title, content, date, hit, _group, step, indent from mvc_board order by _group desc, step asc";
        return (ArrayList<BDto>) template.query(query, new BeanPropertyRowMapper<BDto>(BDto.class));

    }

    //   @SuppressWarnings("deprecation")
    public BDto contentView(int strID) {
        // TODO Auto-generated method stub

        upHit(strID);

        String query = "select * from mvc_board where id = " + strID;
//      return template.queryForObject(query, ParameterizedBeanPropertyRowMapper.newInstance(BDto.class));
        return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

    }

    public void modify(final int id, final String name, final String title, final String content) {

        String query = "update mvc_board set name = ?, title = ?, content = ? where id = ?";

        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setString(1, name);
                ps.setString(2, title);
                ps.setString(3, content);
                ps.setInt(4, id);
            }
        });

    }

    public void delete(final int id) {
        // TODO Auto-generated method stub
        String query = "delete from mvc_board where id = ?";

        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, id);
            }
        });

    }

    //   @SuppressWarnings("deprecation")
    public BDto reply_view(String str) {
        String query = "select * from mvc_board where id = " + str;
//      return template.queryForObject(query, ParameterizedBeanPropertyRowMapper.newInstance(BDto.class));
        return template.queryForObject(query, new BeanPropertyRowMapper<BDto>(BDto.class));

    }

    public void reply(final int id, final String name, final String title, final String content, final String _group, final String step, final String indent) {

        replyShape(_group, step);

        String query = "insert into mvc_board (id, name, title, content, _group, step, indent) values (nextval('mvc_board_seq'), ?, ?, ?, ?, ?, ?)";

        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                ps.setString(1, name);
                ps.setString(2, title);
                ps.setString(3, content);
                ps.setInt(4, Integer.parseInt(_group));
                ps.setInt(5, Integer.parseInt(step) + 1);
                ps.setInt(6, Integer.parseInt(indent) + 1);
            }
        });

    }

    private void replyShape( final String strGroup, final String strStep) {

        String query = "update mvc_board set step = step + 1 where _group = ? and step > ?";

        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                ps.setInt(1, Integer.parseInt(strGroup));
                ps.setInt(2, Integer.parseInt(strStep));
            }
        });
    }

    private void upHit(final int id) {
        String query = "update mvc_board set hit = hit + 1 where id = ?";
        this.template.update(query, new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                // TODO Auto-generated method stub
                ps.setInt(1, id);
            }
        });

    }
}