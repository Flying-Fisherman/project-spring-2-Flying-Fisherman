package com.codesoom.scheduleMaker.dto;

import com.github.dozermapper.core.Mapping;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Schedule DTO
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleData {

    /**
     * Schedule 제목
     */
    @NotBlank
    @Mapping("title")
    private String title;

    /**
     * Schedule 내용
     */
    @NotBlank
    @Mapping("content")
    private String content;

    /**
     * Schedule 작성자 ID
     */
    @Mapping("userId")
    private Long userId;
}
