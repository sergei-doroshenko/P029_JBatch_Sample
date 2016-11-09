$ curl http://localhost:8080/batch-test/jbatch/rest/
$ curl http://localhost:8080/batch-test/jbatch/rest/start/simple-job

$ ./jboss-cli.sh --connect
[standalone@localhost:9990 /] data-source add --name=MyDS --driver-name=h2 --jndi-name=java:jboss/datasources/MyDS --user-name=sa --password=sa --connection-url=jdbc:h2:/tmp/myds;AUTO_SERVER=TRUE

[standalone@localhost:9990 /] /subsystem=datasources/data-source=MyDS:test-connection-in-pool
ction-in-pooltasources/data-source=MyDS:test-conne
{
    "outcome" => "success",
    "result" => [true]
}

$ java -cp ./modules/system/layers/base/com/h2database/h2/main/h2*.jar org.h2.tools.Shell -url "jdbc:h2:/tmp/myds;AUTO_SERVER=TRUE" -user sa -password sa

sql> CREATE TABLE forex (
     symbol VARCHAR(6) NOT NULL,
     ts TIMESTAMP NOT NULL,
     bid_open NUMERIC(10,3) NOT NULL,
     bid_high NUMERIC(10,3) NOT NULL,
     bid_low NUMERIC(10,3) NOT NULL,
     bid_close NUMERIC(10,3) NOT NULL,
     volume INTEGER NOT NULL,
     PRIMARY KEY(symbol, ts)
);

$ curl 'http://localhost:8080/batch-test/jbatch/rest/start/load-csv?symbol=USDJPY&resource=D:\batch_file_test\DAT_ASCII_USDJPY_M1_201504.csv'

csv2json
cause exception while try to load job definition from jar
javax.batch.operations.JobStartException: JBERET000601: Failed to get job xml file for job csvItemReader.xml