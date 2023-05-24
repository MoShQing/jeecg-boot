package org.jeecg.config.udp;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.config.UdpConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.Router;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.dsl.Udp;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.Map;

/**
 * @Version 1.0
 * @Author meng.zhang6@hirain.com
 * @Created Date:2023/2/13 15:09
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2023/2/13 15:09 meng.zhang6@hirain.com 1.0 create file
 */

/**
 * UDP消息接收服务
 *
 * @author wliduo[i@dolyw.com]
 * @date 2020/5/20 14:16
 */
@Configuration
@Slf4j
public class UdpServer {

    /**
     * UDP消息接收服务
     */
    @Bean
    public IntegrationFlow integrationFlow() {
        log.info("===UDP服务启动成功，端口号为: {}", UdpConfig.getListeningPort());
        return IntegrationFlows.from(Udp.inboundAdapter(UdpConfig.getListeningPort())).channel("udpChannel").get();
    }

    /**
     * 转换器
     */
    @Transformer(inputChannel = "udpChannel", outputChannel = "udpFilter")
    public String transformer(@Payload byte[] payload, @Headers Map<String, Object> headers) {
        String message = new String(payload);
        log.info("===transformer message:{}", message);
        // todo 进行数据转换
        message = message.toUpperCase();
        return message;
    }

    /**
     * 过滤器
     */
    @Filter(inputChannel = "udpFilter", outputChannel = "udpRouter")
    public boolean filter(String message, @Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        log.info("===filter id:{} ip:{} port:{}", id, ip, port);
        // todo 信息数据过滤
        if (true) {
            // 没有-的数据会被过滤
            return false;
        }
        return true;
    }

    /**
     * 路由分发处理器:可以进行分发消息被那个处理器进行处理
     */
    @Router(inputChannel = "udpRouter")
    public String router(String message, @Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        log.info("===filter id:{} ip:{} port:{}", id, ip, port);
        // todo 筛选，走那个处理器
        if (true) {
            return "udpHandle2";
        }
        return "udpHandle1";
    }

    /**
     * 最终处理器1
     */
    @ServiceActivator(inputChannel = "udpHandle1")
    public void udpMessageHandle(String message) throws Exception {
        // todo 可以进行异步处理
        log.info("===message:{}", message);
    }

    /**
     * 最终处理器2
     */
    @ServiceActivator(inputChannel = "udpHandle2")
    public void udpMessageHandle2(String message) throws Exception {
        log.info("===UDP2:{}", message);
    }
}


