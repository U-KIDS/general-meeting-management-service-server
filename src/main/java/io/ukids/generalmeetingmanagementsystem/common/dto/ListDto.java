package io.ukids.generalmeetingmanagementsystem.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListDto<T> {
    List<T> data;
}
