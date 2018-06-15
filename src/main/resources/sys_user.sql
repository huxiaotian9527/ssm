CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `userName` varchar(12) NOT NULL COMMENT '登陆用户名',
  `name` varchar(12) NOT NULL COMMENT '姓名',
  `password` varchar(60) NOT NULL COMMENT '登陆密码',
  `phoneNo` varchar(11) NOT NULL COMMENT '用户手机号',
  `intNum2` tinyint(2) unsigned NOT NULL COMMENT '2位整数',
  `intNum5` smallint(5) unsigned NOT NULL COMMENT '5位整数',
  `amount` decimal(16,2) unsigned DEFAULT NULL COMMENT '金额(元)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';