package org.zrclass.wechat.common.bean.message.template;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

import java.io.Serializable;

/**
 * 设置行业信息
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/9/8 - 11:24
 * <p>
 * Description:
 */
@Getter
public class BusinessBean implements Serializable {
    /**
     * 公众号模板消息所属行业编号
     */
    private final String industry_id1;
    /**
     * 公众号模板消息所属行业编号
     */
    private final String industry_id2;

    private BusinessBean(String industry_id1, String industry_id2) {
        this.industry_id1 = industry_id1;
        this.industry_id2 = industry_id2;
    }

    private BusinessBean(BusinessEnum businessEnum_id1, BusinessEnum businessEnum_id2) {
        this.industry_id1 = businessEnum_id1.getCode();
        this.industry_id2 = businessEnum_id2.getCode();
    }

    public static BusinessBean of(String industry_id1, String industry_id2) {
        return new BusinessBean(industry_id1, industry_id2);
    }

    public static BusinessBean of(BusinessEnum businessEnum_id1, BusinessEnum businessEnum_id2) {
        return new BusinessBean(businessEnum_id1, businessEnum_id2);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}