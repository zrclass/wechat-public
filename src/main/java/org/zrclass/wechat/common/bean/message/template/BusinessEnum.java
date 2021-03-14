package org.zrclass.wechat.common.bean.message.template;

/**
 * 模版消息-行业信息
 * Created by zhourui on 2020/9/4 22:31
 */
public enum BusinessEnum {

    Internet("1", "IT科技", "互联网/电子商务"),
    softwareAndServices("2", "IT科技", "IT软件与服务"),
    ItHardwareAndEquipment("3", "IT科技", "IT硬件与设备"),
    ElectronicTechnology("4", "IT科技", "电子技术"),
    CommunicationsAndOperators("5", "IT科技", "通信与运营商"),
    TheNetworkGame("6", "IT科技", "网络游戏"),
    bank("7", "金融业", "银行"),
    FundManagementTrust("8", "金融业", "基金理财信托"),
    insurance("9", "金融业", "保险"),
    FoodAndBeverage("10", "餐饮", "餐饮"),
    hotel("11", "酒店旅游", "酒店"),
    tourism("12", "酒店旅游", "旅游"),
    Courier("13", "运输与仓储", "快递"),
    logistics("14", "运输与仓储", "物流"),
    warehousing("15", "运输与仓储", "仓储"),
    training("16", "教育", "培训"),
    CollegesAndUniversities("17", "教育", "教育"),
    AcademicResearch("18", "政府与公共事业", "学术科研"),
    trafficPolice("19", "政府与公共事业", "交警"),
    museum("20", "政府与公共事业", "博物馆"),
    PublicUtilitiesNonProfitOrganizations("21", "政府与公共事业", "公共事业非盈利机构"),
    MedicalHealth("22", "医药护理", "医药医疗"),
    CareAndBeauty("23", "医药护理", "护理美容"),
    HealthAndHygiene("24", "医药护理", "保健与卫生"),
    AutomotiveRelated("25", "交通工具", "汽车相关"),
    MotorcycleCorrelation("26", "交通工具", "摩托车相关"),
    trainRelated("27", "交通工具", "火车相关"),
    planeRelated("28", "交通工具", "飞机相关"),
    building("29", "房地产", "建筑"),
    property("30", "房地产", "物业"),
    ConsumerGoods("31", "消费品", "消费品"),
    law("32", "商业服务", "法律"),
    convention("33", "商业服务", "会展"),
    IntermediaryService("34", "商业服务", "中介服务"),
    certification("35", "商业服务", "认证"),
    audit("36", "商业服务", "审计"),
    media("37", "文体娱乐", "传媒"),
    sports("38", "文体娱乐", "体育"),
    EntertainmentAndLeisure("39", "文体娱乐", "娱乐休闲"),
    printing("40", "印刷", "印刷"),
    other("41", "其他", "其他"),
    ;

    /**
     * 主行业
     */
    private String mainIndustry;
    /**
     * 副行业
     */
    private String viceIndustry;
    /**
     * 代码
     */
    private String code;

    BusinessEnum(String code, String mainIndustry, String viceIndustry) {
        this.code = code;
        this.mainIndustry = mainIndustry;
        this.viceIndustry = viceIndustry;
    }

    public String getMainIndustry() {
        return this.mainIndustry;
    }

    public String getViceIndustry() {
        return this.viceIndustry;
    }

    public String getCode() {
        return this.code;
    }

    public BusinessEnum getBusiness(String code) {
        for (BusinessEnum businessEnum : BusinessEnum.values()) {
            if (businessEnum.code.equalsIgnoreCase(code)) {
                return businessEnum;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "BusinessEnum{" +
                "mainIndustry='" + mainIndustry + '\'' +
                ", viceIndustry='" + viceIndustry + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
