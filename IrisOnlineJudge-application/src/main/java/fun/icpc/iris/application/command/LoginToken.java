package fun.icpc.iris.application.command;
import org.apache.commons.lang3.StringUtils;

import java.util.Optional;

public record LoginToken(
        String handler,
        String loginUUID
) {

    public String getContent() {
        return handler + "_" + loginUUID;
    }

    public static Optional<LoginToken> fromContent(String content) {
        if(StringUtils.isEmpty(content) || !content.contains("_")) {
            return Optional.empty();
        }
        String[] split = content.split("_");
        if(split.length != 2) {
            return Optional.empty();
        }
        return Optional.of(new LoginToken(split[0], split[1]));
    }
}
