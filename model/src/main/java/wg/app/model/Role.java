package wg.app.model;

public enum Role
{
    PATIENT("ROLE_PATIENT"),
    ADMIN("ROLE_ADMIN"),
    DOCTOR("ROLE_DOCTOR");

    private String fullName;

    Role(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }
}
