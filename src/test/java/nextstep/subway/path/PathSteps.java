package nextstep.subway.path;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import nextstep.subway.station.dto.StationResponse;
import nextstep.subway.utils.RestAssuredTestUtils;

public class PathSteps {

    public static ExtractableResponse<Response> 최단_경로_조회_요청(StationResponse sourceStation, StationResponse targetStation) {
        return 최단_경로_조회_요청(sourceStation.getId(), targetStation.getId());
    }

    public static ExtractableResponse<Response> 최단_경로_조회_요청(Long sourceStationId, Long targetStationId) {
        return RestAssuredTestUtils.get("/paths?source={source}&target={target}", sourceStationId, targetStationId);
    }
}
