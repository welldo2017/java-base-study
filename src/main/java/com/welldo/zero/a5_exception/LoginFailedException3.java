package com.welldo.zero.a5_exception;

/**
 * @author welldo
 * @date 2020/8/12
 */
public class LoginFailedException3 extends BaseException3{
    public LoginFailedException3() {
        super();
    }

    public LoginFailedException3(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException3(String message) {
        super(message);
    }

    public LoginFailedException3(Throwable cause) {
        super(cause);
    }
}
