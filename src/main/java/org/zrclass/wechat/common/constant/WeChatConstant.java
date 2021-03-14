package org.zrclass.wechat.common.constant;

/**
 * 微信公众平台常量类
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * <p>
 * Description:
 */
public class WeChatConstant {
    public static final String CREATE_TIME = "CreateTime";
    public static final String CONTENT = "Content";
    public static final String FROM_USER_NAME = "FromUserName";
    public static final String TO_USER_NAME = "ToUserName";
    public static final String MSG_TYPE = "MsgType";
    public static final String MSG_ID = "MsgId";


    /**
     * 创建菜单URL
     */
    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    /**
     * 删除菜单URL
     */
    public static final String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";

    /**
     * 模版服务相关-设置所属行业
     */
    public static final String SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=ACCESS_TOKEN";
    /**
     * 获取所属行业信息
     */
    public static final String GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=ACCESS_TOKEN";
    /**
     * 获取所有模版信息
     */
    public static final String GET_ALL_PRIVATE_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=ACCESS_TOKEN";
    /**
     * 发送模版消息
     */
    public static final String TEMPLATE_SEND = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
    /**
     * 获取OAUTH认证的access_token
     */
    public static final String OAUTH_GET_AT = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /**
     * 获取用户基本信息
     */
    public static final String OAUTH_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 引导链接
     */
    public static final String OAUTH2_AUTHORIZE = "";
}