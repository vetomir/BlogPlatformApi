package pl.gregorymartin.newsportal.appUser;


import lombok.Getter;

@Getter
enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String userRole;

    Role(String userRole) {
        this.userRole = userRole;
    }
}
