package manfred.end.map.struct.example1;

public class Boot {
    public static void main(String[] args) {
        Car car = new Car();
        car.setMake("make");
        car.setNumberOfSeats(13);
        car.setType(CarType.SEDAN);

        //when
        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        System.out.println(carDto);
    }
}
