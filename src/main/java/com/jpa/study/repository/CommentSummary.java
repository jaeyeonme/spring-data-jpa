package com.jpa.study.repository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentSummary {

    private String comment;
    private int up;
    private int down;

    public String getVotes() {
        return this.up + " " + this.down;
    }
}
