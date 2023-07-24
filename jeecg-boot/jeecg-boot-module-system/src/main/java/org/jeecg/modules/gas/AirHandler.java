package org.jeecg.modules.gas;

import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.gas.model.MGas;
import org.jeecg.modules.gas.service.MGasService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@Service
public class AirHandler {
@Resource
private MGasService mGasService;

    public void handle(byte[] payload) {
        ArrayList<byte[]> dataArr = getData(payload);
        ArrayList<MGas> mGasArrayList = new ArrayList<>();
        if (dataArr.size()>0) {
            for(int i=0 ; i< dataArr.size(); i++) {
                byte[] b = dataArr.get(i);
                MGas mGas = new MGas();
                mGas.setGasIndex(i);
                mGas.setValueFirst(b[0]);
                mGas.setValueSecond(b[1]);
                mGas.setGasType(b[2]);
                mGas.setStatus(b[3]);
                mGasArrayList.add(mGas);
                log.info("index:{},data:{}",i, mGas.toString());
            }

            mGasService.saveOrUpdateBatch(mGasArrayList);
        }
    }

    private ArrayList getData(byte[] payload) {
        ArrayList<byte[]> list = new ArrayList<>();
        if (payload == null || payload.length<=5 || payload.length%4 != 1) {
            return list;
        }

        byte[] dataArr = Arrays.copyOfRange(payload, 3, payload.length-2);
        if (dataArr.length == 0 || dataArr.length%4 != 0) {
            return list;
        }

        for (int i=0;i< dataArr.length;i++) {
            if (list.size() <= i/4 && i%4==0) {
                list.add(new byte[4]);
            }
            list.get(i/4)[i%4]=dataArr[i];
        }
        return list;
    }

    public static void main(String[] args) {
        AirHandler airHandler = new AirHandler();
        log.info(airHandler.getData(new byte[]{1, 35, 64, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 8, -128, 0, 0, 23, -128, 0, 4, 23, -128, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 0, 0, 8, -123, 15, 27}).toString());
    }
}
