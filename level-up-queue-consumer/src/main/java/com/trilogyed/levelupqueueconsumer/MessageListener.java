package com.trilogyed.levelupqueueconsumer;

import com.trilogyed.levelupqueueconsumer.model.LevelUp;
import com.trilogyed.levelupqueueconsumer.util.feign.LevelUpClient;
import com.trilogyed.levelupqueueconsumer.util.messages.LevelUpEntry;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {
    @Autowired
    LevelUpClient levelUpClient;

    @RabbitListener(queues = LevelUpQueueConsumerApplication.QUEUE_NAME)
    public void receiveLevelUp(LevelUpEntry levelUpEntry) {
        System.out.println("Incoming message: " + levelUpEntry.toString());
        try {
            if (levelUpEntry.getLevelUpId() == 0) {
                LevelUp levelUp = new LevelUp();
                levelUp.setCustomerId(levelUpEntry.getCustomerId());
                levelUp.setPoints(levelUpEntry.getPoints());
                levelUp.setMemberDate(levelUp.getMemberDate());
                levelUpClient.createLevelUp(levelUp);
                System.out.println("Saved level up entry: " + levelUp.toString());
            } else {
                LevelUp levelUp = new LevelUp();
                levelUp.setLevelUpId(levelUpEntry.getLevelUpId());
                levelUp.setCustomerId(levelUpEntry.getCustomerId());
                levelUp.setPoints(levelUpEntry.getPoints());
                levelUp.setMemberDate(levelUp.getMemberDate());
                levelUpClient.updateLevelUp(levelUp);
                System.out.println("Saved level up entry: " + levelUp.toString());
            }
        } catch (Exception e) {
            System.out.println("There is an exception: " + e.getMessage());
        }
    }
}
