package net.kiberion.audio;

import java.util.Map;
import java.util.HashMap;

import net.kiberion.swampmachine.api.common.RealtimeUpdatable;

/**
 * Channel container
 * @author caryoscelus
 */
public class Mixer implements RealtimeUpdatable {
    /**
     * Creates new channel
     */
    public Channel newChannel(String name) {
        Channel channel = new Channel();
        channels.put(name, channel);
        return channel;
    }
    
    /**
     * Returns existing channel
     * TODO: proper error-handling
     */
    public Channel getChannel(String name) {
        return channels.get(name);
    }
    
    @Override
    public void update(float delta) {
        for (Channel channel : channels.values()) {
            channel.update(delta);
        }
    }
    
    private Map<String, Channel> channels = new HashMap<>();
}
