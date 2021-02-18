package com.springboot.elasticsearch.example.service;

import com.alibaba.fastjson.JSON;
import com.springboot.elasticsearch.example.model.entity.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName DocService
 * @Description: TODO 文档操作
 * @Author wys
 * @Date 2020/8/10-21:14
 * @Version V1.0
 **/
@Slf4j
@Service
public class DocService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 增加文档信息
     */
    public void addDocument() {
        try {
            // 创建索引请求对象
            IndexRequest indexRequest = new IndexRequest("mydlq-user");
            indexRequest.id("2");
            // 创建员工信息
            UserInfo userInfo = new UserInfo();
            userInfo.setName("李四");
            userInfo.setAge(29);
            userInfo.setSalary(100.00f);
            userInfo.setAddress("北京市");
            userInfo.setRemark("来自北京市的李先生");
            userInfo.setCreateTime(new Date());
            userInfo.setBirthDate("1990-02-10");
            // 将对象转换为 byte 数组
            byte[] json = JSON.toJSONBytes(userInfo);
            // 设置文档内容
            indexRequest.source(json, XContentType.JSON);
            // 执行增加文档
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            log.info("创建状态：{}", JSON.toJSON(response));
        } catch (Exception e) {
            log.error("增加文档信息", e);
        }
    }

    /**
     * 获取文档信息
     */
    public void getDocument() {
        try {
            // 获取请求对象
            GetRequest getRequest = new GetRequest("mydlq-user", "1");
            // 获取文档信息
            GetResponse getResponse = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
            // 将 JSON 转换成对象
            if (getResponse.isExists()) {
                log.info("文档信息：{}", JSON.toJSON(getResponse));
                UserInfo userInfo = JSON.parseObject(getResponse.getSourceAsBytes(), UserInfo.class);
                log.info("员工信息：{}", JSON.toJSON(userInfo));
            }
        } catch (IOException e) {
            log.error("获取文档信息", e);
        }
    }

    /**
     * 更新文档信息
     */
    public void updateDocument() {
        try {
            // 创建索引请求对象
            UpdateRequest updateRequest = new UpdateRequest("mydlq-user", "1");
            // 设置员工更新信息
            UserInfo userInfo = new UserInfo();
            userInfo.setSalary(200.00f);
            userInfo.setAddress("北京市海淀区");
            // 将对象转换为 byte 数组
            byte[] json = JSON.toJSONBytes(userInfo);
            // 设置更新文档内容
            updateRequest.doc(json, XContentType.JSON);
            // 执行更新文档
            UpdateResponse response = restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
            log.info("更新状态：{}", JSON.toJSON(response));
        } catch (Exception e) {
            log.error("更新文档信息", e);
        }
    }

    /**
     * 删除文档信息
     */
    public void deleteDocument() {
        try {
            // 创建删除请求对象
            DeleteRequest deleteRequest = new DeleteRequest("mydlq-user", "2");
            // 执行删除文档
            DeleteResponse response = restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
            log.info("删除状态：{}", JSON.toJSON(response));
        } catch (IOException e) {
            log.error("删除文档信息", e);
        }
    }
}
