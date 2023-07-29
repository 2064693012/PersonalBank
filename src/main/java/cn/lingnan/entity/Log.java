package cn.lingnan.entity;
import java.sql.Timestamp;
import java.util.Date;

public class Log {
    private int log_id;
    private String cardID;
    private String log_content;
    private Timestamp log_time;

    public Log() {
        // Default constructor
    }

    public Log(int log_id, String cardID, String log_content, Timestamp log_time) {
        this.log_id = log_id;
        this.cardID = cardID;
        this.log_content = log_content;
        this.log_time = log_time;
    }

    public Log(String cardID, String log_content) {
        this.cardID = cardID;
        this.log_content = log_content;
    }
    public int getLog_id() {
        return log_id;
    }

    public void setLog_id(int log_id) {
        this.log_id = log_id;
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getLog_content() {
        return log_content;
    }

    public void setLog_content(String log_content) {
        this.log_content = log_content;
    }

    public Timestamp getLog_time() {
        return log_time;
    }

    public void setLog_time(Timestamp log_time) {
        this.log_time = log_time;
    }
}
