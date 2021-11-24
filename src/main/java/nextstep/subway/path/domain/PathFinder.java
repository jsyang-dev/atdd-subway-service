package nextstep.subway.path.domain;

import nextstep.subway.line.domain.Line;
import nextstep.subway.line.domain.Section;
import nextstep.subway.station.domain.Station;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.KShortestPaths;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

import java.util.List;
import java.util.Objects;

public class PathFinder {

    public static final String SAME_STATION_ERROR = "출발역과 도착역은 달라야 합니다.";
    public static final String NOT_CONTAINED_STATION_ERROR = "노선에 연결된 지하철역이어야 합니다.";
    private static final int SHORTEST_PATH_INDEX = 0;

    private final WeightedMultigraph<Station, DefaultWeightedEdge> graph;
    private final KShortestPaths<Station, DefaultWeightedEdge> paths;

    public PathFinder(List<Line> lines) {
        this.graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
        addVertexes(lines);
        addEdges(lines);
        this.paths = new KShortestPaths<>(graph, 100);
    }

    public Path findShortestPath(Station sourceStation, Station targetStation) {
        validateStations(sourceStation, targetStation);
        GraphPath<Station, DefaultWeightedEdge> shortestPath = findShortestPathFromPaths(sourceStation, targetStation);
        return new Path(shortestPath.getVertexList(), (int) shortestPath.getWeight());
    }

    private void addVertexes(List<Line> lines) {
        lines.stream()
                .flatMap(line -> line.getStations().stream())
                .forEach(graph::addVertex);
    }

    private void addEdges(List<Line> lines) {
        lines.stream()
                .flatMap(it -> it.getSections().stream())
                .forEach(this::addEdge);
    }

    private void addEdge(Section section) {
        graph.setEdgeWeight(graph.addEdge(section.getUpStation(), section.getDownStation()), section.getDistance());
    }

    private GraphPath<Station, DefaultWeightedEdge> findShortestPathFromPaths(Station sourceStation, Station targetStation) {
        return paths.getPaths(sourceStation, targetStation).get(SHORTEST_PATH_INDEX);
    }

    private void validateStations(Station sourceStation, Station targetStation) {
        if (Objects.equals(sourceStation, targetStation)) {
            throw new PathFindFailedException(SAME_STATION_ERROR);
        }
        if (!graph.containsVertex(sourceStation) || !graph.containsVertex(targetStation)) {
            throw new PathFindFailedException(NOT_CONTAINED_STATION_ERROR);
        }
    }
}
