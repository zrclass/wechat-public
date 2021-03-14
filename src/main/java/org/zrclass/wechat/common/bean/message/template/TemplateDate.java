package org.zrclass.wechat.common.bean.message.template;

import lombok.Getter;

import java.io.Serializable;
import java.util.HashMap;

/**
 * 模版数据
 * Created with IntelliJ IDEA.
 * User:  zhourui
 * Date:  2020/9/10 - 11:31
 * <p>
 * Description:
 */
@Getter
public class TemplateDate implements Serializable {
    private final HashMap<String, String> first;
    private final HashMap<String, String> keyword1;
    private final HashMap<String, String> keyword2;
    private final HashMap<String, String> keyword3;
    private final HashMap<String, String> keyword4;
    private final HashMap<String, String> keyword5;
    private final HashMap<String, String> keyword6;
    private final HashMap<String, String> keyword7;
    private final HashMap<String, String> keyword8;
    private final HashMap<String, String> keyword9;
    private final HashMap<String, String> remark;


    private TemplateDate(TemplateDateBuilder templateDateBuilder) {
        this.first = templateDateBuilder.first;
        this.keyword1 = templateDateBuilder.keyword1;
        this.keyword2 = templateDateBuilder.keyword2;
        this.keyword3 = templateDateBuilder.keyword3;
        this.keyword4 = templateDateBuilder.keyword4;
        this.keyword5 = templateDateBuilder.keyword5;
        this.keyword6 = templateDateBuilder.keyword6;
        this.keyword7 = templateDateBuilder.keyword7;
        this.keyword8 = templateDateBuilder.keyword8;
        this.keyword9 = templateDateBuilder.keyword9;
        this.remark = templateDateBuilder.remark;
    }

    public static TemplateDateBuilder builder() {
        return new TemplateDateBuilder();
    }


    public static class TemplateDateBuilder {
        private HashMap<String, String> first;
        private HashMap<String, String> keyword1;
        private HashMap<String, String> keyword2;
        private HashMap<String, String> keyword3;
        private HashMap<String, String> keyword4;
        private HashMap<String, String> keyword5;
        private HashMap<String, String> keyword6;
        private HashMap<String, String> keyword7;
        private HashMap<String, String> keyword8;
        private HashMap<String, String> keyword9;
        private HashMap<String, String> remark;

        public TemplateDate build() {
            return new TemplateDate(this);
        }

        public TemplateDateBuilder first(HashMap<String, String> first) {
            this.first = first;
            return this;
        }

        public TemplateDateBuilder first(String first) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", first);
            map.put("color", "#B22222");
            this.first = map;
            return this;
        }

        public TemplateDateBuilder keyword1(HashMap<String, String> keyword1) {
            this.keyword1 = keyword1;
            return this;
        }

        public TemplateDateBuilder keyword1(String keyword1) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", keyword1);
            this.keyword1 = map;
            return this;
        }

        public TemplateDateBuilder keyword2(HashMap<String, String> keyword2) {
            this.keyword2 = keyword2;
            return this;
        }

        public TemplateDateBuilder keyword2(String keyword2) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", keyword2);
            this.keyword2 = map;
            return this;
        }

        public TemplateDateBuilder keyword3(HashMap<String, String> map) {
            this.keyword3 = map;
            return this;
        }

        public TemplateDateBuilder keyword3(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            this.keyword3 = map;
            return this;
        }

        public TemplateDateBuilder keyword4(HashMap<String, String> map) {
            this.keyword4 = map;
            return this;
        }

        public TemplateDateBuilder keyword4(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            this.keyword4 = map;
            return this;
        }

        public TemplateDateBuilder keyword5(HashMap<String, String> map) {
            this.keyword5 = map;
            return this;
        }

        public TemplateDateBuilder keyword5(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            this.keyword5 = map;
            return this;
        }

        public TemplateDateBuilder keyword6(HashMap<String, String> map) {
            this.keyword6 = map;
            return this;
        }

        public TemplateDateBuilder keyword6(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            this.keyword6 = map;
            return this;
        }

        public TemplateDateBuilder keyword7(HashMap<String, String> map) {
            this.keyword7 = map;
            return this;
        }

        public TemplateDateBuilder keyword7(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            this.keyword7 = map;
            return this;
        }

        public TemplateDateBuilder keyword8(HashMap<String, String> map) {
            this.keyword8 = map;
            return this;
        }

        public TemplateDateBuilder keyword8(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            this.keyword8 = map;
            return this;
        }

        public TemplateDateBuilder keyword9(HashMap<String, String> map) {
            this.keyword9 = map;
            return this;
        }

        public TemplateDateBuilder keyword9(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            this.keyword9 = map;
            return this;
        }

        public TemplateDateBuilder remark(String remark) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", remark);
            this.remark = map;
            return this;
        }

        public TemplateDateBuilder remark(HashMap<String, String> remark) {
            this.remark = remark;
            return this;
        }

        @SuppressWarnings("all")
        public TemplateDateBuilder keys(String value) {
            HashMap<String, String> map = new HashMap<>();
            map.put("value", value);
            map.put("color", "#173177");
            if (this.keyword1 == null) {
                this.keyword1 = map;
                return this;
            }
            if (this.keyword2 == null) {
                this.keyword2 = map;
                return this;
            }
            if (this.keyword3 == null) {
                this.keyword3 = map;
                return this;
            }
            if (this.keyword4 == null) {
                this.keyword4 = map;
                return this;
            }
            if (this.keyword5 == null) {
                this.keyword5 = map;
                return this;
            }
            if (this.keyword6 == null) {
                this.keyword6 = map;
                return this;
            }
            if (this.keyword7 == null) {
                this.keyword7 = map;
                return this;
            }
            if (this.keyword8 == null) {
                this.keyword8 = map;
                return this;
            }
            if (this.keyword9 == null) {
                this.keyword9 = map;
                return this;
            }
            return this;
        }
    }

    /**
     * 两个key
     *
     * @param first  第一个内容
     * @param remark 备注内容
     * @return
     */
    public static TemplateDate ofDefault(String first, String remark) {
        HashMap<String, String> firstMap = new HashMap<>();
        firstMap.put("value", first);
        HashMap<String, String> remarkMap = new HashMap<>();
        remarkMap.put("value", remark);
        return TemplateDate.builder().first(firstMap).remark(remarkMap).build();
    }


    public static TemplateDate of(String... args) {
        TemplateDateBuilder builder = TemplateDate.builder();
        builder.first(args[0]);
        for (int i = 1; i < args.length - 1; i++) {
            builder.keys(args[i]);
        }
        builder.remark(args[args.length - 1]);
        return builder.build();
    }


}