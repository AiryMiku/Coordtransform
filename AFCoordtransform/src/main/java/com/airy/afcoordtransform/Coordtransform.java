package com.airy.afcoordtransform;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.atan2;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;

/**
 * Create by Airy at 2019.07.23
 *
 * inspire by https://github.com/wandergis/coordTransform
 */

public class Coordtransform {

    private static double a = 6378245.0;    // 地球长半轴

    private static double ee = 0.00669342162296594323;  // 偏心率平方

    /**
     * google, amap -> baidu
     * @param latitude
     * @param longitude
     * @return
     */
    public static double[] gcj02tobd09(double latitude, double longitude) {
        double z = sqrt(longitude * longitude + latitude * latitude) + 0.00002 * sin(latitude * PI);
        double theta = atan2(latitude, longitude) + 0.000003 * cos(longitude * PI);
        double bd_lng = z * cos(theta) + 0.0065;
        double bd_lat = z * sin(theta) + 0.006;
        return new double[]{bd_lat, bd_lng};
    }

    /**
     * baidu -> google, amap
     * @param bdLatitude
     * @param bdLongitude
     * @return
     */
    public static double[] bd09togcj02(double bdLatitude, double bdLongitude) {
        double x = bdLongitude - 0.0065;
        double y = bdLatitude - 0.006;
        double z = sqrt(x * x + y * y) - 0.00002 * sin(y * PI);
        double theta = atan2(y, x) - 0.000003 * cos(x * PI);
        double gg_lng = z * cos(theta);
        double gg_lat = z * sin(theta);
        return new double[]{gg_lat, gg_lng};
    }

    /**
     * gps -> google, amap
     * @param latitude
     * @param longitude
     * @return
     */
    public static double[] wgs84togcj02(double latitude, double longitude) {
        if (isOutOfChina(latitude, longitude)) {
            return new double[]{latitude, longitude};
        }

        double dlat = tranfromLatitude(latitude - 35.0,longitude - 105.0);
        double dlng = tranfromLongitude(latitude - 35.0,longitude - 105.0);
        double radlat = latitude / 180.0 * PI;
        double magic = sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
        dlng = (dlng * 180.0) / (a / sqrtmagic * cos(radlat) * PI);
        double mglat = latitude + dlat;
        double mglng = longitude + dlng;
        return new double[]{mglat, mglng};
    }

    /**
     * google, amap -> gps
     * @param lat
     * @param lng
     * @return
     */
    public static double[] gcj02towgs84(double lat, double lng) {
        if (isOutOfChina(lat, lng)) {
            return new double[]{lat, lng};
        }
        double dlat = tranfromLatitude(lat - 35.0,lng - 105.0);
        double dlng = tranfromLongitude(lat - 35.0, lng - 105.0);
        double radlat = lat / 180.0 * PI;
        double magic = sin(radlat);
        magic = 1 - ee * magic * magic;
        double sqrtmagic = sqrt(magic);
        dlat = (dlat * 180.0) / ((a * (1 - ee)) / (magic * sqrtmagic) * PI);
        dlng = (dlng * 180.0) / (a / sqrtmagic * cos(radlat) * PI);
        double mglat = lat + dlat;
        double mglng = lng + dlng;
        return new double[]{lat * 2 - mglat, lng * 2 - mglng};
    }

    /**
     * baidu -> gps
     * @param bdLatitude
     * @param bdLongitude
     * @return
     */
    public static double[] bd09towgs84(double bdLatitude, double bdLongitude) {
        double[] latlng = bd09togcj02(bdLatitude, bdLongitude);
        double lat = latlng[0];
        double lng = latlng[1];
        return gcj02towgs84(lat, lng);
    }

    public static double[] wgs84tobd09(double latitude, double longitude) {
        double[] latlng = wgs84togcj02(latitude, longitude);
        double lat = latlng[0];
        double lng = latlng[1];
        return gcj02tobd09(lat, lng);
    }

    /**
     * Algorithm
     * @param latitude
     * @param longitude
     * @return
     */
    private static double tranfromLatitude(double latitude, double longitude) {
        double ret = -100.0 + 2.0 * longitude + 3.0 * latitude + 0.2 * latitude * latitude + 0.1 * longitude * latitude + 0.2 * sqrt(abs(longitude));
        ret += (20.0 * sin(6.0 * longitude * PI) + 20.0 * sin(2.0 * longitude * PI)) * 2.0 / 3.0;
        ret += (20.0 * sin(latitude * PI) + 40.0 * sin(latitude / 3.0 * PI)) * 2.0 / 3.0;
        ret += (160.0 * sin(latitude / 12.0 * PI) + 320 * sin(latitude * PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * Algorithm
     * @param latitude
     * @param longitude
     * @return
     */
    private static double tranfromLongitude(double latitude, double longitude) {
        double ret = 300.0 + longitude + 2.0 * latitude + 0.1 * longitude * longitude + 0.1 * longitude * latitude + 0.1 * sqrt(abs(longitude));
        ret += (20.0 * sin(6.0 * longitude * PI) + 20.0 * sin(2.0 * longitude * PI)) * 2.0 / 3.0;
        ret += (20.0 * sin(longitude * PI) + 40.0 * sin(longitude / 3.0 * PI)) * 2.0 / 3.0;
        ret += (150.0 * sin(longitude / 12.0 * PI) + 300.0 * sin(longitude / 30.0 * PI)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * judge the latlng whether in china
     * @param latitude
     * @param longitude
     * @return
     */
    public static boolean isOutOfChina(double latitude, double longitude) {
        return !(longitude > 73.66 && longitude < 135.05 && latitude > 3.86 && latitude < 53.55);
    }

}