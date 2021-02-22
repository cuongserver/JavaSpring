package demo.spring.models.mapping;

public interface UserMapping {
    String getId();
    String getEmail();
    String getLoginName();
    String getDisplayName();
    boolean getMfaEnabled();
    int getVersion();
}
