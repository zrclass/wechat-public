package org.zrclass.wechat.common.domain;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 业务异常断言工具类，初始化必须使用 build 来初始化
 * <p>
 * 工具的方法均为 静态方法，检查失败，抛出  异常。
 * </p>
 * <p>S
 * 所有的检查方法都会有一个基本方法 xxx(xx, errorCode, cause, args)
 * 和一个重载方法 xxx(xx, errorCode, cause, args)，其中基本方法支持 cause ，重载方法是no cause 模式。
 * </p>
 *

 */
public class Assert {


    /**
     * 不能为空检查
     *
     * @param object    异常对象
     * @param errorCode 异常码 枚举
     * @param args      消息参数
     */
    public static <T extends Enum<T> & ErrorCode> void nonNull(Object object, T errorCode, Object... args) {
        nonNull(object, errorCode, BusinessException.CAUSE_NOOP, args);
    }


    /**
     * 对象不为null检查
     *
     * @param object    要检查的对象
     * @param paramName 参数
     *
     */
    public static void nonNull(Object object, String paramName) {
        nonNull(object, CommonErrorCode.ARGUMENT_NONNULL, BusinessException.CAUSE_NOOP, paramName);
    }

    /**
     * 不能为空检查
     *
     * @param object    异常对象
     * @param errorCode 异常码 枚举
     * @param cause     异常源
     * @param args      消息参数
     */
    public static <T extends Enum<T> & ErrorCode> void nonNull(Object object, T errorCode, Throwable cause, Object... args) {
        assert errorCode != null;
        if (null == object) {
            throw BusinessException.of(errorCode, args, cause);
        }
    }

    /**
     * 抛出位置异常
     *
     * @param message 异常消息
     * @param ex      异常
     * @return 异常对象
     */
    public static BusinessException throwableUnknown(String message, Throwable ex) {
        if (ex instanceof BusinessException) {
            return (BusinessException) ex;
        }
        return BusinessException.of(CommonErrorCode.UNKNOWN, ex, message);
    }


    /**
     * S
     * 是否为true检查，对于 false 的检查也是用此方法，!value，来使用
     *
     * @param state     状态值，state != true 则抛出异常
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode> void isTrue(boolean state, T errorCode, Object... args) {
        isTrue(state, errorCode, BusinessException.CAUSE_NOOP, args);
    }


    /**
     * 是否为true检查，对于 false 的检查也是用此方法，!value，来使用
     *
     * @param state     状态值，state != true 则抛出异常
     * @param errorCode 异常码
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode> void isTrue(boolean state, T errorCode, Throwable cause, Object... args) {
        assert null != errorCode;

        if (!state) {
            throw BusinessException.of(errorCode, args, cause);
        }
    }


    /**
     * object 对象是否实例化自  clazz
     *
     * @param object    检查的对象
     * @param clazz     源于类
     * @param paramName 参数名称
     * @param <T>       类型
     * @param <M>       数据类型
     * @see CommonErrorCode#ARGUMENT_CAST_ERROR
     */
    public static <T extends Enum<T> & ErrorCode, M> void isAssignableFrom( Object object, Class<M> clazz, String paramName) {
        assert null != clazz;

        if (object == null) {
            return;
        }
        isAssignableFrom(object, clazz, CommonErrorCode.ARGUMENT_CAST_ERROR, paramName, object.getClass().getName(), clazz.getName());
    }

    /**
     * object 对象是否实例化自  clazz
     *
     * @param object    检查的对象
     * @param clazz     源于类
     * @param errorCode 错误码
     * @param args      参数
     * @param <T>       类型
     * @param <M>       数据类型
     */
    public static <T extends Enum<T> & ErrorCode, M> void isAssignableFrom(Object object, Class<M> clazz, T errorCode, Object... args) {

        assert null != errorCode;
        assert null != clazz;

        if (object == null) {
            return;
        }
        // 检查是否 instance of
        if (!clazz.isAssignableFrom(object.getClass())) {
            throw BusinessException.of(errorCode, args);
        }
    }

