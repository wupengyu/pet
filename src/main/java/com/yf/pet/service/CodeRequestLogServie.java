package com.yf.pet.service;


import com.yf.pet.entity.checkcode.CodeRequestLog;

/**
 * 概述: 验证码请求日志service层<br>
 * 背景: <br>
 * Created by Infi on 17/3/9.
 */
public interface CodeRequestLogServie {
    /**
     * 保存验证码请求记录<br>
     *
     * @param jsonParam 验证码请求对象json参数<br>
     */
    void addCodeRequestLog(CodeRequestLog codeRequestLog);

    /**
     * 查询验证码请求记录<br>
     *
     * @param jsonParam 查询条件json参数<br>
     *                  mobile     电话号码<br>
     *                  createDate 创建时间<br>
     * @return 验证码信息<br>
     */
    CodeRequestLog findCodeRequestLog(CodeRequestLog codeRequestLog);
}
