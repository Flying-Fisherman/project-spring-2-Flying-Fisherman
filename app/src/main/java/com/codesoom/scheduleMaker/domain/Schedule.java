package com.codesoom.scheduleMaker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Schedule 정보
 */
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {

    /**
     * Schedule 고유 ID값
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * Schedule 제목
     */
    private String title;

    /**
     * Schedule 내용
     */
    private String content;

    // ToDo : 스케쥴의 예정시간 등을 추가

    /**
     * Schedule 작성자 ID
     */
    private Long userId;

    /**
     * Schedule 정보를 변경합니다.
     *
     * @param source 변경할 Schedule 정보
     */
    public void updataData(Schedule source) {
        this.title = source.getTitle();
        this.content = source.getContent();
    }
}