    /**
     * 对象不为空检查，支持 对象、字符串、数组和集合，如果是高级对象或者包装器对象，
     * 则检查 null == object, 如果是字符串则检查 "".equals(object)
     *
     * @param object    要检查的对象
     * @param paramName 参数
     */
    public static void nonEmpty(Object object, String paramName) {
        nonEmpty(object, CommonErrorCode.ARGUMENT_NONEMPTY, BusinessException.CAUSE_NOOP, paramName);
    }

    /**
     * 对象不为空检查，支持 对象、字符串、数组和集合，如果是高级对象或者包装器对象，则检查 null == object, 如果是字符串则检查 "".equals(object)
     *
     * @param object    要检查的对象
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode> void nonEmpty(Object object, T errorCode, Object... args) {
        nonEmpty(object, errorCode, BusinessException.CAUSE_NOOP, args);
    }

    /**
     * 对象为空检查，如果是高级对象或者包装器对象，则检查 null == object, 如果是字符串则检查 "".equals(object)，如果是 数组 则 object.length == 0 或 object.size() == 0
     *
     * @param object    要检查的对象
     * @param errorCode 异常码
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode> void nonEmpty(Object object, T errorCode, Throwable cause, Object... args) {
        // 检查 null 和字符串
        if (null == object || "".equals(object)) {
            throw BusinessException.of(errorCode, args, cause);
        }
        // 检查 集合
        if (object instanceof Collection) {
            if (((Collection) object).isEmpty()) {
                throw BusinessException.of(errorCode, args, cause);
            }
        }
        // 检查数组
        else if (object.getClass().isArray()) {
            // 长度为空 抛出异常
            if (Array.getLength(object) == 0) {
                throw BusinessException.of(errorCode, args, cause);
            }
        }
    }

    /**
     * 字符串不能为空
     *
     * @param object    字符串
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>
     */
    public static <T extends Enum<T> & ErrorCode> void nonBlank(String object, T errorCode, Object... args) {
        if (null == object || "".equals(object.trim())) {
            throw BusinessException.of(errorCode, args);
        }
    }

    /**
     * 字符串不能为空
     *
     * @param object 字符串
     * @param name   参数
     */
    public static void nonBlank(String object, String name) {
        if (null == object || "".equals(object.trim())) {
            throw BusinessException.of(CommonErrorCode.ARGUMENT_NONEMPTY, name);
        }
    }


    /**
     * 断言数组中没有空元素，底层调用的是 {@link #nonEmpty(Object, Enum, Throwable, Object...)} 方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonEmptyElements(K[] objects, T errorCode, Throwable cause, Object... args) {
        // 为空检查
        nonNull(objects, errorCode, cause, args);
        // 逐个元素检查
        for (K obj : objects) {
            nonEmpty(obj, errorCode, cause, args);
        }
    }

    /**
     * 断言数组中没有空元素，底层调用的是 {@link #nonEmpty(Object, Enum, Throwable, Object...)} 方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonEmptyElements(K[] objects, T errorCode, Object... args) {
        nonEmptyElements(objects, errorCode, BusinessException.CAUSE_NOOP, args);
    }

    /**
     * 断言数组中没有null元素，底层调用的是 {@link #nonNull(Object, Enum, Throwable, Object...)}方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonNullElements(K[] objects, T errorCode, Throwable cause, Object... args) {
        // 为空检查
        nonNull(objects, errorCode, cause, args);
        // 逐个元素检查
        for (K obj : objects) {
            nonNull(obj, errorCode, cause, args);
        }
    }

    /**
     * 断言数组中没有null元素，底层调用的是 {@link #nonNull(Object, Enum, Throwable, Object...)}方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonNullElements(K[] objects, T errorCode, Object... args) {
        nonNullElements(objects, errorCode, BusinessException.CAUSE_NOOP, args);
    }


    /**
     * 断言集合中没有空元素，底层调用的是 {@link #nonEmpty(Object, Enum, Throwable, Object...)} 方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonEmptyElements(Collection<K> objects, T errorCode, Throwable cause, Object... args) {
        // 为空检查
        nonNull(objects, errorCode, cause, args);
        // 逐个元素检查
        for (K obj : objects) {
            nonEmpty(obj, errorCode, cause, args);
        }
    }

    /**
     * 断言集合中没有空元素，底层调用的是 {@link #nonEmpty(Object, Enum, Throwable, Object...)} 方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonEmptyElements(Collection<K> objects, T errorCode, Object... args) {
        nonEmptyElements(objects, errorCode, BusinessException.CAUSE_NOOP, args);
    }

    /**
     * 断言集合中没有null元素，底层调用的是 {@link #nonNull(Object, Enum, Throwable, Object...)}方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonNullElements(Collection<K> objects, T errorCode, Throwable cause, Object... args) {
        // 为空检查
        nonNull(objects, errorCode, cause, args);
        // 逐个元素检查
        for (K obj : objects) {
            nonNull(obj, errorCode, cause, args);
        }
    }

    /**
     * 断言集合中没有null元素，底层调用的是 {@link #nonNull(Object, Enum, Throwable, Object...)}方法检查每个元素
     *
     * @param objects   检查的数组对象
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       类型
     */
    public static <T extends Enum<T> & ErrorCode, K> void nonNullElements(Collection<K> objects, T errorCode, Object... args) {
        nonNullElements(objects, errorCode, BusinessException.CAUSE_NOOP, args);
    }


