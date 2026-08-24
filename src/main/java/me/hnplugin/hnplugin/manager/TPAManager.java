package me.hnplugin.hnplugin.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class TPAManager {
    public enum TPARequestType {
        TPA, TPAHERE
    }

    public static class TPARequest {
        public final UUID requester;
        public final String requesterName;
        public final UUID target;
        public final TPARequestType type;
        public final long expiry;

        public TPARequest(UUID requester, String requesterName, UUID target, TPARequestType type, long timeoutSeconds) {
            this.requester = requester;
            this.requesterName = requesterName;
            this.target = target;
            this.type = type;
            this.expiry = System.currentTimeMillis() + timeoutSeconds * 1000;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiry;
        }
    }

    private static final Map<UUID, Map<UUID, TPARequest>> requests = new HashMap<>();

    public static void sendRequest(Player requester, Player target, TPARequestType type, long timeoutSeconds) {
        requests.computeIfAbsent(target.getUniqueId(), k -> new HashMap<>())
                .put(requester.getUniqueId(), new TPARequest(requester.getUniqueId(), requester.getName(), target.getUniqueId(), type, timeoutSeconds));
    }

    public static TPARequest getRequest(Player target) {
        Map<UUID, TPARequest> targetRequests = requests.get(target.getUniqueId());
        if (targetRequests == null || targetRequests.isEmpty()) {
            return null;
        }
        TPARequest latest = null;
        Iterator<Map.Entry<UUID, TPARequest>> it = targetRequests.entrySet().iterator();
        while (it.hasNext()) {
            TPARequest req = it.next().getValue();
            if (req.isExpired()) {
                it.remove();
                continue;
            }
            if (latest == null || req.expiry > latest.expiry) {
                latest = req;
            }
        }
        if (targetRequests.isEmpty()) {
            requests.remove(target.getUniqueId());
        }
        return latest;
    }

    public static TPARequest getRequest(Player target, String requesterName) {
        Map<UUID, TPARequest> targetRequests = requests.get(target.getUniqueId());
        if (targetRequests == null || targetRequests.isEmpty()) {
            return null;
        }
        TPARequest found = null;
        Iterator<Map.Entry<UUID, TPARequest>> it = targetRequests.entrySet().iterator();
        while (it.hasNext()) {
            TPARequest req = it.next().getValue();
            if (req.isExpired()) {
                it.remove();
                continue;
            }
            if (req.requesterName.equalsIgnoreCase(requesterName)) {
                found = req;
            }
        }
        if (targetRequests.isEmpty()) {
            requests.remove(target.getUniqueId());
        }
        return found;
    }

    public static TPARequest removeRequest(Player target, UUID requester) {
        Map<UUID, TPARequest> targetRequests = requests.get(target.getUniqueId());
        if (targetRequests == null) {
            return null;
        }
        TPARequest req = targetRequests.remove(requester);
        if (targetRequests.isEmpty()) {
            requests.remove(target.getUniqueId());
        }
        return req;
    }
}
