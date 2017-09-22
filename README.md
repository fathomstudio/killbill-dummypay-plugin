# Kill Bill dummypayPay Plugin

dummypay Kill Bill plugin. This payment plugin will process all ACH and credit card payments.

## Building
`mvn clean install`

## Installing
Copy the JAR (`target/killbill-dummypay-plugin-<version>.jar`) to the Kill Bill path `/var/lib/killbill/bundles/plugins/java/killbill-dummypay-plugin/<version>/killbill-dummypay-plugin-<version>.jar`. This path can change with the `org.killbill.osgi.bundle.install.dir` property.