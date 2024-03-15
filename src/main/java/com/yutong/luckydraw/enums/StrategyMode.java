package com.yutong.luckydraw.enums;

/**
 * @author yuton
 */

public enum StrategyMode {
    /**
     * 单体抽奖算法
     */
    SINGLE(1, "单体抽奖算法"),
    /**
     * 总体抽奖算法
     */
    ENTIRETY(2, "总体抽奖算法");

    private Integer code;

    private String algorithmName;
    StrategyMode(Integer code, String algorithmName){
        this.code = code;
        this.algorithmName = algorithmName;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }
}
