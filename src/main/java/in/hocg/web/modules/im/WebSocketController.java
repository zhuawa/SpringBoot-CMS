package in.hocg.web.modules.im;

import com.google.gson.Gson;
import in.hocg.web.modules.im.packets.MessagePacket;
import in.hocg.web.modules.im.packets.accept.AcceptType;
import in.hocg.web.modules.im.processor.MessageProcessor;
import in.hocg.web.modules.im.processor.UserToUserProcessor;
import in.hocg.web.modules.system.service.notify.NotifyService;
import in.hocg.web.modules.system.service.notify.UserNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2017/12/18.
 * email: hocgin@gmail.com
 */
@Controller
public class WebSocketController {
    public static final String TOPIC_MESSAGE = "/topic/message";
    public static final String QUEUE_MESSAGE = "/queue/messages";
    
    private SimpMessagingTemplate simpMessagingTemplate;
    private UserNotifyService userNotifyService;
    private NotifyService notifyService;
    private Gson gson;
    private Map<Byte, MessageProcessor<?>> processors = new HashMap<>();
    
    @Autowired
    public WebSocketController(SimpMessagingTemplate simpMessagingTemplate,
                               UserNotifyService userNotifyService,
                               NotifyService notifyService,
                               Gson gson,
                               
                               UserToUserProcessor userToUserProcessor) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.userNotifyService = userNotifyService;
        this.notifyService = notifyService;
        this.gson = gson;
    
        processors.put(AcceptType.USER_TO_USER, userToUserProcessor);
    }
    
    
    @MessageMapping("/reply") // 服务端接收入口
    public void reply(String value, Principal principal) throws Exception {
        MessagePacket packet = gson.fromJson(value, MessagePacket.class);
        MessageProcessor<?> processor = processors.get(packet.getType());
        if (processor != null) {
            processor.process(value);
            return;
        }
        // 处理未知消息
//        System.out.println("内容 " + value);
        this.simpMessagingTemplate.convertAndSendToUser(principal.getName(), QUEUE_MESSAGE, "无法进行处理");
//        this.simpMessagingTemplate.convertAndSend("/ws/queue/user/messages-user" +principal.getName(), "无法进行处理");
    }
    
}
