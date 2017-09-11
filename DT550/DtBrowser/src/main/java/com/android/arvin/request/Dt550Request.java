package com.android.arvin.request;

import android.util.Log;

import com.android.arvin.data.DeviceData;
import com.android.arvin.data.HistoricalData;
import com.android.arvin.data.SubItemData;
import com.android.arvin.R;
import com.arvin.request.request.baserequest.BaseRequest;
import com.arvin.request.request.baserequest.RequestEnum;
import com.sinsche.core.ws.client.android.DT550HisDataRsp;
import com.sinsche.core.ws.client.android.struct.ClientInfoRspUserInfo;
import com.sinsche.core.ws.client.android.struct.DT550RealDataRspDevice;
import com.sinsche.core.ws.client.android.struct.DT550RealDataRspDeviceItem;
import com.sinsche.core.ws.client.android.struct.DT55HisDataRspItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arvin on 2017/8/23 0023.
 */

public class Dt550Request extends BaseRequest {

    private static final String TAG = Dt550Request.class.getSimpleName();
    private RequestEnum requestEnum;
    private List<ClientInfoRspUserInfo> loginList;
    private List<DT550RealDataRspDevice> currentlyDataList;
    private DT550HisDataRsp dt550HisDataRsp;

    private DeviceData deviceData;

    public void setRequestEnum(RequestEnum requestEnum) {
        this.requestEnum = requestEnum;
    }

    public void setLoginList(List<ClientInfoRspUserInfo> loginList) {
        this.loginList = loginList;
    }

    public void setCurrentlyDataList(List<DT550RealDataRspDevice> currentlyDataList) {
        this.currentlyDataList = currentlyDataList;
    }

    public void setDT550HisDataRsp(DT550HisDataRsp data) {
        this.dt550HisDataRsp = data;
    }

    public DeviceData getDeviceData(){
        return deviceData;
    }

    @Override
    public void execute(final BaseRequest request) {
        if (request instanceof Dt550Request)
            switch (((Dt550Request) request).requestEnum) {
                case RequestFormLogin:


                    break;
                case RequestFormCurrentlyData:
                    if (currentlyDataList == null) return;
                    for (DT550RealDataRspDevice data : currentlyDataList) {
                        List<SubItemData> subListDatas = new ArrayList<>();
                        List<DT550RealDataRspDeviceItem> list = data.getItem();

                        for (DT550RealDataRspDeviceItem item : list) {
                            SubItemData itemData = new SubItemData(item.getStrCode(), R.drawable.liquid_state_text_yes_bg, item.getStrWaterState(), item.getStrName(), item.getStrData(), data.getStrTestTime());
                            subListDatas.add(itemData);
                        }

                        Log.d(TAG, "data.getStrState1(): " + data.getStrState1());
                        Log.d(TAG, "data.getStrState2(): " + data.getStrState2());

                        boolean state1 = data.getStrState1().contains("是") ? true : false;
                        boolean state2 = data.getStrState2().contains("是") ? true : false;
                        deviceData = new DeviceData(data.getStrName(), data.getStrSerial(), data.getStrTestTime(), state1, state2, subListDatas);
                    }


                    break;
                case RequestFormHistoricData:
                    if (dt550HisDataRsp == null) return;

                    List<HistoricalData> listHisDatas = new ArrayList<>();
                    for (DT55HisDataRspItem item : dt550HisDataRsp.getListDT55HisDataRspItem()) {
                        HistoricalData data = new HistoricalData(item.getStrTestTime(), item.getStrData());
                        listHisDatas.add(data);
                    }
                    deviceData = new DeviceData(dt550HisDataRsp.getStrItemCode(), listHisDatas);
                    break;
            }
    }
}


