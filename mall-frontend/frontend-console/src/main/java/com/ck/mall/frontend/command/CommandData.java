package com.ck.mall.frontend.command;

/**
 * 命令信息抽象接口
 *
 * @author Qnxy
 */
public interface CommandData {

    /**
     * 命令序号
     */
    int getCmdIndex();

    /**
     * 命令描述信息
     */
    String getCmdDesc();

}
