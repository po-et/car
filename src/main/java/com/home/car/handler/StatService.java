package com.home.car.handler;

import com.google.common.collect.Lists;
import com.home.car.dao.*;
import com.home.car.model.CarBodyMO;
import com.home.car.model.CarBrandDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * stat car info
 *
 * @Author: poet
 * @Date: 2019-04-21
 */
@Slf4j
@Service
public class StatService {

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

    /**
     * save result files
     *
     * @param list
     * @param fileName
     */
    public void saveTargetFile(List<String> list, String fileName) {

        String filePath = System.getProperty("user.dir") + "/files/" + fileName + ".txt";
        File file = new File(filePath);
        try {
            FileUtils.writeLines(file, list, "\n", true);
        } catch (Exception e) {
            log.error("saveTargetFile Error!", e);
        }
    }


    /**
     * 车厢
     */
    public List<String> statCarriage() {

        //init
        List<String> respList = Lists.newLinkedList();

        // get names from files
        String filePath = System.getProperty("user.dir") + "/files/car_source.txt";
        File file = new File(filePath);

        List<CarBrandDetail> carBrandDetails = carBrandDetailDao.selectAll();

        Map<String, List<CarBrandDetail>> carTypeMap = carBrandDetails.stream().collect(Collectors.groupingBy(CarBrandDetail::getCarType));


//        List<CarBodyMO> carBodyMOS = carBodyMODao.selectAll();
        List<CarBodyMO> carBodyMOS = carBodyMODao.selectAllGroupByBrandId();
        Map<Long, List<CarBodyMO>> carriageMap = carBodyMOS.stream().collect(Collectors.groupingBy(CarBodyMO::getBrandId));


        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file), "UTF-8");

            for (String line : lines) {

                if (!carTypeMap.containsKey(line)) {
                    respList.add("-");
                    continue;
                }

                // only one element
                CarBrandDetail carBrandDetail = carTypeMap.get(line).get(0);
                Long brandId = carBrandDetail.getId();

                if (!carriageMap.containsKey(brandId)) {
                    respList.add("-");
                    continue;
                }

                // only one element
                CarBodyMO mo = carriageMap.get(brandId).get(0);
                respList.add(mo.getAttribute9().trim());
            }

        } catch (Exception e) {
            log.error("statCarriage error!", e);
        }

        return respList;
    }

    /**
     * 进口/出口
     *
     * @return
     */
    public List<String> statImExportComp(){

        //init
        List<String> respList = Lists.newLinkedList();

        // get names from files
        String filePath = System.getProperty("user.dir") + "/files/car_source.txt";
        File file = new File(filePath);

        List<CarBrandDetail> carBrandDetails = carBrandDetailDao.selectAll();

        Map<String, List<CarBrandDetail>> carTypeMap = carBrandDetails.stream().collect(Collectors.groupingBy(CarBrandDetail::getCarType));

        try {
            List<String> lines = IOUtils.readLines(new FileInputStream(file), "UTF-8");

            for (String line : lines) {

                String importStr = line + "(进口)";

                if (carTypeMap.containsKey(importStr)) {
                    respList.add("进口");
                    continue;
                }

                if (carTypeMap.containsKey(line)) {
                    respList.add("国产");
                    continue;
                }

                respList.add("-");
            }

        } catch (Exception e) {
            log.error("statImExportComp error!", e);
        }

        return respList;
    }


}
