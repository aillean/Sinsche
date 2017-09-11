package com.android.arvin.manager;

import android.content.Context;
import android.util.Log;

import com.android.arvin.data.DeviceData;
import com.android.arvin.interfaces.UiDataCallback;
import com.android.arvin.request.Dt550Request;
import com.arvin.request.request.baserequest.BaseCallback;
import com.arvin.request.request.baserequest.BaseRequest;
import com.arvin.request.request.baserequest.RequestEnum;
import com.arvin.request.request.baserequest.RequestManager;
import com.qq408543103.basenet.AuthorClient;
import com.qq408543103.basenet.interfaces.DataCallback;
import com.sinsche.core.ws.client.android.DT550HisDataRsp;
import com.sinsche.core.ws.client.android.struct.ClientInfoRspUserInfo;
import com.sinsche.core.ws.client.android.struct.DT550RealDataRspDevice;

import java.util.List;

/**
 * Created by arvin on 2017/9/11 0011.
 */

public class DataReception implements DataCallback {

    private static final String TAG = DataReception.class.getSimpleName();
    private Context context;
    private AuthorClient authorClient;
    private RequestManager requestManager;
    private UiDataCallback uiDataCallback;

    public DataReception(Context context, UiDataCallback callback) {
        this.context = context;
        this.uiDataCallback = callback;
        authorClient = new AuthorClient();
        authorClient.setDataCallback(this);
        requestManager = new RequestManager();
        authorClient.Start("192.168.3.105", 7010, "A14BEC3A-FC35F0AE-2302646A-6EB3723A","AndroidAPP", context.getCacheDir().getAbsolutePath());
    }

    @Override
    public void getClientInfoRspUserInfoList(List<ClientInfoRspUserInfo> list) {
        requestFormLogin(list);
    }

    @Override
    public void getDataRspDeviceList(List<DT550RealDataRspDevice> list) {
        Log.d(TAG, "getDataRspDeviceList");
        requestFormCurrentlyData(list);
    }

    @Override
    public void getDT550HisDataRsp(DT550HisDataRsp dt550HisDataRsp) {
        Log.d(TAG, "getDT550HisDataRsp");
        requestFormHistoricData(dt550HisDataRsp);
    }


    public void requestFormLogin(List<ClientInfoRspUserInfo> list) {
        Dt550Request request = new Dt550Request();
        request.setLoginList(list);
        request.setContext(context);
        request.setRequestEnum(RequestEnum.RequestFormLogin);
        requestManager.submitRequest(request, new BaseCallback() {
            @Override
            public void done(BaseRequest request, Exception e) {
                if (request instanceof Dt550Request) {

                }
            }
        });
    }

    public void requestFormCurrentlyData(List<DT550RealDataRspDevice> list) {
        Dt550Request request = new Dt550Request();
        request.setCurrentlyDataList(list);
        request.setContext(context);
        request.setRequestEnum(RequestEnum.RequestFormCurrentlyData);
        requestManager.submitRequest(request, new BaseCallback() {
            @Override
            public void done(BaseRequest request, Exception e) {
                if (request instanceof Dt550Request) {
                    DeviceData deviceData = ((Dt550Request) request).getDeviceData();
                    uiDataCallback.addDeviceView(deviceData);
                }
            }
        });
    }

    public void requestFormHistoricData(DT550HisDataRsp data) {
        Dt550Request request = new Dt550Request();
        request.setDT550HisDataRsp(data);
        request.setContext(context);
        request.setRequestEnum(RequestEnum.RequestFormHistoricData);
        requestManager.submitRequest(request, new BaseCallback() {
            @Override
            public void done(BaseRequest request, Exception e) {
                if (request instanceof Dt550Request) {
                    DeviceData deviceData = ((Dt550Request) request).getDeviceData();
                    uiDataCallback.showDialog(deviceData);
                }
            }
        });
    }
}

