package com.syl.snow.fragment.content2;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.syl.snow.R;
import com.syl.snow.base.BaseFragment;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

/**
 * @author Bright
 * @date 2020/6/26 11:23
 * @describe 获取当前手机网络信息, WiFi信息
 */
public class WifiFragment extends BaseFragment {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_wifi, container, false);
        TextView tv = rootView.findViewById(R.id.tv);
        String networkState = getNetworkState(Objects.requireNonNull(getActivity()));
        String wifiInfo = getWifiInfo(getActivity());
        tv.setText(networkState + "\n" + wifiInfo);
        return rootView;
    }

    /**
     * 获取WiFi信号强度
     *
     * @param context
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private String getWifiInfo(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = Objects.requireNonNull(wifiManager).getConnectionInfo();
        int describeContents = wifiInfo.describeContents();
        String bssid = wifiInfo.getBSSID();
        int frequency = wifiInfo.getFrequency();
        boolean hiddenSSID = wifiInfo.getHiddenSSID();
        int ipAddress = wifiInfo.getIpAddress();
        int linkSpeed = wifiInfo.getLinkSpeed();
        String macAddress = wifiInfo.getMacAddress();
        int networkId = wifiInfo.getNetworkId();
//        String passpointFqdn = wifiInfo.getPasspointFqdn();
        int rssi = wifiInfo.getRssi();
//        int rxLinkSpeedMbps = wifiInfo.getRxLinkSpeedMbps();
        String ssid = wifiInfo.getSSID();
        SupplicantState supplicantState = wifiInfo.getSupplicantState();
//        int txLinkSpeedMbps = wifiInfo.getTxLinkSpeedMbps();
        String str = "describeContents=" + describeContents + "\nbssid=" + bssid + "\nfrequency=" + frequency +
                "\nhiddenSSID=" + hiddenSSID + "\nipAddress=" + ipAddress + "\nlinkSpeed=" + linkSpeed +
                "\nmacAddress=" + macAddress + "\nrssi" + rssi;
        return str;
    }

    /**
     * 获取网络状态信息
     *
     * @param context
     * @return
     */
    private String getNetworkState(Context context) {
        String strNetworkType = "";
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE); // 获取网络服务
        //NetworkInfo networkInfo = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE).getActiveNetworkInfo();
        NetworkInfo networkInfo = Objects.requireNonNull(connManager).getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                strNetworkType = "WIFI";
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                String _strSubTypeName = networkInfo.getSubtypeName();

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000")) {
                            strNetworkType = "3G";
                        } else {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }
            }
        }
        return strNetworkType;
    }
}
