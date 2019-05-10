package com.sunniwell.common.entity.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 14:58
 * @Description:
 */
@Data
@Document(collection = "equipment")
@CompoundIndexes({
        @CompoundIndex(name = "stats_sn_mac_vendor_model_activeTime", def = "{'stats': 1, 'sn': 1, 'mac': 1, 'vendor': 1, 'model': 1, 'activeTime': 1}")
})
//实现按在线状态/SN/MAC/厂商/型号/活跃时间段查询；
public class Equipment implements Serializable {
    @Id
    private String _id;
//    设备属性：厂商/型号/SN/MAC/软件版本/在线状态/注册时间/活跃时间/描述。

    private String vendor;//厂商
    private String model;//型号
    private String sn;//SN设备号
    private String mac;//也是号
    private String version;//软件版本
    private String stats;//在线状态
    private Date registrationTime;//注册时间
    private Date activeTime;//活跃时间
    private String desc;//描述

}
