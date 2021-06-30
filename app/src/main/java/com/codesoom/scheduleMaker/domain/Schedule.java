package com.codesoom.scheduleMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    public void updataData(Schedule source) {
        this.id = source.getId();
        this.title = source.getTitle();
        this.content = source.getContent();
    }
}
