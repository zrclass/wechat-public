package org.zrclass.wechat.common.bean.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.zrclass.wechat.common.util.XmlUtils;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:57
 * <p>
 * Description:
 */
@XStreamAlias("xml")
@Data
public class NewsMessage extends BaseMessage {
    @XStreamAlias("ArticleCount")
    private String articleCount;
    @XStreamAlias("Articles")
    private List<Articles> articles;

    public static NewsMessage ofSend(Map<String, Object> param, List<Articles> articles) {
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.init(param);
        newsMessage.setArticleCount(String.valueOf(articles.size()));
        newsMessage.setArticles(articles);
        newsMessage.setMsgType("news");
        String from = newsMessage.getFromUserName();
        newsMessage.setFromUserName(newsMessage.getToUserName());
        newsMessage.setToUserName(from);
        return newsMessage;
    }

    public String toXml() {
        return XmlUtils.beanToXml(this, NewsMessage.class);
    }

}