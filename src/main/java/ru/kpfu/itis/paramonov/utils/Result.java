package ru.kpfu.itis.paramonov.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Result<T> {
    private String result;

    private T entity;
}
