package com.home.car.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.home.car.dao.*;
import com.home.car.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.home.car.enums.UrlConstant.APP_KEY;
import static com.home.car.enums.UrlConstant.SERIES_ID_URL;

/**
 * 查询车型详细信息
 *
 * @Author: poet
 * @Date: 2019-04-20
 */
@Slf4j
@Service
public class CarSeriesService {

    @Autowired
    AirConfigMODao airConfigMODao;
    @Autowired
    BaseInfoMODao baseInfoMODao;
    @Autowired
    CarBodyMODao carBodyMODao;
    @Autowired
    CarBrandDao carBrandDao;
    @Autowired
    CarBrandDetailDao carBrandDetailDao;
    @Autowired
    CarSeriesNameDao carSeriesNameDao;
    @Autowired
    ChassisMODao chassisMODao;
    @Autowired
    ControlConfigMODao controlConfigMODao;
    @Autowired
    EngineMODao engineMODao;
    @Autowired
    ExterConfigMODao exterConfigMODao;
    @Autowired
    GlassConfigMODao glassConfigMODao;
    @Autowired
    InterConfigMODao interConfigMODao;
    @Autowired
    LightConfigMODao lightConfigMODao;
    @Autowired
    MediaConfigMODao mediaConfigMODao;
    @Autowired
    SafetyDeviceMODao safetyDeviceMODao;
    @Autowired
    SeatConfigMODao seatConfigMODao;
    @Autowired
    TechConfigMODao techConfigMODao;
    @Autowired
    TransmissionMODao transmissionMODao;
    @Autowired
    WheelInfoMODao wheelInfoMODao;


    public void saveCarAllInfo() throws Exception {

        // init
        List<SafetyDeviceMO> safetyDeviceMOS = Lists.newArrayList();
        List<AirConfigMO> airConfigMOList = Lists.newArrayList();
        List<ChassisMO> chassisMOS = Lists.newArrayList();
        List<SeatConfigMO> seatConfigMOS = Lists.newArrayList();
        List<GlassConfigMO> glassConfigMOS = Lists.newArrayList();
        List<EngineMO> engineMOS = Lists.newArrayList();
        List<ExterConfigMO> exterConfigMOS = Lists.newArrayList();
        List<CarBodyMO> carBodyMOS = Lists.newArrayList();
        List<MediaConfigMO> mediaConfigMOS = Lists.newArrayList();
        List<LightConfigMO> lightConfigMOS = Lists.newArrayList();
        List<InterConfigMO> interConfigMOS = Lists.newArrayList();
        List<ControlConfigMO> controlConfigMOS = Lists.newArrayList();
        List<BaseInfoMO> baseInfoMOS = Lists.newArrayList();


        List<CarSeriesName> carSeriesNameList = carSeriesNameDao.selectAll();
        log.info("carSeriesNameList: {}", carSeriesNameList);

        for (CarSeriesName car : carSeriesNameList) {
            Integer carId = car.getCarId();

            // url req data
            Map<String, String> urlParam = Maps.newHashMap();
            urlParam.put("key", APP_KEY);
            urlParam.put("cid", carId + "");

            HttpRequest request = HttpRequest.get(SERIES_ID_URL).form(urlParam);
            String resp = request.body();
            log.info("http serial id, code: {}, msg: {}", request.code(), resp);

            // parse json
            if (request.code() == 200) {

                JSONObject jsonObject = JSON.parseObject(resp);
                if ("200".equals(jsonObject.getString("retCode"))) {

                    JSONObject result = jsonObject.getJSONArray("result").getJSONObject(0);


                    saveAirConfigMO(car, result.getJSONArray("airConfig"));
                    saveBaseInfoMO(car, result.getJSONArray("baseInfo"));
                    saveCarBodyMO(car, result.getJSONArray("carbody"));
                    saveChassisMO(car, result.getJSONArray("chassis"));
                    saveControlConfigMO(car, result.getJSONArray("controlConfig"));
                    saveEngineMO(car, result.getJSONArray("engine"));
                    saveExterConfigMO(car, result.getJSONArray("exterConfig"));
                    saveGlassConfigMO(car, result.getJSONArray("glassConfig"));
                    saveInterConfigMO(car, result.getJSONArray("interConfig"));
                    saveLightConfigMO(car, result.getJSONArray("lightConfig"));
                    saveMediaConfigMO(car, result.getJSONArray("mediaConfig"));
                    saveSafetyDeviceMO(car, result.getJSONArray("safetyDevice"));
                    saveSeatConfigMO(car, result.getJSONArray("seatConfig"));
                    saveTechConfigMO(car, result.getJSONArray("techConfig"));
                    saveTransmissionMO(car, result.getJSONArray("transmission"));
                    saveWheelInfoMO(car, result.getJSONArray("wheelInfo"));


                    car.setCarImage(result.getString("carImage"));
                    carSeriesNameDao.updateCarImage(car);
                }

            } else {
                log.error("http failed! carId: {}, code: {}", carId, request.code());
            }
        }
    }


