package com.sample.db;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BDto {

    private int id;
    private String name;
    private String title;
    private String content;
    private Timestamp date;
    private int hit;
    private int _group;
    private int step;
    private int indent;


}
