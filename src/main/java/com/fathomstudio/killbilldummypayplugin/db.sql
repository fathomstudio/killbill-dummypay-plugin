DROP TABLE IF EXISTS `dummyPay_paymentMethods`;
CREATE TABLE `dummyPay_paymentMethods` (
  `id`              INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `paymentMethodId` VARCHAR(255) NOT NULL UNIQUE,
  `gatewayToken`   VARCHAR(255) NOT NULL,
  INDEX `INDEX_dummyPay_paymentMethods_ON_paymentMethodId`(`paymentMethodId`)
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_bin;