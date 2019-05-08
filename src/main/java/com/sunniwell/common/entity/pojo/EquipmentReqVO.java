package com.sunniwell.common.entity.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 孟德坤
 * @Date: 2019/5/7 17:21
 * @Description: eq动态查询对象
 */
@Data
public class EquipmentReqVO implements Serializable {
//    分页数据
    private int page;
    private int size;
//    动态查询条件
//    实现按在线状态/SN/MAC/厂商/型号/活跃时间段查询；
    private String vendor;//厂商
    private String model;//型号
    private String sn;//SN设备号
    private String mac;//也是号
    private String stats;//在线状态
    private Date startTime;
    private Date endTime;
}
