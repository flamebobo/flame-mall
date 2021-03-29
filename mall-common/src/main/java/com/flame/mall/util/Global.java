package com.flame.mall.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>Title: 全局常量定义</p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company:PCCW</p>
 *
 * @version 1.0
 * @description:
 * @author: Flame.Lai
 * @time: 2020/12/29 23:42
 */
public class Global {

    /**
     * Redis过期检测的Key
     */
    public class ExpireKey {
        public final static String EXPIRED_KEY_EVENT_START_UUID = "EXPIRED_KEY_EVENT_START_UUID_";
        public final static String EXPIRED_KEY_ORDER_SPIKE = "EXPIRED_KEY_ORDER_SPIKE_";
        public final static String EXPIRED_KEY_ORDER_NORMAL = "EXPIRED_KEY_ORDER_NORMAL_";
    }

    /**
     * Redis消息队列频道ID定义
     */
    public class REDIS_MQ_CHANNEL {
        public static final String KEY_EXPIRED = "__keyevent@0__:expired";            // 所有Key到期通知
    }

    /**
     * 订单类型
     */
    @Getter
    @AllArgsConstructor
    public enum OrderType {
        /**
         * 正常订单
         */
        NORMAL(0, "正常订单"),
        /**
         * 秒杀订单
         */
        SPIKE(1, "秒杀订单");

        int value;
        String message;
    }

    /**
     * 支付方式
     */
    @Getter
    @AllArgsConstructor
    public enum PayType {
        /**
         * 0：未支付
         */
        NO_PAY(0, "未支付"),
        /**
         * 1：支付宝
         */
        ALI_PAY(1, "支付宝"),
        /**
         * 2：微信
         */
        WECHAT_PAY(2, "微信支付");

        int value;
        String message;
    }

    /**
     * 订单来源
     */
    @Getter
    @AllArgsConstructor
    public enum SourceType {
        /**
         * PC订单
         */
        PC_ORDER(0, "PC订单"),
        /**
         * app订单
         */
        APP_ORDER(1, "app订单");

        int value;
        String message;
    }

    /**
     * 订单状态
     */
    @Getter
    @AllArgsConstructor
    public enum OrderStatus {
        /**
         * 待付款
         */
        PENDING_PAYMENT(0, "待付款"),
        /**
         * 待发货
         */
        TO_BE_DELIVERED(1, "待发货"),
        /**
         * 已发货
         */
        SHIPPED(2, "已发货"),
        /**
         * 已完成
         */
        COMPLETED(3, "已完成"),

        /**
         * 已关闭
         */
        CLOSED(4, "已发货"),
        /**
         * 无效订单
         */
        Invalid_order(5, "无效订单");

        int value;
        String message;
    }

    /**
     * 发票类型
     */
    @Getter
    @AllArgsConstructor
    public enum BillType {
        /**
         * 不开发票
         */
        NOINVOICE(0, "不开发票"),
        /**
         * 电子发票
         */
        INVOICE(1, "电子发票"),
        /**
         * 纸质发票
         */
        PAPER_INVOICE(1, "纸质发票");

        int value;
        String message;
    }

    /**
     * 确认收货状态
     */
    @Getter
    @AllArgsConstructor
    public enum ConfirmStatus {
        /**
         * 未确认
         */
        UNCONFIRMED(0, "未确认"),
        /**
         * 已确认
         */
        CONFIRMED(1, "已确认");

        int value;
        String message;
    }

    /**
     * 删除状态
     */
    @Getter
    @AllArgsConstructor
    public enum DeleteStatus {
        /**
         * 未删除
         */
        NOT_DELETED(0, "未删除"),
        /**
         * 已删除
         */
        DELETED(1, "已删除");

        int value;
        String message;
    }

}
