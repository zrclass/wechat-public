package org.zrclass.wechat.web.service.message;

import org.springframework.stereotype.Service;
import org.zrclass.wechat.common.bean.message.Articles;
import org.zrclass.wechat.common.bean.message.NewsMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 20:05
 * <p>
 * Description:
 */
@Service
public class NewsMessageService implements IMessage {
    @Override
    public String handler(Map<String, Object> param) {
        List<Articles> articles = new ArrayList<>();
        Articles article = new Articles();
        article.setDescription("图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数");
        article.setTitle("图文消息信息");
        article.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/tViciceZib6VkhbkNKyhQfD6NKiaDKWIo7rfM3xrxDHG8ApBR3PIicicciccKMCteQNrRydX5tMKibIZa7qs4WtmE6cA1w/0");
        article.setUrl("www.baidu.com");
        Articles article2 = new Articles();
        article2.setDescription("图文消息信息，注意，如果图文数超过限制，则将只发限制内的条数");
        article2.setTitle("高清童话");
        article2.setPicUrl("http://mmbiz.qpic.cn/mmbiz_jpg/tViciceZib6VkhbkNKyhQfD6NKiaDKWIo7rfYQDShA83OZUmk8tIyiaLhSsBFSd8Q9HmgDfkhupeB0IibPvHUcHt3oyg/0");
        article2.setUrl("www.soso.com");
        articles.add(article2);
        articles.add(article);
        return NewsMessage.ofSend(param, articles).toXml();
    }
}