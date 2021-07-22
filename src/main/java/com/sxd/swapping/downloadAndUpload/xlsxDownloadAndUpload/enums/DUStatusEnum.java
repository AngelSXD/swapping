package com.sxd.swapping.downloadAndUpload.xlsxDownloadAndUpload.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
public enum DUStatusEnum {



    WAIT(0,"等待处理"),

    SUCCESS(1,"成功"),

    FAIL(-1, "失败")
    ;

    /**值*/
    private Integer value;

    /**报表描述*/
    private String desc;


    @JsonCreator
    public static DUStatusEnum valueOf(Integer value) {
        return Arrays.stream(DUStatusEnum.values())
                .filter(e -> Objects.equals(e.value, value)).findFirst()
                .orElseThrow(() -> new RuntimeException("DUStatusEnum value=" + value + " not exists!"));
    }

}
