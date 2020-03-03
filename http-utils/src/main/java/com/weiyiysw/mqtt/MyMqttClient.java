package com.weiyiysw.mqtt;

import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;

/**
 * @author yishiwei
 * @Date 2020/3/1
 */
public class MyMqttClient {

    private static final String MQTT_BROKER_HOST = "172.16.0.195";
    private static final int MQTT_BROKER_PORT = 1883;

    public static void main(String[] args) {
        Mqtt3AsyncClient client = MqttClient.builder()
                .useMqttVersion3()
                .identifier("my-mqtt-client-id")
                .serverHost(MQTT_BROKER_HOST)
                .serverPort(MQTT_BROKER_PORT)
//                .sslWithDefaultConfig()
                .buildAsync();

        client.connectWith().send().whenComplete(((mqtt3ConnAck, throwable) -> {
            if (throwable != null) {
                System.out.println("connect failed!");
                System.out.println(throwable);
            } else {
                // 连接成功后，去做业务逻辑
                System.out.println("connect success!");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 业务逻辑完成，关闭即可。
                client.disconnect();
            }
        }));

    }
}
