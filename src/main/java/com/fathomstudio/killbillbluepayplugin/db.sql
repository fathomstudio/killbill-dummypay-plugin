DROP TABLE IF EXISTS `bluePay_paymentMethods`;
CREATE TABLE `bluepay_paymentMethods` (
  `id`              INT(11)      NOT NULL AUTO_INCREMENT,
  `paymentMethodId` VARCHAR(255) NOT NULL,
  `transactionId`   VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `INDEX_bluePay_paymentMethods_ON_paymentMethodId`(`paymentMethodId`)
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_bin;

DROP TABLE IF EXISTS `bluePay_credentials`;
CREATE TABLE `bluePay_credentials` (
  `id`        INT(11)      NOT NULL AUTO_INCREMENT,
  `tenantId`  VARCHAR(255) NOT NULL,
  `accountId` VARCHAR(255),
  `secretKey` VARCHAR(255),
  `test`   BOOLEAN,
  PRIMARY KEY (`id`),
  INDEX `INDEX_bluePay_credentials_ON_tenantId`(`tenantId`),
  INDEX `INDEX_bluePay_credentials_ON_accountId`(`accountId`)
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_bin;