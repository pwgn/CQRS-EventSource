package dude.chrisp.cqrseventsource.readmodel.carmanager.dto;

public class CarDto {
    public int version;
    public String id;
    public int rate;
    public String carModel;
    public boolean available;

    public CarDto() {}

    public CarDto(int version, String id, int rate, String carModel, boolean available) {
        this.version = version;
        this.id = id;
        this.rate = rate;
        this.carModel = carModel;
        this.available = available;
    }
}