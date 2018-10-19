package apap.tugas.tugas1.util;

public class Message {

    private String title;

    private String content;

    private String type;

    public static final String MESSAGE_NAME = "message";

    public enum Type {SUCCESS, DANGER, WARNING, INFO}

    public Message(String title, String content, Type type) {
        this.title = title;
        this.content = content;
        this.type = getType(type);
    }

    public Message() {
        this("", "", Type.INFO);
    }

    public Message(String title, String content) {
        this(title, content, Type.INFO);
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setType(Type t) {
        this.type = getType(t);
    }
}
