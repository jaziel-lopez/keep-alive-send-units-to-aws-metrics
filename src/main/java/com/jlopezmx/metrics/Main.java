package com.jlopezmx.metrics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.cloudwatch.CloudWatchClient;
import software.amazon.awssdk.services.cloudwatch.model.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
  /** Logger **/
  static Logger logger = LoggerFactory.getLogger(Main.class);
  /** TODO: use your own namespace**/
  final static String NAMESPACE = "";
  /** TODO: use your own metrics**/
  final static String METRIC_NAME_SERVICE_EXCEPTIONS = "";
  /** TODO: use your own metrics**/
  final static String METRIC_NAME_CRONJOB_FAIL_RECORDS= "";
  /** TODO: use your own dimensions**/
  final static String DIMENSION_NAME_SERVICE_EXCEPTIONS = "";
  /** TODO: use your own dimensions**/
  final static String DIMENSION_NAME_CRONJOB_FAIL_RECORDS = "";
  /** YTD: year to date (accumulative value) **/
  final static String DIMENSION_VALUE = "YTD";
  /** Randomizer **/
  static Random units = new Random();
  /** Upper value used by randomize**/
  final static Integer MAX_BOUND_RANDOM = 50;
  /** Lower value used by randomize**/
  final static Integer MIN_BOUND_RANDOM = 1;
  /** Program starts here...**/
  public static void main(String[] args) throws InterruptedException{
    while(true) {
      logger.info("hey all, posting data units to identified namespace/metric:");
      logger.info("{}/{}", NAMESPACE, METRIC_NAME_SERVICE_EXCEPTIONS);
      logger.info("{}/{}", NAMESPACE, METRIC_NAME_CRONJOB_FAIL_RECORDS);
      logger.info("===========");
      MetricDatum errorDataServiceExceptions = setData(
          METRIC_NAME_SERVICE_EXCEPTIONS,
          getDimension(DIMENSION_NAME_SERVICE_EXCEPTIONS));
  
      MetricDatum errorDataCronjobFailedRecords = setData(
          METRIC_NAME_CRONJOB_FAIL_RECORDS,
          getDimension(DIMENSION_NAME_CRONJOB_FAIL_RECORDS));
  
      logger.info("units to be send to metric {}: {}", METRIC_NAME_SERVICE_EXCEPTIONS, errorDataServiceExceptions.value());
      logger.info("units to be send to metric {}: {}", METRIC_NAME_CRONJOB_FAIL_RECORDS, errorDataCronjobFailedRecords.value());
  
      logger.info("creating metric data requests");
      
      PutMetricDataRequest errorServiceExceptionRequest = PutMetricDataRequest
          .builder()
          .namespace(NAMESPACE)
          .metricData(errorDataServiceExceptions).build();
  
      PutMetricDataRequest errorCronjobFailedRequest = PutMetricDataRequest
          .builder()
          .namespace(NAMESPACE)
          .metricData(errorDataCronjobFailedRecords).build();
  
      try {
        logger.info("sending data to metric: {}", METRIC_NAME_SERVICE_EXCEPTIONS);
        getClient().putMetricData(errorServiceExceptionRequest);
    
        logger.info("sending data to metric: {}", METRIC_NAME_CRONJOB_FAIL_RECORDS);
        getClient().putMetricData(errorCronjobFailedRequest);
    
        logger.info("completed sending data to metrics");
    
      } catch (CloudWatchException e) {
        
        logger.error(e.awsErrorDetails().errorMessage());
        
        System.exit(1);
      }
      logger.info("until next time...");
      logger.info("===========");
      TimeUnit.MINUTES.sleep(randomize(5,2));
    }
  }
  
  /**
   *
   * @return
   */
   private static CloudWatchClient getClient(){
     
     logger.info("acquiring cloudwatch client with default AWS credential provider");
     
     return CloudWatchClient.builder().build();
  }
  
  /**
   *
   * @param metricName
   * @param dimension
   * @return
   */
  static private MetricDatum setData(String metricName, Dimension dimension){
  
    logger.info("creating data point");
    
     return MetricDatum.builder().
        metricName(metricName)
        .unit(StandardUnit.NONE)
        .value(randomize(MAX_BOUND_RANDOM,MIN_BOUND_RANDOM).doubleValue())
        .dimensions(dimension)
        .build();
  }
  /**
   *
   * @param max
   * @param min
   * @return
   */
  static  private Integer randomize(Integer max, Integer min) {
    return units.nextInt((max-min)+min);
  }
  
  /**
   *
   * @param dimensionName
   * @return
   */
  static private Dimension getDimension(String dimensionName){
    logger.info("cloudwatch dimension identifier:{}", dimensionName);
    logger.info("cloudwatch dimension value:{}", DIMENSION_VALUE);
    return Dimension
        .builder()
        .name(dimensionName)
        .value(DIMENSION_VALUE)
        .build();
  }
}