    private void saveAirConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }


        AirConfigMO mo = new AirConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            airConfigMODao.insertSelective(mo);

        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveBaseInfoMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        BaseInfoMO mo = new BaseInfoMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));
            mo.setAttribute14(array.getJSONObject(13).getString("value"));
            mo.setAttribute15(array.getJSONObject(14).getString("value"));
            mo.setAttribute16(array.getJSONObject(15).getString("value"));

            baseInfoMODao.insertSelective(mo);

        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveCarBodyMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        CarBodyMO mo = new CarBodyMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));

            carBodyMODao.insertSelective(mo);

        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveChassisMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        ChassisMO mo = new ChassisMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));

            chassisMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveControlConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        ControlConfigMO mo = new ControlConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));
            mo.setAttribute14(array.getJSONObject(13).getString("value"));

            controlConfigMODao.insertSelective(mo);

        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveEngineMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        EngineMO mo = new EngineMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));
            mo.setAttribute14(array.getJSONObject(13).getString("value"));
            mo.setAttribute15(array.getJSONObject(14).getString("value"));
            mo.setAttribute16(array.getJSONObject(15).getString("value"));
            mo.setAttribute17(array.getJSONObject(16).getString("value"));
            mo.setAttribute18(array.getJSONObject(17).getString("value"));
            mo.setAttribute19(array.getJSONObject(18).getString("value"));
            mo.setAttribute20(array.getJSONObject(19).getString("value"));
            mo.setAttribute21(array.getJSONObject(20).getString("value"));
            mo.setAttribute22(array.getJSONObject(21).getString("value"));
            mo.setAttribute23(array.getJSONObject(22).getString("value"));
            engineMODao.insertSelective(mo);

        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveExterConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        ExterConfigMO mo = new ExterConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));

            exterConfigMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveGlassConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        GlassConfigMO mo = new GlassConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));
            mo.setAttribute14(array.getJSONObject(13).getString("value"));

            glassConfigMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveInterConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        InterConfigMO mo = new InterConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));

            interConfigMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveLightConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        LightConfigMO mo = new LightConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));

            lightConfigMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveMediaConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        MediaConfigMO mo = new MediaConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));

            mediaConfigMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveSafetyDeviceMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        SafetyDeviceMO mo = new SafetyDeviceMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));

            safetyDeviceMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveSeatConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        SeatConfigMO mo = new SeatConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));
            mo.setAttribute11(array.getJSONObject(10).getString("value"));
            mo.setAttribute12(array.getJSONObject(11).getString("value"));
            mo.setAttribute13(array.getJSONObject(12).getString("value"));
            mo.setAttribute14(array.getJSONObject(13).getString("value"));
            mo.setAttribute15(array.getJSONObject(14).getString("value"));
            mo.setAttribute16(array.getJSONObject(15).getString("value"));
            mo.setAttribute17(array.getJSONObject(16).getString("value"));

            seatConfigMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveTechConfigMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        TechConfigMO mo = new TechConfigMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));
            mo.setAttribute7(array.getJSONObject(6).getString("value"));
            mo.setAttribute8(array.getJSONObject(7).getString("value"));
            mo.setAttribute9(array.getJSONObject(8).getString("value"));
            mo.setAttribute10(array.getJSONObject(9).getString("value"));

            techConfigMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveTransmissionMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        TransmissionMO mo = new TransmissionMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));

            transmissionMODao.insertSelective(mo);
        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

    private void saveWheelInfoMO(CarSeriesName car, JSONArray array) {
        if (Objects.isNull(array)) {
            log.error("JSONArray is empty! car: {}", car);
            return;
        }

        WheelInfoMO mo = new WheelInfoMO();
        try {
            mo.setParentId(car.getParentId());
            mo.setBrandId(car.getBrandId());
            mo.setCarId(car.getCarId());
            mo.setAttribute1(array.getJSONObject(0).getString("value"));
            mo.setAttribute2(array.getJSONObject(1).getString("value"));
            mo.setAttribute3(array.getJSONObject(2).getString("value"));
            mo.setAttribute4(array.getJSONObject(3).getString("value"));
            mo.setAttribute5(array.getJSONObject(4).getString("value"));
            mo.setAttribute6(array.getJSONObject(5).getString("value"));

            wheelInfoMODao.insertSelective(mo);

        } catch (Exception e) {
            log.error("Error! car: Car: {}, JsonArray: {}", car, array);
        }
    }

}
