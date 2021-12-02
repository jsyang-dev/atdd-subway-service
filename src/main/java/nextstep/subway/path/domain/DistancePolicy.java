package nextstep.subway.path.domain;

public abstract class DistancePolicy {

    protected static final int BASE_FARE = 1250;
    private static final int ADD_FARE = 100;

    protected final int distance;

    public DistancePolicy(int distance) {
        this.distance = distance;
    }

    public abstract int calculate();

    protected int calculateOverFare(int overDistance, int perKm) {
        return (int) ((Math.ceil((overDistance - 1) / perKm) + 1) * ADD_FARE);
    }
}