    /**
     * 断言 elt 元素包含在 list 中，这里使用的是 equales 比较
     *
     * @param elt       检查的元素
     * @param list      检查的列表，比如不能为空
     * @param errorCode 异常code
     * @param args      参数
     * @param <T>       异常类型
     * @param <M>       检查的枚举类型
     */
    public static <T extends Enum<T> & ErrorCode, M> void contains(M elt, Collection<M> list, T errorCode, Object... args) {
        contains(elt, list, errorCode, BusinessException.CAUSE_NOOP, args);
    }


    /**
     * 断言 elt 元素包含在 list 中，这里使用的是 equales 比较
     *
     * @param elt       检查的元素
     * @param list      检查的列表，比如不能为空
     * @param errorCode 异常code
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       异常类型
     * @param <M>       检查的枚举类型
     */
    public static <T extends Enum<T> & ErrorCode, M> void contains(M elt, Collection<M> list, T errorCode, Throwable cause, Object... args) {
        // 检查对象
        assert null != list;

        nonNull(elt, errorCode, cause, args);
        // 检查
        // 是否包含
        for (M object : list) {
            // 如果有一个相等，则退出
            if (elt.equals(object)) {
                return;
            }
        }
        throw BusinessException.of(errorCode, args, cause);
    }


    /**
     * 参数是否是 hex color 色值，#000000 必须7位
     *
     * @param color     颜色值
     * @param paramName 参数名
     */
    public static void isHexColor(String color, String paramName) {
        isHexColor(color, CommonErrorCode.ARGUMENT_FORMAT_ERROR, paramName, color, "#000000");
    }

    /**
     * 参数是否是 hex color 色值，#000000 必须7位
     *
     * @param color     颜色色值，7位
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       异常码类型
     */
    public static <T extends Enum<T> & ErrorCode> void isHexColor(String color, T errorCode, Object... args) {
        // 空检查
        nonEmpty(color, errorCode, args);
        // 检查值类型
        isTrue(HEX_COLOR_REGX.matcher(color).matches(), errorCode, args);
    }

    /**
     * 检查元素是否被包含在数组中
     *
     * @param elt       检查的元素
     * @param list      数组
     * @param errorCode 异常 code
     * @param args      参数
     * @param <T>       code类型
     * @param <M>       元素类型
     */
    public static <T extends Enum<T> & ErrorCode, M> void contains(M elt, M[] list, T errorCode, Object... args) {
        contains(elt, list, errorCode, BusinessException.CAUSE_NOOP, args);
    }

    /**
     * 检查元素是否被包含在数组中
     *
     * @param elt       检查的元素
     * @param list      数组
     * @param errorCode 异常 code
     * @param cause     异常原因
     * @param args      参数
     * @param <T>       code类型
     * @param <M>       元素类型
     */
    public static <T extends Enum<T> & ErrorCode, M> void contains(M elt, M[] list, T errorCode, Throwable cause, Object... args) {
        assert null != list;

        nonNull(elt, errorCode, cause, args);
        // 是否包含
        for (M object : list) {
            // 如果有一个相等，则退出
            if (elt.equals(object)) {
                return;
            }
        }
        throw BusinessException.of(errorCode, args, cause);
    }

