package org.jeecg.config.udp;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.config.UdpConfig;
import org.jeecg.modules.gas.AirHandler;
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

import javax.annotation.Resource;
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
    static byte byte_json = 123;
    static byte byte_data = 1;

    @Resource
    private AirHandler airHandler;
    /**
     * UDP消息接收服务
     */
    @Bean
    public IntegrationFlow integrationFlow() {
        log.info("===UDP服务启动成功，端口号为: {}", UdpConfig.getListeningPort());
        return IntegrationFlows.from(Udp.inboundAdapter(UdpConfig.getListeningPort())).channel("udpChannel").get();
    }

    /*@Bean
    @ServiceActivator(inputChannel = "udpOut")
    public UnicastSendingMessageHandler unicastSendingMessageHandler() {
        UnicastSendingMessageHandler unicastSendingMessageHandler = new UnicastSendingMessageHandler("127.0.0.1", UdpConfig.getSendingPort());
        return unicastSendingMessageHandler;
    }*/

    /**
     * 转换器
     */
    @Transformer(inputChannel = "udpChannel", outputChannel = "udpFilter")
    public DataMess transformer(@Payload byte[] payload, @Headers Map<String, Object> headers) {
        DataMess dataMess = new DataMess();
        dataMess.setIp(headers.get("ip_address").toString());
        dataMess.setPayload(payload);
//        String message = new String(payload);
        log.info("===transformer dataMess:{}", dataMess.toString());
        // todo 进行数据转换
//        message = message.toUpperCase();
        return dataMess;
    }

    /**
     * 过滤器
     */
    @Filter(inputChannel = "udpFilter", outputChannel = "udpRouter")
    public boolean filter(DataMess dataMess, @Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        log.info("===filter id:{} ip:{} port:{}", id, ip, port);
        // todo 信息数据过滤
        log.info("===filter length 0");
        return true;
    }

    /**
     * 路由分发处理器:可以进行分发消息被那个处理器进行处理
     */
    @Router(inputChannel = "udpRouter")
    public String router(DataMess dataMess, @Headers Map<String, Object> headers) {
        // 获取来源Id
        String id = headers.get("id").toString();
        // 获取来源IP，可以进行IP过滤
        String ip = headers.get("ip_address").toString();
        // 获取来源Port
        String port = headers.get("ip_port").toString();
        log.info("===udpRouter id:{} ip:{} port:{}", id, ip, port);

        byte[] payload = dataMess.getPayload();
        if (payload.length > 0) {
            byte firstByte = payload[0];
            if (firstByte == byte_json) {
                return "udpHandle2";
            }else if (firstByte == byte_data) {
                return "udpHandle1";
            }
        }

        log.info("===udpRouter 不处理");
        return "errHandler";
    }

    /**
     * 最终处理器1
     */
    @ServiceActivator(inputChannel = "udpHandle1")
    public void udpMessageHandle(DataMess dataMess) throws Exception {
        // todo 可以进行异步处理
        log.info("===udpHandle1:{}", dataMess.toString());
        airHandler.handle(dataMess.getPayload());
    }

    /**
     * 最终处理器2
     */
    @ServiceActivator(inputChannel = "udpHandle2")
    public void udpMessageHandle2(DataMess dataMess) throws Exception {
        byte[] payload = dataMess.getPayload();
        log.info("===udpHandle2:{}", new String(payload, "GBK"));
        try {
            HeartData heartData = JSONObject.parseObject(new String(payload, "GBK"), HeartData.class);
            log.info(heartData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @ServiceActivator(inputChannel = "errHandler")
    public void errHandler(DataMess dataMess) throws Exception {
        log.info("===errHandler:{}", dataMess.toString());
    }
}

@Data
class HeartData {
    private String type;
    private String sn;
    private String name;
}

@Data
class DataMess {
    private String ip;
    private byte[] payload;
}
