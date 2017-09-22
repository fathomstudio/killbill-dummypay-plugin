#!/usr/bin/env bash

set -e

host="$1"

if [ -z "$host" ]; then
	echo "missing host argument"
	exit 1
fi

./build.sh

outputJar="target/killbill-dummypay-plugin-0.0.1-SNAPSHOT-jar-with-dependencies.jar"
scp $outputJar stack@"$host":coconut-stack/killbill/killbill-dummypay-plugin.jar
cp $outputJar /home/chris13524/programming/coconut-stack/killbill/killbill-dummypay-plugin.jar

outputSql="src/main/java/com/fathomstudio/killbilldummypayplugin/db.sql"
scp $outputSql stack@"$host":coconut-stack/database/dummyPay.sql
cp $outputSql /home/chris13524/programming/coconut-stack/database/dummyPay.sql