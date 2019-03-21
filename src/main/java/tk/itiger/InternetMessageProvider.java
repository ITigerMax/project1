package tk.itiger;

import org.springframework.stereotype.Component;

@Component
public class InternetMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello from the Internet!";
    }
}
