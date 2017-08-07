DROP TABLE IF EXISTS `bluePay_paymentMethods`;
CREATE TABLE `bluePay_paymentMethods` (
  `id`              INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `paymentMethodId` VARCHAR(255) NOT NULL UNIQUE,
  `transactionId`   VARCHAR(255) NOT NULL,
  INDEX `INDEX_bluePay_paymentMethods_ON_paymentMethodId`(`paymentMethodId`)
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_bin;

DROP TABLE IF EXISTS `bluePay_credentials`;
CREATE TABLE `bluePay_credentials` (
  `id`        INT(11)      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `tenantId`  VARCHAR(255) NOT NULL UNIQUE,
  `accountId` VARCHAR(255),
  `secretKey` VARCHAR(255),
  `test`      BOOLEAN,
  INDEX `INDEX_bluePay_credentials_ON_tenantId`(`tenantId`),
  INDEX `INDEX_bluePay_credentials_ON_accountId`(`accountId`)
)
  ENGINE = InnoDB
  CHARACTER SET utf8
  COLLATE utf8_bin;