    /**
     * 便利 LIST 集合
     *
     * @param list             列表
     * @param iteratorFunction 便利的函数
     * @param <T>              数据类型
     */
    public static  <T> void iterator(final List<T> list, ForEachGetIndex<T> iteratorFunction) {
        assert null != iteratorFunction;

        int l = list.size();
        for (int i = 0; i < l; i++) {
            iteratorFunction.apply(i, list.get(i));
        }
    }

    /**
     * 便利数组
     *
     * @param list             数组
     * @param iteratorFunction 函数
     * @param <T>              类型
     */
    public static <T> void iterator(final T[] list, ForEachGetIndex<T> iteratorFunction) {
        assert null != iteratorFunction;
        for (int i = 0; i < list.length; i++) {
            iteratorFunction.apply(i, list[i]);
        }
    }

    /**
     * 是否小于等于 <=
     *
     * @param value     检查的值
     * @param max       最大值
     * @param paramName 参数名
     */
    public static void le(int value, int max, String paramName) {
        isTrue(value <= max, CommonErrorCode.ARGUMENT_NONLE, paramName, value, max);
    }

    /**
     * 是否小于等于 <
     *
     * @param value     检查的值
     * @param max       最大值
     * @param paramName 参数名
     */
    public static  void lt(int value, int max, String paramName) {
        isTrue(value < max, CommonErrorCode.ARGUMENT_NONLT, paramName, value, max);
    }

    /**
     * 是否大于
     *
     * @param value     检查的值
     * @param min       最小值
     * @param paramName 参数名
     */
    public static void gt(int value, int min, String paramName) {
        isTrue(value > min, CommonErrorCode.ARGUMENT_NONGT, paramName, value, min);
    }

    /**
     * 是否大于等于
     *
     * @param value     检查的值
     * @param min       最小值
     * @param paramName 参数名
     */
    public static void ge(int value, int min, String paramName) {
        isTrue(value >= min, CommonErrorCode.ARGUMENT_NONGE, paramName, value, min);
    }

    /**
     * 包含在数组内
     *
     * @param target    元素对象
     * @param list      列表
     * @param paramName 参数名
     * @param <T>       类型
     */
    public  static <T> void contains(T target, Collection<T> list, String paramName) {
        contains(target, list, CommonErrorCode.ARGUMENT_NONIN, paramName, target, list);
    }

    /**
     * 包含在数组内
     *
     * @param target    元素对象
     * @param list      列表
     * @param paramName 参数名
     * @param <T>       类型
     */
    public static <T> void contains(T target, T[] list, String paramName) {
        contains(target, list, CommonErrorCode.ARGUMENT_NONIN, paramName, target, Arrays.toString(list));
    }


    /**
     * 是否小于 <
     *
     * @param value     检查的值
     * @param max       最小值
     * @param errorCode 异常码
     * @param args      参数
     * @param <T>       异常类型
     */
    public static <T extends Enum<T> & ErrorCode> void lt(int value, int max, T errorCode, Object... args) {
        if (value > max) {
            throw BusinessException.of(errorCode, null, args);
        }
    }


    /**
     * 断言执行没有异常，如有有异常则抛出 ErrorCode.UNKNOWN 异常
     *
     * @param supplier 执行的函数
     * @param message  消息
     * @return
     */
    public static void nonErrorVoid(ExecuteByNoError supplier, String message) {
        nonErrorVoid(supplier, CommonErrorCode.UNKNOWN, message);
    }

    /**
     * 断言执行没有异常，如有有异常则抛出 CommonErrorCode.UNKNOWN 异常
     *
     * @param supplier 执行的函数
     * @param message  消息
     * @param <T>      返回数据
     * @return
     */
    public static <T> T nonError(SupplierByNoError<T> supplier, String message) {
        return nonError(supplier, CommonErrorCode.UNKNOWN, message);
    }

