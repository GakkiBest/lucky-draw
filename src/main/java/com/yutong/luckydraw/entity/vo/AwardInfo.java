package com.yutong.luckydraw.entity.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yuton
 * @description 奖品信息类
 */
@Data
public class AwardInfo {

    /**
     * 奖品 ID
     */
    private Long awardId;

    /**
     * 中奖率
     */
    private BigDecimal awardRate;
}
