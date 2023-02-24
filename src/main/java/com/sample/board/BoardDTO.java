package com.sample.board;

import lombok.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDTO {

    private int board_num, board_re_ref, board_re_lev, board_re_seq, board_readcount;
    private String board_name, board_pass, board_subject, board_content, board_file;
    private Date board_date;


}
