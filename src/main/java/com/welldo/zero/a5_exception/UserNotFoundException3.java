package com.welldo.zero.a5_exception;

/**
 * 在一个大型项目中，可以自定义新的异常类型，但是，保持一个合理的异常继承体系是非常重要的。
 *
 * 一个常见的做法是自定义一个BaseException作为“根异常”，然后，派生出各种业务类型的异常。
 *
 * BaseException需要从一个适合的Exception派生，通常建议从RuntimeException派生：
 *
 * @author welldo
 * @date 2020/8/12
 */
public class UserNotFoundException3 extends BaseException3 {


    public UserNotFoundException3() {
        super();
    }

    public UserNotFoundException3(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException3(String message) {
        super(message);
    }

    public UserNotFoundException3(Throwable cause) {
        super(cause);
    }
}
