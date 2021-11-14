package com.welldo.zero.a18_json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.welldo.zero.a11_io.Classpath7;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.time.LocalDate;

/**
 * 0. XML的特点是功能全面，但标签繁琐，格式复杂。在Web上使用XML现在越来越少，取而代之的是JSON这种数据结构。
 *
 * 在Java中，针对JSON也有标准的JSR 353 API
 *
 * 1.JSON是JavaScript Object Notation(js对象符号)的缩写，它去除了所有 js 执行代码，只保留 js 的对象格式。
 * 一个典型的JSON如下：
 * {
 *     "id": 1,
 *     "name": "Java核心技术",
 *     "author": {
 *         "firstName": "wang",
 *         "lastName": "welldo"
 *     },
 *     "isbn": "1234567",
 *     "tags": ["Java", "bigdata"]
 * }
 *
 * 2.JSON作为数据传输的格式，有几个显著的优点：
 *      JSON只允许使用UTF-8编码，不存在编码问题；
 *      JSON只允许使用双引号作为key，特殊字符用\转义，格式简单；
 *      浏览器内置JSON支持，如果把数据用JSON发送给浏览器，可以用 js 直接读写。
 *      (所以，开发Web应用的时候，使用JSON作为数据传输，在浏览器端非常方便)
 *
 * 3.因为它格式简单，仅支持以下几种数据类型：
 *      键值对：{"key": value}
 *      数组：[1, 2, 3]
 *      字符串："abc"
 *      数值（整数和浮点数）：12 或 12.34
 *      布尔值：true或false
 *      空值：null
 *
 * 4.使用Java如何对JSON进行读写？
 * 在Java中，针对JSON也有标准的JSR 353 API
 * 常用的用于解析JSON的第三方库有：
 *      Jackson (com.fasterxml.jackson.core:jackson-databind:2.10.0)
 *      Gson
 *      Fastjson
 *      ...
 *
 *
 * 5.
 * 把JSON解析为JavaBean的过程称为反序列化。
 * 如果把JavaBean变为JSON，那就是序列化。
 * 注意, 如果使用jackson来序列化时, 属性不能时private
 *
 * 6.要把JSON的某些值解析为特定的Java对象，例如LocalDate，也是完全可以的
 * com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.0
 * 看代码
 *
 *
 *
 * @author welldo
 * @date 2020/9/18
 */
public class WhatIsJson {

    public static void main(String[] args) {
        test1();
        // test6();
    }


    /**
     * 5. 要把JSON的某些值解析为特定的Java对象，例如LocalDate，也是完全可以的。
     * 只需要引入标准的JSR 310关于JavaTime的数据格式定义至Maven：
     * com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.0
     *
     * 然后，在创建ObjectMapper时，注册一个新的JavaTimeModule：
     *
     * 6. 有些时候，内置的解析规则和扩展的解析规则如果都不满足我们的需求，还可以自定义解析。
     * 例如isbn字段, 直接解析，肯定报错。这时，我们需要自定义一个IsbnDeserializer，用于解析含有非数字的字符串：
     */
    static void test6() {
        String json = "{\n" +
                "    \"name\": \"Java核心技术\",\n" +
                "    \"pubDate\": \"2016-09-01\",\n" +
                "    \"isbn\": \"978-7-111-54742-6\"\n" +
                "}";

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Book6 book = null;
        try {
            book = mapper.readValue(json, Book6.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(book);
    }


    /**
     * classpath参考 {@link Classpath7}
     */
    static void test1() {
        Book book = null;
        String json = "";
        try (InputStream input = WhatIsJson.class.getResourceAsStream("/book.json")) {

            ObjectMapper mapper = new ObjectMapper();

            // 反序列化时忽略不存在的JavaBean属性:
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            //解析JSON文件,也就是反序列化
            book = mapper.readValue(input, Book.class);

            // 要实现序列化，只需要一行代码：
            json = mapper.writeValueAsString(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(book);
        System.out.println(json);
    }

}

class Book6 {
    public String name;

    public LocalDate pubDate;

    // 表示反序列化isbn时使用自定义的IsbnDeserializer:
    @JsonDeserialize(using = IsbnDeserializer.class)
    public BigInteger isbn;


    @Override
    public String toString() {
        return "Book6{" +
                "name='" + name + '\'' +
                ", pubDate=" + pubDate +
                ", isbn=" + isbn +
                '}';
    }
}


/**
 * 6.自定义一个IsbnDeserializer，用于解析含有非数字的字符串：
 *
 * 类似的，自定义序列化时我们需要自定义一个IsbnSerializer，然后在Book类中标注@JsonSerialize(using = ...)即可。todo
 */
class IsbnDeserializer extends JsonDeserializer<BigInteger> {
    public BigInteger deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        // 读取原始的JSON字符串内容:
        String s = p.getValueAsString();
        if (s != null) {
            try {
                return new BigInteger(s.replace("-", ""));
            } catch (NumberFormatException e) {
                throw new JsonParseException(p, s, e);
            }
        }
        return null;
    }
}

