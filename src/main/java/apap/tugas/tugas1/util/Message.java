package apap.tugas.tugas1.util;

import static apap.tugas.tugas1.util.Message.Type.*;

public class Message {

    private String title;

    private String content;

    private String type;

    public enum Type {SUCCESS, DANGER, WARNING, INFO}

    public Message(String title, String content, Type type) {
        this.title = title;
        this.content = content;
        this.type = getType(type);
    }

    public Message(String title, String content) {
        this.title = title;
        this.content = content;
        this.type = getType(INFO);
    }

    private String getType(Type t) {
        switch (t) {
            case INFO:
                return "info";
            case SUCCESS:
                return "success";
            case DANGER:
                return "danger";
            case WARNING:
                return "warning";
            default:
                return "info";
        }
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
