package com.adventurer.demo_adventure.component.kafka;

import com.adventurer.demo_adventure.model.MarketPlaceModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.springframework.beans.factory.annotation.Qualifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Slf4j
@Component
public class KafkaProducerComponent {
    private KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerComponent(@Qualifier("registerKafkaTemplate") KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendData(MarketPlaceModel marketPlaceModel, String topic) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(marketPlaceModel);

            Random random = new Random();
            List<PartitionInfo> partitionInfoList = this.kafkaTemplate.partitionsFor(topic);
            int index = random.nextInt(partitionInfoList.size());
            int partition = partitionInfoList.get(index).partition();

//            boolean trueOrFalse = random.nextBoolean();
//            int num = trueOrFalse ? 1 : 0;

            this.kafkaTemplate.send(topic, partition,null, message)
                    .whenComplete((result, throwable) -> {
                        if (throwable == null) {
                            log.info("Kafka sent to {} done: {}",
                                    result.getRecordMetadata().topic(), message);
                        } else {
                            log.error("Kafka send exception: {}", throwable.getMessage());
                        }
                    });
        } catch (Exception e) {
            log.error("Error serializing MarketPlaceModel to JSON: {}", e.getMessage());
        }
    }
}

