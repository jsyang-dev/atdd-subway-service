package nextstep.subway.path.domain;

public class DistancePolicyForShort extends DistancePolicy {

    public DistancePolicyForShort(int distance) {
        super(distance);
    }

    @Override
    public int calculate() {
        return BASE_FARE;
    }
}
