package org.zrclass.wechat.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.zrclass.wechat.common.constant.MessageType;
import org.zrclass.wechat.common.context.ContextMessageFactory;
import org.zrclass.wechat.common.util.AccessAuthentication;
import org.zrclass.wechat.common.util.XmlUtils;

import java.util.Map;

/**
 * @author zhourui 20114535
 * @version 1.0
 * @date 2021/3/13 19:58
 */
@RestController
@RequestMapping("/v1/wechat")
public class WechatPublicController {

    @Value("${wechat.token}")
    private String token;

    @Autowired
    private ContextMessageFactory contextMessageFactory;

    @GetMapping("/public")
    public String getWechat(@RequestParam String signature,
                            @RequestParam String timestamp,
                            @RequestParam String nonce,
                            @RequestParam String echostr) {
        System.out.println("signature :" + signature);
        System.out.println("timestamp :" + timestamp);
        System.out.println("nonce :" + nonce);
        System.out.println("echostr :" + echostr);
        if (AccessAuthentication.of(token, timestamp, nonce, signature).checkSignature()) {
            return echostr;
        }
        return null;
    }


    @PostMapping("/public")
    public String post(@RequestBody String message) throws Exception {
        System.out.println("接收到微信公众平台消息：" + message);
        Map<String, Object> stringObjectMap = XmlUtils.xmlStrToMap(message);
        MessageType msgType = MessageType.getType(stringObjectMap.get("MsgType").toString());
        return contextMessageFactory.doAction(msgType, stringObjectMap);
    }




    @GetMapping("/test")
    public String test(){
        return "success";
    }

}
