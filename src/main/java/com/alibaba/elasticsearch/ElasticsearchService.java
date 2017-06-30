package com.alibaba.elasticsearch;

import com.alibaba.common.model.YuGongContext;
import com.alibaba.common.model.record.IncrementOpType;
import com.alibaba.common.utils.thread.NamedThreadFactory;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.InetAddress;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by wzy on 2017/6/30.
 */
public class ElasticsearchService {
    private YuGongContext context;
    private ThreadPoolExecutor executor;
    private String executorName = this.getClass().getSimpleName() + "-executor";
    private int threadSize = 5;
    private boolean concurrent;
    private DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
    private String fullTopic = "OracleFull";
    private String incTopic = "OracleInc";
    private String insertTag = "insert";
    private String updateTag = "update";
    private String deleteTag = "delete";
    private TransportClient client;
    private String elasticsearchClusterAddr;

    public ElasticsearchService(YuGongContext context) {
        this.context = context;
        this.elasticsearchClusterAddr = context.getElasticClusterAddr();

        executor = new ThreadPoolExecutor(threadSize,
                threadSize,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue(threadSize * 2),
                new NamedThreadFactory(executorName),
                new ThreadPoolExecutor.CallerRunsPolicy());
        consumer.setNamesrvAddr(context.getRocketMQNameServerAddr());

    }

    public void start() throws Exception {
        consumer.subscribe(fullTopic, "");
        consumer.subscribe(incTopic, "D || I || U");
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName()
                        + "Receive New Messages: " + msgs.size());

                MessageExt msg = msgs.get(0);
                if (msg.getTopic().equals(fullTopic)) {
                    doBatchInsert(new String(msg.getBody()));
                } else if (msg.getTopic().equals(incTopic)) {
                    if (msg.getTags() != null && msg.getTags().equals(IncrementOpType.I.name())) {
                        insert(new String(msg.getBody()));
                    } else if (msg.getTags() != null && msg.getTags().equals(IncrementOpType.U.name())) {
                        update(new String(msg.getBody()));
                    } else if (msg.getTags() != null && msg.getTags().equals(IncrementOpType.D.name())) {
                        delete(new String(msg.getBody()));
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        client = TransportClient.builder().build()
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(elasticsearchClusterAddr), 9300));

    }

    public void stop() {
        consumer.shutdown();
        client.close();
    }

    private void doBatchInsert(String msgs) {
        System.out.println(msgs);
    }

    private void insert(String msg) {
        System.out.println(msg);
    }

    public void update(String msg) {
        System.out.println(msg);
    }

    public void delete(String msg) {
        System.out.println(msg);
    }
}
