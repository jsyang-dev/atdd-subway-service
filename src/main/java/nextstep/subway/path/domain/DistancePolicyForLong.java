package nextstep.subway.path.domain;

public class DistancePolicyForLong extends DistancePolicy {

    private static final int BASE_DISTANCE = 50;
    private static final int PER_KM = 8;

    public DistancePolicyForLong(int distance) {
        super(distance);
    }

    @Override
    public int calculate() {
        return new DistancePolicyForMedium(BASE_DISTANCE - DistancePolicyForMedium.BASE_DISTANCE).calculate() +
                calculateOverFare(distance - BASE_DISTANCE, PER_KM);
    }
}
