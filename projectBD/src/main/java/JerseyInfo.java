public class JerseyInfo {
    private int jersey_id;
    private int club_id;
    private String name;
    private String brand;
    private String color;
    private String description;

    JerseyInfo(int club_id, String name, String brand, String color, String description){
        this.club_id = club_id;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.description = description;
    }

    JerseyInfo(int jersey_id, int club_id, String name, String brand, String color, String description){
        this.jersey_id = jersey_id;
        this.club_id = club_id;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.description = description;
    }

    public int getJersey_id() {
        return jersey_id;
    }

    public int getClub_id() {
        return club_id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getColor() {
        return color;
    }

    public String getDescription() {
        return description;
    }

    public void setJersey_id(int jersey_id) {
        this.jersey_id = jersey_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColor(String color){
        this.color = color;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
