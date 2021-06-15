package com.example.demo.threadPoolExceutor.dao;

import com.example.demo.threadPoolExceutor.bean.CommandSend;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

/**
 * @author : pp
 * @date : Created in 2020/7/7 11:09
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DelayMessionDao {
    private final MongoTemplate mongoTemplate;

    /**
     * 存在就更新，不存在就插入
     *
     * @param bean
     */
    public void upsert(CommandSend bean) {
        // 查询条件，如果数据存在更新
        Query query = new Query();
        query.addCriteria(Criteria.where("sn").is(bean.getSn()));


        // 更新的字段
        Update update = new Update();
        update.set("buildId", bean.getBuildId());

        mongoTemplate.upsert(query, update, CommandSend.class, "command_send");
    }

}
