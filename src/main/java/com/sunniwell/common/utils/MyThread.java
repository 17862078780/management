package com.sunniwell.common.utils;

import com.mongodb.MongoClient;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.dao.EquipmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/29 11:25
 * @Description:
 */
@Service
public class MyThread  {
    @Autowired
    private EquipmentDao equipmentDao;

    @Async
    public void saveAll() {
//        MongoClient localhost = new MongoClient("localhost");
//        MongoTemplate mongoTemplate = new MongoTemplate(localhost, "management");
        //开始时间
        Long begin = new Date().getTime();
        //插入数据
        List<Equipment> list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
//            System.out.println(i);
            Equipment e = new Equipment();
//            封装数据
//            厂商/型号/SN/MAC/软件版本/在线状态/注册时间/活跃时间/描述。
            e.setVendor("北京朝歌科技");
            e.setModel("one");
            e.setSn(UUID.randomUUID().toString());
            e.setMac(UUID.randomUUID().toString());
            e.setVersion("1.0");
            e.setStats("1");
            e.setRegistrationTime(new Date());
            e.setActiveTime(new Date());
            e.setDesc(
                    "朝歌科技是一家专业的融合视讯终端产品的设计与制造服务提供商, 公司主营IPTV机顶盒, 双模机顶盒等网络终端设备以及信息家电产品的研发, 生产和销售, 并致力于以创新并富有竞争力的产品和服务, 为全球运营商, 系统集成商, 行业应用客户提");
            list.add(e);
            if (i > 0 && (i + 1) % 1000 == 0) {
                equipmentDao.saveAll(list);
                list=new ArrayList<>();
                String name = Thread.currentThread().getName();
                System.out.println(name);
            }

        }
        // 结束时间
        Long end = new Date().getTime();
        // 耗时
        System.err.println("10万条数据插入花费时间 : " + (end - begin) / 1000 + " s" + "  插入完成");
    }
}