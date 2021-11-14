package com.welldo.zero.a18_json;

/**
 * @author welldo
 * @date 2020/9/18
 */
public class Author {
    public String firstName;
    public String lastName;

    public Author() {
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
