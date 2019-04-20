DROP TABLE IF EXISTS `car_brand`;
CREATE TABLE `car_brand` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `brand` varchar(100) NOT NULL COMMENT '品牌名称',
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车品牌表';



DROP TABLE IF EXISTS `car_brand_details`;
CREATE TABLE `car_brand_details` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT '父品牌ID, F:',
  `parent_brand` varchar(100) NOT NULL COMMENT '父品牌',
  `car_brand` varchar(100) NOT NULL COMMENT '子品牌',
  `car_type` varchar(100) NOT NULL COMMENT '子品牌Type',
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车子品牌表';



DROP TABLE IF EXISTS `car_series_name`;
CREATE TABLE `car_series_name` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id`  bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `brand_name` varchar(100) DEFAULT NULL COMMENT '品牌名称',
  `series_name` varchar(100) DEFAULT NULL COMMENT '车型名称',
  `car_id` int(11) NOT NULL COMMENT '数据id(查询车型详细信息用到)',
  `guide_price` varchar(100) DEFAULT NULL COMMENT '指导价',
  `car_image` varchar(200) DEFAULT NULL COMMENT '汽车图片',
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车型表';









--------------------------------------------------
DROP TABLE IF EXISTS `t_air_config`;
CREATE TABLE `t_air_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1` varchar(200) DEFAULT NULL COMMENT '空调控制方式: 手动 1',
  `attribute_2` varchar(200) DEFAULT NULL COMMENT '后排独立空调: -',
  `attribute_3` varchar(200) DEFAULT NULL COMMENT '后座出风口: -',
  `attribute_4` varchar(200) DEFAULT NULL COMMENT '温度分区控制: -',
  `attribute_5` varchar(200) DEFAULT NULL COMMENT '车内空气调节/花粉过滤: 1',
  `attribute_6` varchar(200) DEFAULT NULL COMMENT '车载冰箱: -',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='空调/冰箱配置信息';



DROP TABLE IF EXISTS `t_base_info`;
CREATE TABLE `t_base_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '车型名称',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '厂商指导价(元)',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '厂商',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '级别',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '发动机',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '变速箱',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '长*宽*高(mm)',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '车身结构',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '最高车速(km/h)',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '官方0-100km/h加速(s)',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '实测0-100km/h加速(s)',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '实测100-0km/h制动(m)',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT '实测油耗(L/100km)',
  `attribute_14` varchar(200) DEFAULT NULL COMMENT '工信部综合油耗(L/100km)',
  `attribute_15` varchar(200) DEFAULT NULL COMMENT '实测离地间隙(mm)',
  `attribute_16` varchar(200) DEFAULT NULL COMMENT '整车质保',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车型基本配置信息';



DROP TABLE IF EXISTS `t_car_body`;
CREATE TABLE `t_car_body` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '长度(mm)',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '宽度(mm)',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '高度(mm)',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '轴距(mm)',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '前轮距(mm)',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '后轮距(mm)',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '最小离地间隙(mm)',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '整备质量(kg)',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '车身结构',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '车门数(个)',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '座位数(个)',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '油箱容积(L)',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT '行李厢容积(L)',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车身配置信息';




DROP TABLE IF EXISTS `t_chassis`;
CREATE TABLE `t_chassis` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '驱动方式',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '前悬架类型',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '后悬架类型',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '助力类型',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '车体结构',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='底盘配置信息';




DROP TABLE IF EXISTS `t_control_config`;
CREATE TABLE `t_control_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT 'ABS防抱死',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '制动力分配(EBD/CBC等)',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '刹车辅助(EBA/BAS/BA等)',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '牵引力控制(ASR/TCS/TRC等)',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '车身稳定控制(ESC/ESP/DSC等)',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '上坡辅助',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '自动驻车',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '陡坡缓降',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '可变悬架',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '空气悬架',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '可变转向比',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '前桥限滑差速器/差速锁',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT '中央差速器锁止功能',
  `attribute_14` varchar(200) DEFAULT NULL COMMENT '后桥限滑差速器/差速锁',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='操控配置信息';



DROP TABLE IF EXISTS `t_engine`;
CREATE TABLE `t_engine` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '发动机型号',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '排量(mL)',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '排量(L)',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '进气形式',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '气缸排列形式',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '气缸数(个)',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '每缸气门数(个)',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '压缩比',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '配气机构',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '缸径(mm)',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '行程(mm)',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '最大马力(Ps)',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT '最大功率(kW)',
  `attribute_14` varchar(200) DEFAULT NULL COMMENT '最大功率转速(rpm)',
  `attribute_15` varchar(200) DEFAULT NULL COMMENT '最大扭矩(N·m)',
  `attribute_16` varchar(200) DEFAULT NULL COMMENT '最大扭矩转速(rpm)',
  `attribute_17` varchar(200) DEFAULT NULL COMMENT '发动机特有技术',
  `attribute_18` varchar(200) DEFAULT NULL COMMENT '燃料形式',
  `attribute_19` varchar(200) DEFAULT NULL COMMENT '燃油标号',
  `attribute_20` varchar(200) DEFAULT NULL COMMENT '供油方式',
  `attribute_21` varchar(200) DEFAULT NULL COMMENT '缸盖材料',
  `attribute_22` varchar(200) DEFAULT NULL COMMENT '缸体材料',
  `attribute_23` varchar(200) DEFAULT NULL COMMENT '环保标准',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='发动机配置信息';




DROP TABLE IF EXISTS `t_exter_config`;
CREATE TABLE `t_exter_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '电动天窗',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '全景天窗',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '运动外观套件',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '铝合金轮圈',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '电动吸合门',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '侧滑门',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '电动后备厢',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '感应后备厢',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '车顶行李架',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='外部配置信息';




