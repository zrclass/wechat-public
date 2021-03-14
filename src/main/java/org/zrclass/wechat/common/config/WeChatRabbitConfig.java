package org.zrclass.wechat.common.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

/**
 * 类功能描述：<br>
 * Broker:它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输,
 * Exchange：消息交换机,它指定消息按什么规则,路由到哪个队列。
 * Queue:消息的载体,每个消息都会被投到一个或多个队列。
 * Binding:绑定，它的作用就是把exchange和queue按照路由规则绑定起来.
 * Routing Key:路由关键字,exchange根据这个关键字进行消息投递。
 * vhost:虚拟主机,一个broker里可以有多个vhost，用作不同用户的权限分离。
 * Producer:消息生产者,就是投递消息的程序.
 * Consumer:消息消费者,就是接受消息的程序.
 * Channel:消息通道,在客户端的每个连接里,可建立多个channel.
 * <ul>
 * <li>类功能描述1<br>
 * <li>类功能描述2<br>
 * <li>类功能描述3<br>
 * </ul>
 *
 * @author xuefl
 * @version 5.0 since 2020-01-02
 * @blame Android Team
 */
//@Configuration
public class WeChatRabbitConfig {

    @Resource
    private WeChatConfig chatConfig;

    /**
     * //创建队列
     * // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
     * // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
     * // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
     * //   return new Queue("TestDirectQueue",true,true,false);
     * // 一般设置一下队列的持久化就好,其余两个就是默认false
     *
     * @return
     */
    @Bean
    public Queue createDirectQueue() {
        return new Queue(chatConfig.getWeChatQueue(), true);
    }

    /**
     * 创建测试的Direct交换机
     * testTopicExchange
     *
     * @return
     */
    @Bean
    DirectExchange defDirectExchange() {
        return new DirectExchange(chatConfig.getWeChatExchange());
    }

    /**
     * 交换机于队列进行绑定
     *
     * @return
     */
    @Bean
    Binding bindingTopic() {
        return BindingBuilder.bind(createDirectQueue()).
                to(defDirectExchange()).
                with(chatConfig.getDirectKey());
    }


}