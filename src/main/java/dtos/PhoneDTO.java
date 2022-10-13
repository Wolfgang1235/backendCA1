package dtos;

public class PhoneDTO {

    private int id;
    private final String number;
    private final String description;
    private boolean isPrivate;

    public PhoneDTO(String number, String description, boolean isPrivate) {
        this.number = number;
        this.description = description;
        this.isPrivate = isPrivate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                ", isPrivate=" + isPrivate +
                '}';
    }
}
