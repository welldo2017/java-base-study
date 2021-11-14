package com.welldo.zero.a5_exception;

/**
 * 测试
 * 从BaseException派生LoginFailedException 和 UserNotFoundException
 *
 *
 * @author welldo
 * @date 2020/8/12
 */
public class Test3 {

    public static void main(String[] args) {
        try {
            String token = login("admin", "password");
            System.out.println("Token: " + token);
        } catch (LoginFailedException3 | UserNotFoundException3 e) {
            e.printStackTrace();
        }
    }

    static String login(String username, String password) {
        if (username.equals("admin")) {
            if (password.equals("password")) {
                return "登录成功";
            } else {
                // 抛出LoginFailedException:
                throw new LoginFailedException3("Bad username or password.");
            }
        } else {
            // 抛出UserNotFoundException:
            throw new UserNotFoundException3("User not found.");
        }
    }
}
