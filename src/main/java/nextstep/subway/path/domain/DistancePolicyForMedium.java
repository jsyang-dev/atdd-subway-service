package nextstep.subway.path.domain;

public class DistancePolicyForMedium extends DistancePolicy {

    public static final int BASE_DISTANCE = 10;
    private static final int PER_KM = 5;

    public DistancePolicyForMedium(int distance) {
        super(distance);
    }

    @Override
    public int calculate() {
        return new DistancePolicyForShort(distance).calculate() +
                calculateOverFare(distance - BASE_DISTANCE, PER_KM);
    }
}
