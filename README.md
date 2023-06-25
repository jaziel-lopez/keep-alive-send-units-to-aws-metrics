# keep-alive-send-units-to-aws-metrics
minimal java program that is keep alive until manual cancelled that is feeding aws custom metrics with random values every n-minutes.


```
[main] INFO com.jlopezmx.metrics.Main - hey all, posting data units to identified namespace/metric:
[main] INFO com.jlopezmx.metrics.Main - nsp/exceptions-metric
[main] INFO com.jlopezmx.metrics.Main - nsp/failed-records-metric
[main] INFO com.jlopezmx.metrics.Main - ===========
[main] INFO com.jlopezmx.metrics.Main - cloudwatch dimension identifier:exceptions-dimension
[main] INFO com.jlopezmx.metrics.Main - cloudwatch dimension value:YTD
[main] INFO com.jlopezmx.metrics.Main - creating data point
[main] INFO com.jlopezmx.metrics.Main - cloudwatch dimension identifier:failed-records-dimension
[main] INFO com.jlopezmx.metrics.Main - cloudwatch dimension value:YTD
[main] INFO com.jlopezmx.metrics.Main - creating data point
[main] INFO com.jlopezmx.metrics.Main - units to be send to metric exceptions-metric: 12.0
[main] INFO com.jlopezmx.metrics.Main - units to be send to metric failed-records-metric: 5.0
[main] INFO com.jlopezmx.metrics.Main - creating metric data requests
[main] INFO com.jlopezmx.metrics.Main - sending data to metric: exceptions-metric
[main] INFO com.jlopezmx.metrics.Main - acquiring cloudwatch client with default AWS credential provider
[main] INFO com.jlopezmx.metrics.Main - sending data to metric: failed-records-metric
[main] INFO com.jlopezmx.metrics.Main - acquiring cloudwatch client with default AWS credential provider
[main] INFO com.jlopezmx.metrics.Main - completed sending data to metrics
[main] INFO com.jlopezmx.metrics.Main - until next time...
[main] INFO com.jlopezmx.metrics.Main - ===========
```
