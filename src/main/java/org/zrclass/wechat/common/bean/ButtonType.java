package org.zrclass.wechat.common.bean;


/**
 * 自定义菜单类型枚举类
 *
 * @auther: zhourui
 * @blame: zhourui
 * @date: 2020-08-30 16:45
 * @Description:
 */
public enum ButtonType {
    /**
     * 点击事件
     */
    CLICK("click"),
    /**
     * 跳转URL用户点击view类型按钮后，
     * 微信客户端将会打开开发者在按钮中填写的网页URL，
     * 可与网页授权获取用户基本信息接口结合，获得用户基本信息
     */
    VIEW("view"),
    /**
     * 小程序类型
     */
    MINIPROGRAM("miniprogram"),
    /**
     * 扫码推事件用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），
     * 且会将扫码的结果传给开发者，开发者可以下发消息
     */
    SCANCODE_PUSH("scancode_push"),
    /**
     * 扫码推事件且弹出“消息接收中”提示框用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，
     * 将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息
     */
    SCANCODE_WAITMSG("scancode_waitmsg"),
    /**
     * 弹出系统拍照发图用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，
     * 会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息
     */
    PIC_SYSPHOTO("pic_sysphoto"),
    /**
     * 弹出拍照或者相册发图用户点击按钮后，
     * 微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。
     * 用户选择后即走其他两种流程
     */
    PIC_PHOTO_OR_ALBUM("pic_photo_or_album"),
    /**
     * 弹出微信相册发图器用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，
     * 将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息
     */
    PIC_WEIXIN("pic_weixin"),
    /**
     * 弹出地理位置选择器用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，
     * 将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
     */
    LOCATION_SELECT("location_select"),
    /**
     * 下发消息（除文本消息）用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，
     * 永久素材类型可以是图片、音频、视频、图文消息。
     * 请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。
     */
    MEDIA_ID("media_id"),
    /**
     * 跳转图文消息URL用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，
     * 永久素材类型只支持图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id
     */
    VIEW_LIMITED("view_limited"),
    /**
     * 空结点类型
     */
    EMPTY(null);
    /**
     * type值
     */
    private final String type;

    ButtonType(String type) {
        this.type = type;
    }

    /**
     * 获取type值
     *
     * @return
     */
    public String type() {
        return this.type;
    }

}
