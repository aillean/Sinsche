package com.android.arvin.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.android.arvin.data.DeviceData;
import com.android.arvin.R;
import com.android.arvin.manager.DataReception;
import com.android.arvin.data.GObject;
import com.android.arvin.interfaces.UiDataCallback;
import com.android.arvin.ui.ContentItemView;
import com.android.arvin.ui.DeviceFooterLayout;
import com.android.arvin.ui.DeviceLayout;
import com.android.arvin.ui.Dialog.DeviceDialog;
import com.android.arvin.ui.DtContentView;
import com.android.arvin.util.DeviceConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends DtAppCompatActivity implements UiDataCallback {

    final static String TAG = MainActivity.class.getSimpleName();
    private Map<String, DeviceLayout> deviceMap = new HashMap<String, DeviceLayout>();
    private ScrollView device_scrollView;
    private LinearLayout deviceFatherFayout;
    private DataReception dataReception;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initSupportActionBarWithCustomBackFunction();
        initActionBar();
        initView();


    }

    private void initActionBar() {
        actionBar.addOnMenuVisibilityListener(new ActionBar.OnMenuVisibilityListener() {
            @Override
            public void onMenuVisibilityChanged(boolean isVisible) {
                if (!isVisible) {

                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    private void initView() {
        device_scrollView = (ScrollView) findViewById(R.id.device_scrollView);
        deviceFatherFayout = (LinearLayout) findViewById(R.id.device_father_layout);
        dataReception = new DataReception(this, this);
    }

    private void initSubView(DeviceLayout deviceLayout) {
        HashMap<String, Integer> mapping = new HashMap<String, Integer>();
        mapping.put(DeviceConfig.MEASURE_ITEM_LIQUID_STATE, R.id.measure_item_liquid_state_text);
        mapping.put(DeviceConfig.MEASURE_ITEM_NAME, R.id.measure_item_name_text);
        mapping.put(DeviceConfig.MEASURE_ITEM_VALUE, R.id.measure_item_value_text);
        mapping.put(DeviceConfig.MEASURE_ITEM_TIME, R.id.measure_item_time_text);
        ArrayList<Integer> styleList = new ArrayList<>();
        deviceLayout.setSubLayoutParameter(mapping, styleList);
    }

    public void showLoginDialog() {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        DeviceDialog dialog = DeviceDialog.instance();
        dialog.show(ft, "");
    }

    private void startAddDeviceView(final DeviceData deviceData) {
        DeviceLayout deviceLayout = new DeviceLayout(this, deviceData);
        //key必需不一样, 需要设置端提供唯的标识,否则有可能发生碰撞, 进而更新错数据
        deviceMap.put(deviceData.getStrSerial(), deviceLayout);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        deviceLayout.setLayoutParams(layoutParams);

        if (deviceFatherFayout == null) return;

        deviceFatherFayout.addView(deviceLayout);

        deviceLayout.setOnClickCallBack(new DeviceFooterLayout.OnClickCallBack() {
            @Override
            public void onClickCallBack(final DeviceLayout device, final boolean fold) {

                if (device != null) {
                    device.updateGridLayoutHight(fold);
                }

                if (fold) {
                    //需要重新确定定位的位置.
                    // device_scrollView.fullScroll(ScrollView.FOCUS_UP);
                }
            }
        });

        deviceLayout.setContentViewCallback(new DtContentView.ContentViewCallback() {

            @Override
            public void uiLoading(DeviceLayout device) {
                Log.d(TAG, "uiLoading");
                if (device != null)
                    device.setAdapter();
            }

            @Override
            public void beforeSetupData(ContentItemView view, GObject object) {

            }

            @Override
            public void onItemClick(ContentItemView view) {

                ////
                ////
                ////请在这里,触发更新dialog数据的操作, 数据更新完成, 会启动dialog
                ////数据暂时未填充.

            }

            @Override
            public boolean onItemLongClick(ContentItemView view) {
                return false;
            }
        });

        initSubView(deviceLayout);
    }

    @Override
    public void addDeviceView(DeviceData deviceData) {
        startAddDeviceView(deviceData);
    }

    @Override
    public void showDialog(DeviceData deviceData) {
        showLoginDialog();
    }

}
