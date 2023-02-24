package com.sample.dao;

import com.sample.member.MemberDTO;
import java.util.ArrayList;

public interface MemberDao {

    public ArrayList<MemberDTO> list();
    public void join(String id, String pw, String mail, String name, int serial, int birth, String hobby, String intro, String address);
    public MemberDTO memberDetail(String id);
    public MemberDTO memberLogin(String id, String pw);
    public void delete(final String id);
    public void modify(String id, String pw, String mail, String name, int serial, int birth, String hobby, String intro, String address);
    public int idChk(String id);
}
