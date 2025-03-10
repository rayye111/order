package com.qd.common.result;
/**
 * 结果枚举
 *
 */
public enum ResultEnum {
    // 成功
    SUCCESS("200", "成功"), FAIL("0", "失败"), COMMON_EXCEPTION("500", "系统异常"),

    // 用户相关
    FAIL_USER_ACCOUNT_EMPTY("10001", "账号为空"), FAIL_USER_ACCOUNT_EXIST("10002", "账号已存在，请重新输入"),
    FAIL_USER_GET("10003", "查询用户信息失败"), FAIL_USER_ADD_ERROR("10004", "添加用户失败"),
    FAIL_USER_HANDLE_ERROR("10005", "系统管理员禁止禁用"), FAIL_USER_ACCOUNT_ERROR("10022", "用户名错误，用户名仅支持英文、数字、字符"),

    // 文件相关
    FAIL_FILE_EMPTY("40001", "文件为空"), FAIL_FILE_SIZE_ERROR("40002", "文件大小超过50k"),
    FAIL_FILE_SUFFIX_ERROR("40003", "文件格式不符合要求"),

    ;

    String code;
    String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ResultEnum getByCode(String code) {
        if (code == null) {
            return COMMON_EXCEPTION;
        }
        for (ResultEnum outputEnum : ResultEnum.values()) {
            if (code.equals(outputEnum.code)) {
                return outputEnum;
            }
        }
        return COMMON_EXCEPTION;
    }
}