    /**
     * 断言执行没有异常，如有有异常则抛出 ErrorCode.UNKNOWN 异常
     *
     * @param param    入参
     * @param supplier 执行的函数
     * @param message  消息
     * @param <T>      返回数据
     * @param <Input>  入参类型
     * @return 结果
     */
    public static <T, Input> T nonError(Input param, SupplierOneParamByNoError<Input, T> supplier, String message) {
        return nonError(param, supplier, CommonErrorCode.UNKNOWN, message);
    }

    /**
     * 断言执行没有异常，如有有异常则抛出 ErrorCode.UNKNOWN 异常
     *
     * @param param    入参
     * @param supplier 执行的函数
     * @param message  消息
     * @param <Input>  入参类型
     */
    public static <Input> void nonErrorVoid(Input param, ExecuteOneParamByNoError<Input> supplier, String message) {
        nonErrorVoid(param, supplier, CommonErrorCode.UNKNOWN, message);
    }


    /**
     * 断言函数执行没有错误,如果有异常则抛出 {@link CommonErrorCode#UNKNOWN} 异常
     *
     * @param param1   传递的参数
     * @param param2   传递的参数
     * @param supplier 执行函数
     * @param message  消息
     * @param <T>      code 类型
     * @param <Input1> 入参类型
     * @param <Input2> 入参类型
     * @return supplier 执行结果
     * @see ErrorCode
     */
    public static <T extends Enum<T> & ErrorCode, Input1, Input2> void nonErrorVoid(Input1 param1, Input2 param2, ExecuteTwoParamByNoError<Input1, Input2> supplier, String message) {
        nonErrorVoid(param1, param2, supplier, CommonErrorCode.UNKNOWN, message);
    }


    /**
     * 断言函数执行没有错误
     *
     * @param param1   传递的参数
     * @param param2   传递的参数
     * @param supplier 执行函数
     * @param message  未知显示消息
     * @param <T>      code 类型
     * @param <Input1> 入参类型
     * @param <Input2> 入参类型
     * @param <K>      入参类型
     * @return supplier 执行结果
     */
    public static <T extends Enum<T> & ErrorCode, K, Input1, Input2> K nonError(Input1 param1, Input2 param2, SupplierTwoParamByNoError<Input1, Input2, K> supplier, String message) {
        return nonError(param1, param2, supplier, CommonErrorCode.UNKNOWN, message);
    }

    /**
     * 断言函数执行没有错误
     *
     * @param param     传递的参数
     * @param supplier  执行函数
     * @param errorCode 异常code
     * @param args      参数
     * @param <T>       code 类型
     * @param <Input>   入参类型
     * @return supplier 执行结果
     */
    public static <T extends Enum<T> & ErrorCode, Input, K> K nonError(Input param, SupplierOneParamByNoError<Input, K> supplier, T errorCode, Object... args) {
        try {
            return supplier.get(param);
        }
        // 如果是 sdk exception 则原样fanhui
        catch (BusinessException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw BusinessException.of(errorCode, ex, args);
        }
    }

    /**
     * 断言函数执行没有错误
     *
     * @param supplier  执行函数
     * @param errorCode 异常code
     * @param args      参数
     * @param <T>       code 类型
     * @param <M>       返回类型
     * @return supplier 执行结果
     */
    public static <T extends Enum<T> & ErrorCode, M> M nonError(SupplierByNoError<M> supplier, T errorCode, Object... args) {
        try {
            return supplier.get();
        }// 如果是 sdk exception 则原样fanhui
        catch (BusinessException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw BusinessException.of(errorCode, ex, args);
        }
    }

    /**
     * 断言函数执行没有错误
     *
     * @param param     传递的参数
     * @param supplier  执行函数
     * @param errorCode 异常code
     * @param args      参数
     * @param <T>       code 类型
     * @param <Input>   入参类型
     * @return supplier 执行结果
     */
    public static <T extends Enum<T> & ErrorCode, Input> void nonErrorVoid(Input param, ExecuteOneParamByNoError<Input> supplier, T errorCode, Object... args) {
        try {
            supplier.get(param);
        }// 如果是 sdk exception 则原样fanhui
        catch (BusinessException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw BusinessException.of(errorCode, ex, args);
        }
    }

