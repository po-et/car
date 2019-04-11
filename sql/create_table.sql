DROP TABLE IF EXISTS `car_brand`;
CREATE TABLE `post_header_cond_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `brand` varchar(100) NOT NULL COMMENT '品牌名称',
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车品牌表';



DROP TABLE IF EXISTS `car_brand_details`;
CREATE TABLE `post_header_cond_item` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(20) unsigned NOT NULL COMMENT '父品牌ID,F:',
  `parent_brand` varchar(100) NOT NULL COMMENT '父品牌',
  `car_brand` varchar(100) NOT NULL COMMENT '父品牌',
  `car_type` varchar(100) NOT NULL COMMENT '父品牌',
  `crtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
  `uptime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='汽车子品牌表';