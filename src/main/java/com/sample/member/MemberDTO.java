package com.sample.member;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MemberDTO {
    private String id;
    private String pw;
    private String mail;
    private String name;
    private Integer serial;
    private Integer birth;
    private String hobby;
    private String intro;
    private String address;
}
