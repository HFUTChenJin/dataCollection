package com.example.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("power_quality_realtime")
public class power_quality_realtime {
    private int id;
    private int datatime;
    private int deviceid;
    private String iaphd;
    private String ibphd;
    private String icphd;
    private String uaphd;
    private String ubphd;
    private String ucphd;

    private String frequencydeviation;
    private String uadeviation;
    private String ubdeviation;
    private String ucdeviation;

    private String uuabdeviation;
    private String uubcdeviation;
    private String uucadeviation;
    private String iunbalance;
    private String uunbalance;



}

/*
*
*   `id` int(11) NOT NULL AUTO_INCREMENT,
    `dataTime` int(11) NULL DEFAULT NULL,
    `deviceId` int(11) NULL DEFAULT NULL,
    *
    `iaPHD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'A相电流谐波畸变率',
    `ibPHD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `icPHD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `uaPHD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'A相电压谐波畸变率',
    `ubPHD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `ucPHD` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    *
    `frequencyDeviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '频率偏差',
    `uaDeviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `ubDeviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `ucDeviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    *
    `uUabDeviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'Uab线电压偏差',
    `uUbcDeviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `uUcaDeviation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    `iUnbalance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '三相电流不平衡度%',
    `uUnbalance` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
    *
    * */