DROP TABLE IF EXISTS `t_glass_config`;
CREATE TABLE `t_glass_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '前/后电动车窗',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '车窗防夹手功能',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '防紫外线/隔热玻璃',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '后视镜电动调节',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '后视镜加热',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '内/外后视镜自动防眩目',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '后视镜电动折叠',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '后视镜记忆',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '后风挡遮阳帘',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '后排侧遮阳帘',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '后排侧隐私玻璃',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '遮阳板化妆镜',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT '后雨刷',
  `attribute_14` varchar(200) DEFAULT NULL COMMENT '感应雨刷',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='玻璃/后视镜配置信息';



DROP TABLE IF EXISTS `t_inter_config`;
CREATE TABLE `t_inter_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '真皮方向',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '方向盘调节',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '方向盘电动调节',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '多功能方向盘',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '方向盘换挡',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '方向盘加热',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '方向盘记忆',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '定速巡航',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '前/后驻车雷达',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '倒车视频影像',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '行车电脑显示屏',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '全液晶仪表盘',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT 'HUD抬头数字显示',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='内部配置信息';




DROP TABLE IF EXISTS `t_light_config`;
CREATE TABLE `t_light_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '近光灯',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '远光灯',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '日间行车灯',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '自适应远近光',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '自动头灯',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '转向辅助灯',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '转向头灯',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '前雾灯',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '大灯高度可调',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '大灯清洗装置',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '车内氛围灯',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='灯光配置信息';




DROP TABLE IF EXISTS `t_media_config`;
CREATE TABLE `t_media_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT 'GPS导航系统',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '定位互动服务',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '中控台彩色大屏',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '蓝牙/车载电话',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '车载电视',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '后排液晶屏',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '220V/230V电源',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '外接音源接口',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT 'CD支持MP3/WMA',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '多媒体系统',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '扬声器品牌',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '扬声器数量',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='多媒体配置信息';




DROP TABLE IF EXISTS `t_safety_device`;
CREATE TABLE `t_safety_device` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '主/副驾驶座安全气囊',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '前/后排侧气囊',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '前/后排头部气囊(气帘)',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '膝部气囊',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '胎压监测装置',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '零胎压继续行驶',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '安全带未系提示',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT 'ISOFIX儿童座椅接口',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '发动机电子防盗',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '车内中控锁',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '遥控钥匙',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '无钥匙启动系统',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT '无钥匙进入系统',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='安全装置信息';




DROP TABLE IF EXISTS `t_seat_config`;
CREATE TABLE `t_seat_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '座椅材质',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '运动风格座椅',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '座椅高低调节',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '腰部支撑调节',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '肩部支撑调节',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '主/副驾驶座电动调节',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '第二排靠背角度调节',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '第二排座椅移动',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '后排座椅电动调节',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '电动座椅记忆',
  `attribute_11` varchar(200) DEFAULT NULL COMMENT '前/后排座椅加热',
  `attribute_12` varchar(200) DEFAULT NULL COMMENT '前/后排座椅通风',
  `attribute_13` varchar(200) DEFAULT NULL COMMENT '前/后排座椅按摩',
  `attribute_14` varchar(200) DEFAULT NULL COMMENT '第三排座椅',
  `attribute_15` varchar(200) DEFAULT NULL COMMENT '后排座椅放倒方式',
  `attribute_16` varchar(200) DEFAULT NULL COMMENT '前/后中央扶手',
  `attribute_17` varchar(200) DEFAULT NULL COMMENT '后排杯架',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='座椅配置信息';



DROP TABLE IF EXISTS `t_tech_config`;
CREATE TABLE `t_tech_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '自动泊车入位',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '发动机启停技术',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '并线辅助',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '车道偏离预警系统',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '主动刹车/主动安全系统',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '整体主动转向系统',
  `attribute_7`  varchar(200) DEFAULT NULL COMMENT '夜视系统',
  `attribute_8`  varchar(200) DEFAULT NULL COMMENT '中控液晶屏分屏显示',
  `attribute_9`  varchar(200) DEFAULT NULL COMMENT '自适应巡航',
  `attribute_10` varchar(200) DEFAULT NULL COMMENT '全景摄像头',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='高科技配置信息';




DROP TABLE IF EXISTS `t_transmission`;
CREATE TABLE `t_transmission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '简称',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '挡位个数',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '变速箱类型',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='变速箱信息';



DROP TABLE IF EXISTS `t_wheel_info`;
CREATE TABLE `t_wheel_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand ID, F:',
  `brand_id` bigint(20) unsigned NOT NULL COMMENT 'car_brand_detail ID, F:',
  `car_id` int(11) unsigned NOT NULL COMMENT '数据id, F:',

  `attribute_1`  varchar(200) DEFAULT NULL COMMENT '前制动器类型',
  `attribute_2`  varchar(200) DEFAULT NULL COMMENT '后制动器类型',
  `attribute_3`  varchar(200) DEFAULT NULL COMMENT '驻车制动类型',
  `attribute_4`  varchar(200) DEFAULT NULL COMMENT '前轮胎规格',
  `attribute_5`  varchar(200) DEFAULT NULL COMMENT '后轮胎规格',
  `attribute_6`  varchar(200) DEFAULT NULL COMMENT '备胎规格',

  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='车轮制动信息';

