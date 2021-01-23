package pl.gregorymartin.newsportal.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ErrorDetails {

    private Date timestamp;
    private List<String> messages;
    private String causePath;

    public ErrorDetails(Date timestamp, List<String> messages, String causePath) {
        this.timestamp = timestamp;
        this.messages = messages;
        this.causePath = causePath;
    }
}
