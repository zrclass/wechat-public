package org.zrclass.wechat.common.bean;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import org.zrclass.wechat.common.domain.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单按钮对象类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/10/14 - 20:37
 * <p>
 * Description:
 */
@Getter
public class Button implements Serializable {
    public static final Button HEAD = new Button(ButtonType.EMPTY, null, null);
    /**
     * 一级菜单数组，个数应为1~3个
     */
    private List<Button> button;
    /**
     * 菜单标题，不超过16个字节，子菜单不超过60个字节
     */
    private String name;
    /**
     * 菜单的响应动作类型，view表示网页类型，
     * click表示点击类型，miniprogram表示小程序类型
     */
    private String type;
    /**
     * 菜单KEY值，用于消息接口推送，不超过128字节
     */
    private String key;
    /**
     * 网页 链接，用户点击菜单可打开链接，不超过1024字节。
     * type为miniprogram时，不支持小程序的老版本客户端将打开本url。
     */
    private String url;
    /**
     * 调用新增永久素材接口返回的合法media_id
     */
    private String media_id;
    /**
     * 小程序的appid（仅认证公众号可配置）
     */
    private String appid;
    /**
     * 小程序的页面路径
     */
    private String pagepath;
    /**
     * 二级菜单数组，个数应为1~5个
     */
    private List<Button> sub_button;


    /**
     * 校验函数
     */
    private void verify(ButtonType typeEnum) {
        switch (typeEnum) {
            case CLICK:
                Assert.nonNull(key, "key");
                Assert.nonNull(name, "name");
                Assert.nonNull(type, "type");
                break;
            case VIEW:
                Assert.nonNull(url, "url");
                Assert.nonNull(name, "name");
                Assert.nonNull(type, "type");
                break;
            case MEDIA_ID:
            case VIEW_LIMITED:
                Assert.nonNull(media_id, "media_id");
                Assert.nonNull(name, "name");
                Assert.nonNull(type, "type");
                break;
            case MINIPROGRAM:
                Assert.nonNull(appid, "appid");
                Assert.nonNull(url, "url");
                Assert.nonNull(pagepath, "pagepath");
                Assert.nonNull(name, "name");
                Assert.nonNull(type, "type");
                break;
            case EMPTY:
                break;
        }
    }

    private Button(ButtonType typeEnum, String name, String param) {
        this.type = typeEnum.type();
        this.name = name;
        switch (typeEnum) {
            case PIC_SYSPHOTO:
            case PIC_PHOTO_OR_ALBUM:
            case LOCATION_SELECT:
            case SCANCODE_PUSH:
            case PIC_WEIXIN:
            case CLICK:
                this.key = param;
                break;
            case VIEW:
                this.url = param;
                break;
            case MEDIA_ID:
                this.media_id = param;
                break;
        }
        verify(typeEnum);
    }


    public static Button of(Button button, int parent, ButtonType typeEnum, String name, String param) {
        Button menu = new Button(ButtonType.EMPTY, null, null);
        menu.button = new ArrayList<>();
        Button newButton = new Button(typeEnum, name, param);
        if (button != null) {
            Button temp = button.button.get(parent);
            if (temp.sub_button == null) {
                temp.sub_button = new ArrayList<>();
            }
            temp.sub_button.add(newButton);
            return button;
        }
        menu.button.add(newButton);
        return menu;
    }

    public static Button ofOneMenu(Button button, ButtonType typeEnum, String name, String key) {
        Button menu = new Button(typeEnum, name, key);
        if (button.button == null) {
            button.button = new ArrayList<>();
        }
        button.button.add(menu);
        return button;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
    
}