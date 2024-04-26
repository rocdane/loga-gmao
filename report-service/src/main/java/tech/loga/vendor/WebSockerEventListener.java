package tech.loga.vendor;

import com.loga.reportingservice.messenger.factory.ChatMessage;
import com.loga.reportingservice.messenger.factory.MessageType;
import com.loga.reportingservice.vendor.io.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
@RequiredArgsConstructor
public class WebSockerEventListener {

    private final SimpMessageSendingOperations messageTemplate;

    @EventListener
    public void handleWebSocketDisconnectListner(
            SessionDisconnectEvent event
    ){
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor
                .getSessionAttributes()
                .get("username");

        if (username!=null){
            Logger.info(String.format("User disconnected: {}",username));

            var chatMessage = ChatMessage.builder()
                    .type(MessageType.LEAVE)
                    .sender(username)
                    .build();

            messageTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}
