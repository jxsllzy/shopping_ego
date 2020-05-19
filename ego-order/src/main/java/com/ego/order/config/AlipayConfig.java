package com.ego.order.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016101800713873";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCBmkoETj0yni2lgy1/bPd8piHubG16ahFb5thxVrEl+SHIBzfMHV9BYGg0dw8aqr5Fo3i3CtnesL/pL17kbJJ6q7AyOV7jHJjDriNTnXFZnHpVzYZErxQ/O7G7laArBCEy3S4y/IywLDlZyn01B8Y09M7rfjYFfV+Vrzm06D3u/B3YtuTM+Evb3v/LUXvUKYzjofXswskd6wovMl7Babzx09LPO9vwZhdNCDlEaOR3Goldvidqnbfbp6LhrR/k1tC4qBj4xUcA4nF3QWeqJninDAf0W201rvWyNSuIeItP+ikrfZQ3l1zWkOUBZx7NG3W2cT1eHFb1rn3THmKOD+vdAgMBAAECggEARH2PJBtcyAVDxjY+sxqY56y8Pc3bRVgMeG7Ub4EK+TBho938pMfY8SnLp/5LYyJcj1zGjKUVYlQx9z9rfHfMKTvWT5R2CruwF6lNTgDkSCEjnXiRvnZv0sLvOFzoWhNCoCi3I4bHqsqUPFmANag4HQBK4bS/Q8Nv4iDJ9ErPgB3HrKJz/p2zQenMGlL2GPtsgo7/DA+w6sJd07QzppJITKKqg1QU6La1LHO5DhHJrpGvdYqV81ohEBiTR/x517TSfAiBVacGh7QjHShy4zn1SIR/+MQTRtWEEm9VLzdb6g/WapkFj+X76roku09OGhsxde3uVaHfnVMChd01gBNVwQKBgQDAr1RfhMd5QrF67m4p9CBfsfvtu5m/MrY2FJJeVdWmckne84lUQ52F1lw3h/f3BmN1opj7o9vffxbKe5GvlLtzkFUZBNrLikH66Q7+uogBIPnaRBjMUkECML9NVXDVJNMpmExixwtewy/EZHWZuzO0gwvL081uPDJIvhHK6gD8GQKBgQCsMHrVk4NfbPQdqSOYCVQpFZgBdTduKPnHvqijyIGDiDHWIRmU5mVyrSu43xYs7mvJRiLakqzwTwMnP4KYzXpb7JfKYKDvSLhchwuLbXJwWbGNV0t7t4C74IVCkqxUGfJSiOwJS+rjD8WZBqSMWPzpn9mbjqcnxBC4WajqCF7mZQKBgFCnEwIIahZvvCpgsuwMq6Bit7kC1ZAagfqREdM6ynspS5JvDgSfaMgkueTknP5hRjBvJunt5JLgd4G5x3lfIDd3KqeWSJ75+DQ2aXhmS5dtXifmaRrdMyjfjrOoF9qf12OJxC90cWXw/YWPJ0p5+WKNl9pxhYOm3sVkCb6NxGvBAoGARkLJ/JhixO96dRftVD6KPvF2wzGHWsYRljXwLlTEtF1AqLV88MtKng6XhqpRLChlhw29bx7mvnAfQ0ahDaTaxuO11Hi1cq5jvC3Gwi/4wGt5DcmPHX51YzRsbAvZkb+8p30Dg4+c2uY2pQiW4EX3fYUikF0XTByvDepYCJc90hkCgYByUfEL59JGpXN5gNgK5lFqulpYajNcNlKzcuCYP3pQiFoCARZL6EiCIVg6VA219jqUEO1hhvRsNqCSx6VBdqTg1WPETL2bKKEb5xr4d3bIJ/MPACmMPcaWyDPCQcgY2z0SoJiINvpqiZPJ62CwEBxfahGNdbmN2L/Lne7Ltoot2A==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkoc0oIGGzkSX5zIN1SwI2Cd8ZSCoKoeVztN2+Fu9aH//ggqktUswlRZWCc7chKWGoZL+fPrxlFDAEWOn3+PUgDMp/1aeQCgpYoytK+k5gNvSi8FTHqGE8oatZ0PqMwN/GvVo8zKJhl6KREBTX4h6NZIS7TxlI+x3nUMzQE6dy14DjDDr0LkvqGBtP0lZs5JagXsNr5HNTwftcxxq64LEMfXmMIAq/sFXu4e2maGxV2PaTOTUSWWOiF6d1GMGHQjLfZrkFNkOG+aIATKBKwJmB2Zje4QpnaPZ/ddFeiSaWhlAPHs1R4lNpHlSpr9nHs4awjW2X98ITGEPG1bUFg/WuQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = " http://zn7q5h.natappfree.cc/ego-portal/order/callback";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:9092/ego-order/order/myOrder";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

