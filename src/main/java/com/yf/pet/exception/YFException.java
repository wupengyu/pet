package com.yf.pet.exception;


import com.yf.pet.entity.ReturnMessageEnum;

/**
 * 自定义系统异常类
 *
 * @author infi.wang
 */
public class YFException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private static final String SQLERROR_CODE = "p0001";

    protected String result;


    protected ReturnMessageEnum returnMessage;

    /**
     * 构造方法
     */
    public YFException() {
    }

    /**
     * coros自定义业务异常
     *
     * @param returnMessage 消息编号
     */
    public YFException(ReturnMessageEnum returnMessage) {
        super(returnMessage.getMessage());
        this.setResult(returnMessage.getResult());
    }


    /**
     * coros自定义业务异常
     *
     * @param result    消息编号
     * @param message 消息
     */
    public YFException(String result, String message) {
        super(message);
        this.setResult(result);
    }


    /**
     * coros自定义业务异常
     *
     * @param e       异常信息
     * @param result    消息编号
     * @param message 占位符中参数
     */
    public YFException(Exception e, String result, String message) {
        super(message, e);
        this.setResult(result);
    }

    /**
     * 获取异常编码
     *
     * @return code 异常编码
     */
    public String getResult() {
        return result;
    }

    /**
     * 设置异常编码
     *
     * @param result 异常编码
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * 获取错误信息
     *
     * @return 错误信息枚举
     */
    public ReturnMessageEnum getReturnMessage() {
        return returnMessage;
    }

    /**
     * 设置错误信息
     *
     * @param returnMessage 错误信息枚举
     */
    public void setReturnMessage(ReturnMessageEnum returnMessage) {
        this.returnMessage = returnMessage;
    }

}
