package com.yutong.luckydraw.algorithm;

import com.yutong.luckydraw.entity.vo.AwardInfo;
import com.yutong.luckydraw.enums.StrategyMode;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yuton
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    /**
     * 斐波那契散列增量
     */
    private final int HASH_INCREMENT = 0x61c88647;

    /**
     * 概率元组长度
     */
    private final int RATE_TUPLE_LEN = 128;

    /**
     * 不同策略对应不同的概率元组
     * key : strategyId
     * value : 奖品数组
     */
    Map<Long, Long[]> initRateTupleMap = new ConcurrentHashMap<>();

    Map<Long, List<AwardInfo>> awardInfoMap = new ConcurrentHashMap<>();

    @Override
    public synchronized void initRateTuple(Long strategyId, Integer strategyMode, List<AwardInfo> awardInfoList) {

        // 已经被初始化过
        if (isExist(strategyId)) {
            return;
        }

        // 非单体概率，总体概率实时变化，需要独立处理
        if (!StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }

        awardInfoMap.put(strategyId, awardInfoList);

        Long[] rateTuple = initRateTupleMap.computeIfAbsent(strategyId, k -> new Long[RATE_TUPLE_LEN]);

        int cursor = 0;
        for (AwardInfo award : awardInfoList) {
            // 中奖率
            int awardRate = award.getAwardRate().multiply(new BigDecimal(100)).intValue();

            // 循环散列填充奖品信息
            for (int i = cursor + 1; i <= awardRate + cursor; i++){
                rateTuple[hashIdx(i)] = award.getAwardId();
            }

            cursor += awardRate;
        }

    }

    /**
     * 斐波那契散列法
     * @param val 值
     * @return    随机索引下标
     */
    protected int hashIdx(int val) {
        int hashcode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashcode & (RATE_TUPLE_LEN - 1);
    }

    @Override
    public boolean isExist(Long strategyId) {
        return awardInfoMap.containsKey(strategyId);
    }

    /**
     * 生成百位随机抽奖码
     * @param bound 边界
     * @return      int 值
     */
    protected int generateSecureRandomIntValue(int bound) {
        return new SecureRandom().nextInt(bound) + 1;
    }
}
