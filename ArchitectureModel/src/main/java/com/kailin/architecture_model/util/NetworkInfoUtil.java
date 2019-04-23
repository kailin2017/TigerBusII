package com.kailin.architecture_model.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;

public final class NetworkInfoUtil {

    private static final AtomicReference<NetworkInfoUtil> reference = new AtomicReference<>();

    public static NetworkInfoUtil getInstance() {
        while (true) {
            NetworkInfoUtil instance = reference.get();
            if (instance != null)
                return instance;

            instance = new NetworkInfoUtil();
            if (reference.compareAndSet(null, instance))
                return instance;
        }
    }

    private final LoggerUtil loggerUtil = LoggerUtil.getInstance();

    private NetworkInfoUtil() {
    }

    public String getIPAddress(Context context) {
        if (!isConnection(context)) {
            return "";
        }
        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return CheckVersionUtil.getInstance().isAboveM() ?
                aboveM(context, connectivityManager) : belowM(context, connectivityManager);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private String aboveM(Context context, ConnectivityManager connectivityManager) {
        String ipAddress = "";
        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
            ipAddress = getLteIP();
        } else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE)) {
            ipAddress = getWiFiIP(context);
        }
        return ipAddress;
    }

    private String belowM(Context context, ConnectivityManager connectivityManager) {
        String ipAddress = "";
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            ipAddress = getLteIP();
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            ipAddress = getWiFiIP(context);
        }
        return ipAddress;
    }

    private String getLteIP() {
        try {
            Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaceEnumeration.hasMoreElements()) {
                Enumeration<InetAddress> inetAddressEnumeration = networkInterfaceEnumeration.nextElement().getInetAddresses();
                while (inetAddressEnumeration.hasMoreElements()) {
                    InetAddress inetAddress = inetAddressEnumeration.nextElement();
                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            loggerUtil.e("getLteIP Exception", e);
            return "";
        }
        return "";
    }

    private String getWiFiIP(Context context) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        return intIP2StringIP(wifiInfo.getIpAddress());
    }

    private String intIP2StringIP(int ip) {
        return String.format("%s.%s.%s.%s", ip & 0xFF, ip >> 8 & 0xFF, ip >> 16 & 0xFF, ip >> 24 & 0xFF);
    }

    public String getMacAddress() {
        try {
            NetworkInterface networkInterface = NetworkInterface.getByName("eth0");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }

            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : networkInterface.getHardwareAddress()) {
                stringBuffer.append(String.format("%02X:", b));
            }
            if (stringBuffer.length() > 0) {
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            }
            return stringBuffer.toString();
        } catch (SocketException e) {
            loggerUtil.e(e, "getMacAddress Exception");
            return "02:00:00:00:00:02";
        }
    }

    public boolean isConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return false;
        }
        return networkInfo.isConnected();
    }
}
