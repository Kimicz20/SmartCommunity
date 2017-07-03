/*
 *  所有权归603实验室所有
 */

package edu.hdu.lab.utils;

import com.google.gson.Gson;
import edu.hdu.lab.model.GeoLocation;
import java.net.URI;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.log4j.Logger;

/**
 *
 * @author justin
 */
public class LocationUtils {
    
    private static Logger logger = Logger.getLogger(LocationUtils.class);
    
    /**
     * 圆周率
     */
    private final static double PI = 3.14159265358979323;
    
    /**
     * 地球半径
     */
    private final static double R = 6371229;
    
    /**
     * 计算两个地点之间的距离
     * @param longitude1 地点一的经度
     * @param latitude1 地点一的纬度
     * @param longitude2 地点二的经度
     * @param latitude2 地点二的纬度
     * @return 距离
     * @author http://www.cnblogs.com/computer-lzy/archive/2011/04/21/2024289.html
     */
    public static double getDistanceBetweenPlaces(double longitude1, double latitude1, double longitude2, double latitude2) {
        double x, y, distance;
        
        x = (longitude2 - longitude1) * PI * R
            * Math.cos(((latitude1 + latitude2) / 2) * PI / 180) / 180;
        y = (latitude2 - latitude1) * PI * R / 180;
        distance = Math.hypot(x, y);
        
        return distance;
    }
    
    /**
     * 获取给定地址的经纬度
     * @param rawAddress 原始地址，文字形式
     * @param mapURLScheme   地图供应商接口的协议，如http
     * @param mapURLHost  地图供应商接口的主机地址
     * @param mapURLPath  地图供应商接口的路径
     * @param mapKey 地图供应商的接口的key
     * @return 经纬度，格式为"120.33,30.45".
     */
    public static String getGeoLocation(String rawAddress, String mapURLScheme, String mapURLHost, String mapURLPath, String mapKey) {
        URI uri = null;
        String location = "";
        
        try {
            uri = new URIBuilder()
                      .setScheme(mapURLScheme)
                      .setHost(mapURLHost)
                      .setPath(mapURLPath)
                      .setParameter("address", rawAddress)
                      .setParameter("output", "json")
                      .setParameter("ak", mapKey)
                      .build();
            String resultJson = Request.Get(uri).execute().returnContent().asString();
            GeoLocation geo = new Gson().fromJson(resultJson, GeoLocation.class);
            location = geo.getResult().getLocation().getLng() + "," + geo.getResult().getLocation().getLat();
            logger.info("Address: " + rawAddress + "\nLocation: " + location);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return location;
    }
    
    public static void main(String[] args) {
        String location = getGeoLocation("万塘路", "http", "api.map.baidu.com", "/geocoder/v2/", "86888b40d197256c44aae7c900378dee");
        System.out.println("location: " + location);
    }
}
