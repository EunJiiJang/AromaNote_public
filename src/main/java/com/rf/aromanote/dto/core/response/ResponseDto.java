package com.rf.aromanote.dto.core.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {
    private String msg;
    private Object data;
}
