#!/bin/sh
echo "--------start exhibition registration------->>"
export JAVA_HOME=/data-ray/app/jdk1.8.0_121
export PATH=$JAVA_HOME/bin:$PATH
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
cpid=`jps -v| grep exhibition-register-server `
if [ -n "$cpid" ]; then
  echo $cpid | cut -d " " -f1 | xargs kill -9
fi
nohup java -Did=exhibition-register-server -jar target/royalnu-demo-exhibition-registration-0.0.1-SNAPSHOT-fat.jar -conf target/local.json > log.file 2>&1 &