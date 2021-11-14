package com.welldo.zero.a18_json;

import java.util.Arrays;

/**
 * 注意, 如果使用jackson来序列化时, 属性不能时private
 * @author welldo
 * @date 2020/9/18
 */
class Book {
    public int id;
    public String name;
    public Author author;
    public String isbn;
    public String[] tags;

    public Book() {
    }

    public Book(int id, String name, Author author, String isbn, String[] tags) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", isbn='" + isbn + '\'' +
                ", tags=" + Arrays.toString(tags) +
                '}';
    }
}
