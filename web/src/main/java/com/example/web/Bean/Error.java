package com.example.web.Bean;

//虚拟商品错误 8  用户错误7  关系错误6  实体商品错误5
//查询 1 更新 2 删除 3 插入 4
//不存在用户1 不存在管理员2 不存在商品3 不存在目标用户4 不存在关系 5 用户重复 6 密码错误 7 金额不足 8

public class Error {
    private int errorId;
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorId() {
        return errorId;
    }

    public void setErrorMsg(int errorId,String errorMsg) {
        this.errorId = errorId;
        this.errorMsg = errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setErrorId(int errorId) {
        this.errorId = errorId;
    }
}
