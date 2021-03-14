package org.zrclass.wechat.common.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zrclass.wechat.common.constant.MessageType;
import org.zrclass.wechat.common.context.ContextMessageFactory;
import org.zrclass.wechat.web.service.message.EventMessageService;
import org.zrclass.wechat.web.service.message.ImageMessageService;
import org.zrclass.wechat.web.service.message.NewsMessageService;
import org.zrclass.wechat.web.service.message.TextMessageService;

/**
 * 消息配置类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/18 - 19:50
 * <p>
 * Description:
 */
@Configuration
public class MessageConfig {

    private ApplicationContext applicationContext;

    public MessageConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    public ContextMessageFactory getContextMessageFactory() {
        ContextMessageFactory contextMessageFactory = new ContextMessageFactory();
        contextMessageFactory.builder(MessageType.IMAGE, applicationContext.getBean(ImageMessageService.class))
                .builder(MessageType.TEXT, applicationContext.getBean(TextMessageService.class))
                .builder(MessageType.LINK, applicationContext.getBean(NewsMessageService.class))
                .builder(MessageType.EVENT, applicationContext.getBean(EventMessageService.class));
        return contextMessageFactory;
    }

}