package org.zrclass.wechat.common.util;

import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * XML对象处理类
 *
 * @auther: zhourui
 *
 * @date: 2020-08-30 17:31
 * @Description:
 */
public class XmlUtils {

    /**
     * 将xml格式的字符串转换成Map对象
     *
     * @param xmlStr xml格式的字符串
     * @return Map对象
     * @throws Exception 异常
     */
    public static Map<String, Object> xmlStrToMap(String xmlStr) throws Exception {
        if (StringUtils.isEmpty(xmlStr)) {
            return null;
        }
        Map<String, Object> map = new HashMap<>(16);
        //将xml格式的字符串转换成Document对象
        Document doc = DocumentHelper.parseText(xmlStr);
        //获取根节点
        Element root = doc.getRootElement();
        //获取根节点下的所有元素
        List children = root.elements();
        //循环所有子元素
        if (children != null && children.size() > 0) {
            for (Object o : children) {
                Element child = (Element) o;
                map.put(child.getName(), child.getTextTrim());
            }
        }
        return map;
    }

    public static String beanToXml(Object object, Class cls) {
        XStream stream = new XStream();
        stream.processAnnotations(cls);
        return stream.toXML(object);
    }

}
