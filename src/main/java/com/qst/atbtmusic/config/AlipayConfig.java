package com.qst.atbtmusic.config;

public class AlipayConfig {
    
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	    public static String app_id = "2016100200644855";
	    
	    // 商户私钥，您的PKCS8格式RSA2私钥
	    public static String merchant_private_key = "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";
	    
	    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAghVO48JOFug8C9PaMjmTB+IDqOc8HiFDdl+HcGdey40o3jv9oHujUMlR0CH+M1l9uOrLZ99js86M6aFQt5OwmCzbtRw/cjtcW0OyGZEYspYyWHci+D0lScTJ+Yl2WTlmc8zZPtYbI/e6YM3SXVkcCmo/Vhc8D8x88Tvq68eZ7cA1D9HemgqVB/oLMD0XvP73i/jajh/Exp6lYw0EAxd/NZDxhjVDeOw3W7YRZnhRX9teK0meoZJyFyJZs8XUXfRCrL7HjJfHABfN6tiQuwRnYsQAdXZdHWOBLJLl0arPx2Z3nhpqczWJtX7Jd8keWhoZ3fnIzDrKQ99cIXQ4M6NV1QIDAQAB";

	    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	    public static String notify_url = "http://localhost:8082/success";

	    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	    public static String return_url = "http://localhost:8082/success";

	    // 签名方式
	    public static String sign_type = "RSA2";
	    
	    // 字符编码格式
	    public static String charset = "utf-8";
	    
	    // 支付宝网关
	    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	    
	    // 支付宝网关
	    public static String log_path = "C:\\";
}
