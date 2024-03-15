package com.yutong.luckydraw.algorithm;

import com.yutong.luckydraw.entity.vo.AwardInfo;

import java.util.List;

/**
 * @author yuton
 * @description 抽奖算法接口
 */
public interface IDrawAlgorithm {

    /**
     * 初始化概率元组
     * @param strategyId        策略 ID
     * @param strategyMode      策略模式编号
     * @param awardInfoList     奖品信息列表
     */
    void initRateTuple(Long strategyId, Integer strategyMode, List<AwardInfo> awardInfoList);

    /**
     * 是否已经初始化了概率元祖
     * @param strategyId        策略 ID
     * @return                  布尔值结果
     */
    boolean isExist(Long strategyId);

    /**
     * 随机抽奖
     * @param strategyId        策略ID
     * @return                  奖品ID
     */
    Long randomDraw(Long strategyId);
}
