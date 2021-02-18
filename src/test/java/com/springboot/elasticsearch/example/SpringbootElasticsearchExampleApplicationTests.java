package com.springboot.elasticsearch.example;

import com.springboot.elasticsearch.example.service.DocService;
import com.springboot.elasticsearch.example.service.IndexService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootElasticsearchExampleApplicationTests {

    @Autowired
    private IndexService indexService;
    @Autowired
    private DocService docService;

    @Test
    void testCreateIndex() {
        indexService.createIndex();
    }
    @Test
    void testDeleteIndex() {
        indexService.deleteIndex();
    }
    @Test
    void testAddDocument(){
        docService.addDocument();
    }
    @Test
    void testGetDocument(){
        docService.getDocument();
    }
    @Test
    void testUpdateDocument(){
        docService.updateDocument();
    }
    @Test
    void testDeleteDocument(){
        docService.deleteDocument();
    }

}
