package manfred.end;

// 目标DTO
public class UserDTO {
    private Long UserId;
    private String name;
    private String emailAddress;
    private String createdAt;
    private AddressDTO address;

    private Long Id;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        this.UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + UserId +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", address=" + address +
                '}';
    }
}
