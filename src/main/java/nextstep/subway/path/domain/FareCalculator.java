package nextstep.subway.path.domain;

import nextstep.subway.line.domain.Distance;

public class FareCalculator {

    private final Distance distance;

    public FareCalculator(int distance) {
        this.distance = new Distance(distance);
    }

    public int calculate() {
        DistancePolicy distancePolicy = DistancePolicyFactory.from(distance);

        // enum으로 변경
        // 계산 기준 값만 관리
        // calculator() -> over 거리에 따라 값 추가


        return distancePolicy.calculate();
    }
}
