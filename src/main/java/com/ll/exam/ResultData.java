package com.ll.exam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultData<T> {

    private final String msg;
    private String resultCode;
    private T data;
}
