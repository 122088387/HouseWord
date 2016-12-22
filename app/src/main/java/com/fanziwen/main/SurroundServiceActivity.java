package com.fanziwen.main;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.CircleOptions;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.Stroke;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.fanziwen.base.BaseActivity;
import com.fanziwen.houseword.R;
import com.fanziwen.main.bean.HouseBean;
import com.fanziwen.utils.PoiOverlay;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建者：  王晓赛
 * 时  间： 2016/12/22
 */

@ContentView(R.layout.activity_surround_service)
public class SurroundServiceActivity extends BaseActivity implements OnGetPoiSearchResultListener {

    HouseBean.DataBean dataBean;

    @ViewInject(R.id.bdmapView)
    private MapView mMapView = null;

    private BaiduMap mBaiduMap = null;

    private PoiSearch mPoiSearch = null;

    private int loadIndex = 0;

    LatLng center;

    int radius = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
//        setActionBar("周边服务", R.drawable.nav_return, 0);
        dataBean = (HouseBean.DataBean) getIntent().getExtras().getSerializable("bean");

        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

        //设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        MyLocationConfiguration config = new MyLocationConfiguration(
                MyLocationConfiguration.LocationMode.NORMAL, true, null);
        mBaiduMap.setMyLocationConfigeration(config);

        //将当前房屋位置 设置为地图的中心位置
        LatLng ll = new LatLng(Double.parseDouble(dataBean.getLatitude()),
                Double.parseDouble(dataBean.getLongitude()));
        center = ll;
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.target(ll).zoom(18.0f);
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));

        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        searchNearbyProcess();
    }

    boolean isLast = false;

    String[] poiTile = {"餐厅", "医院", "银行", "停车场", "KTV"};

    /**
     * 相应周边搜索时间
     */
    public void searchNearbyProcess() {
        for (int i = 0; i < poiTile.length; i++) {
            if (i == poiTile.length - 1) {
                isLast = true;
            }
            PoiNearbySearchOption nearbySearchOption = new PoiNearbySearchOption().keyword(poiTile[i]).sortType(PoiSortType.distance_from_near_to_far).location(center)
                    .radius(radius).pageNum(loadIndex);
            mPoiSearch.searchNearby(nearbySearchOption);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPoiSearch.destroy();
        super.onDestroy();
    }

    List<PoiResult> resultList = new ArrayList<>();

    @Override
    public void onGetPoiResult(PoiResult result) {
//        if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
//            Toast.makeText(this, "未找到结果", Toast.LENGTH_LONG)
//                    .show();
//            return;
//        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {
            resultList.add(result);
//            mBaiduMap.clear();
            if (isLast) {
                PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
                mBaiduMap.setOnMarkerClickListener(overlay);
//                overlay.setData(result);
                overlay.setDataList(resultList);
                overlay.addToMap();
                overlay.zoomToSpan();
                showNearbyArea(center, radius);
                return;
            }
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
            String strInfo = "在";
            for (CityInfo cityInfo : result.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }
            strInfo += "找到结果";
            Toast.makeText(this, strInfo, Toast.LENGTH_LONG)
                    .show();
        }
    }

    /**
     * 对周边检索的范围进行绘制
     *
     * @param center
     * @param radius
     */
    public void showNearbyArea(LatLng center, int radius) {
        BitmapDescriptor centerBitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_gcoding);
        MarkerOptions ooMarker = new MarkerOptions().position(center).icon(centerBitmap);
        mBaiduMap.addOverlay(ooMarker);

        OverlayOptions ooCircle = new CircleOptions().fillColor(0x22FFFFFF)
                .center(center).stroke(new Stroke(2, 0xFF2897F2))
                .radius(radius);
        mBaiduMap.addOverlay(ooCircle);
    }

    /**
     * 获取POI详情搜索结果，得到searchPoiDetail返回的搜索结果
     *
     * @param result
     */
    @Override
    public void onGetPoiDetailResult(PoiDetailResult result) {
        if (result.error != SearchResult.ERRORNO.NO_ERROR) {
            Toast.makeText(this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
                    .show();
        } else {
            Toast.makeText(this, result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT)
                    .show();
        }
    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }


    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));
            // }
            return true;
        }
    }
}
