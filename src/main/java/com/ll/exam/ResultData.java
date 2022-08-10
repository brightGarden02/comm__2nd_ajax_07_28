package com.ll.exam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultData<T> {

    private String resultCode;
    private final String msg;
    private T data;
}