    /**
     * 断言函数执行没有错误
     *
     * @param param1    传递的参数
     * @param param2    传递的参数
     * @param supplier  执行函数
     * @param errorCode 异常code
     * @param args      参数
     * @param <T>       code 类型
     * @param <Input1>  入参类型
     * @param <Input2>  入参类型
     * @return supplier 执行结果
     */
    public static <T extends Enum<T> & ErrorCode, Input1, Input2> void nonErrorVoid(Input1 param1, Input2 param2, ExecuteTwoParamByNoError<Input1, Input2> supplier, T errorCode, Object... args) {
        try {
            supplier.get(param1, param2);
        }// 如果是 sdk exception 则原样fanhui
        catch (BusinessException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw BusinessException.of(errorCode, ex, args);
        }
    }

    /**
     * 断言函数执行没有错误
     *
     * @param param1    传递的参数
     * @param param2    传递的参数
     * @param supplier  执行函数
     * @param errorCode 异常code
     * @param args      参数
     * @param <T>       code 类型
     * @param <Input1>  入参类型
     * @param <Input2>  入参类型
     * @param <K>       入参类型
     * @return supplier 执行结果
     */
    public static <T extends Enum<T> & ErrorCode, K, Input1, Input2> K nonError(Input1 param1, Input2 param2, SupplierTwoParamByNoError<Input1, Input2, K> supplier, T errorCode, Object... args) {
        try {
            return supplier.get(param1, param2);
        }// 如果是 sdk exception 则原样fanhui
        catch (BusinessException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw BusinessException.of(errorCode, ex, args);
        }
    }

    /**
     * 断言函数执行没有错误
     *
     * @param supplier  执行函数
     * @param errorCode 异常code
     * @param args      参数
     * @param <T>       code 类型
     * @return supplier 执行结果
     */
    public static <T extends Enum<T> & ErrorCode> void nonErrorVoid(ExecuteByNoError supplier, T errorCode, Object... args) {
        try {
            supplier.get();
        }// 如果是 sdk exception 则原样输出
        catch (BusinessException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw BusinessException.of(errorCode, ex, args);
        }
    }


    /**
     * hex 正则
     */
    public static final Pattern HEX_COLOR_REGX = Pattern.compile("^#[0-9a-z]{6}", Pattern.CASE_INSENSITIVE);


    /**
     * 能获取到 index 的 接口
     *
     * @param <T> 输入对象
     */
    public interface ForEachGetIndex<T> {

        /**
         * 处理函数
         *
         * @param index 坐标
         * @param elt   值
         */
        void apply(int index, T elt);

    }


    /**
     * 用于处理 try catch 的函数式接口
     *
     * @param <T>
     */
    public interface SupplierByNoError<T> {
        /**
         * 执行函数
         *
         * @return 结果
         * @throws Throwable 异常
         */
        T get() throws Throwable;
    }

    /**
     * 用于处理 try catch 的函数式接口，没有返回值模式
     */
    public interface ExecuteByNoError {
        /**
         * 执行函数
         *
         * @throws Throwable 异常
         */
        void get() throws Throwable;
    }


    public interface ExecuteOneParamByNoError<T> {
        /**
         * 执行
         *
         * @param param1 参数
         * @throws Throwable 异常
         */
        void get(T param1) throws Throwable;
    }

    public interface ExecuteTwoParamByNoError<T, M> {
        /**
         * 执行函数
         *
         * @param param1 参数1
         * @param param2 参数2
         * @throws Throwable 异常
         */
        void get(T param1, M param2) throws Throwable;
    }


    public interface SupplierOneParamByNoError<T, K> {
        /**
         * 执行
         *
         * @param param1 参数1
         * @return 结果
         * @throws Throwable 异常
         */
        K get(T param1) throws Throwable;
    }

    public interface SupplierTwoParamByNoError<T, K, M> {
        /**
         * 执行
         *
         * @param param1 参数1
         * @param param2 参数2
         * @return 结果
         * @throws Throwable 异常
         */
        M get(T param1, K param2) throws Throwable;
    }

}