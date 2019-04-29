package com.sunniwell;

import com.mongodb.MongoClient;
import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.common.utils.IdWorker;
import com.sunniwell.dao.EquipmentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @Auther: 孟德坤
 * @Date: 2019/4/28 16:33
 * @Description:模拟插入100万条设备数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAdd {
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private EquipmentDao equipmentDao;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void saveAll(){
       List<Equipment> list = new ArrayList<>();
        for(int i=0;i<100000;i++){
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
            if(i > 0 && (i+1) % 1000==0){
               equipmentDao.saveAll((Iterable)list);
               list = new ArrayList<Equipment>();
            }

    }
    }
//    删除全部
    @Test
    public void del(){
        equipmentDao.deleteAll();
    }


}
