package com.sunniwell;

import com.sunniwell.common.entity.pojo.Equipment;
import com.sunniwell.dao.EquipmentDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Auther: 孟德坤
 * @Date: 2019/5/7 17:11
 * @Description: 测试dataMongo的动态分页查询
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestExampleMatcher {
    @Autowired
    private EquipmentDao equipmentDao;
    @Test
    public void test01(){
        /*Spublic Page<Student> getListWithExampleAndCriteria(StudentReqVO studentReqVO) {
            Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
            Pageable pageable = PageRequest.of(studentReqVO.getPageNum(), studentReqVO.getPageSize(), sort);

            Student student = new Student();
            BeanUtils.copyProperties(studentReqVO, student);

            //创建匹配器，即如何使用查询条件
            ExampleMatcher matcher = ExampleMatcher.matching() //构建对象
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                    .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                    .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains()) //标题采用“包含匹配”的方式查询
                    .withIgnorePaths("pageNum", "pageSize");  //忽略属性，不参与查询

            //创建实例
            Example<Student> example = Example.of(student, matcher);
            Query query = new Query(Criteria.byExample(example));
            if (studentReqVO.getCreateTime() != null){
                query.addCriteria(Criteria.where("createTime").lte(studentReqVO.getCreateTime()));
            }

            //计算总数
            long total = mongoTemplate.count(query, Student.class);

            //查询结果集
            List<Student> studentList = mongoTemplate.find(query.with(pageable), Student.class);
            Page<Student> studentPage = new PageImpl(studentList, pageable, total);
            return studentPage;
        }*/
    }
}
