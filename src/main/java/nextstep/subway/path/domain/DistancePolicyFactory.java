package nextstep.subway.path.domain;

import nextstep.subway.line.domain.Distance;

public class DistancePolicyFactory {

    public static DistancePolicy from(Distance distance) {
        if (distance.greaterThan(50)) {
            return new DistancePolicyForLong(distance.getDistance());
        }
        if (distance.greaterThan(10)) {
            return new DistancePolicyForMedium(distance.getDistance());
        }
        return new DistancePolicyForShort(distance.getDistance());
    }
}
