package com.online.college.common.util;

import java.util.Date;
import java.util.Random;

/**
 *
 * @Description: number工具类
 * @author cmazxiaoma
 * @date 2018年2月5日
 * @version V1.0
 */
public class NumberUtil {

    /**
     * 生成编号
     *
     * @param number
     *            生成的编号位数
     * @return 返回生成的随机数
     */
    public static long randomNo(int number) {
        double rate1 = Math.pow(10, number - 1);
        double rate2 = rate1 * 9;
        long rate3 = (long) rate1 * 10;
        Random random = new Random();
        double tmp = random.nextDouble() * rate2 + rate1;
        long no = Math.round(tmp) % rate3;
        return no;
    }

    private static int seq = 0;// 序列
    private static final int LIMIT = 100000;// 上限
    private static Date date = new Date();

    // 根据时间生成唯一编码(考虑并发)
    public static synchronized long timeUinqueNumber() {
        if (seq > LIMIT) {
            seq = 0;
        }
        date.setTime(System.currentTimeMillis());
        String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d", date, seq++);
        return Long.parseLong(str);
    }
}
