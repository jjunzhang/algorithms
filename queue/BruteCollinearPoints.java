import java.util.ArrayList;
import java.util.Arrays;


/**
 * A BruteForce solution for finding collinear points in a set of points.
 * @author ISchwarz
 */
public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException("Duplicated entries found.");
                }
            }
        }
        ArrayList<LineSegment> foundSegments = new ArrayList<LineSegment>();
        Point[] pointsC = Arrays.copyOf(points, points.length);
        Arrays.sort(pointsC);

        for (int p = 0; p < pointsC.length - 3; p++) {
            for (int q = p + 1; q < pointsC.length - 2; q++) {
                for (int r = q + 1; r < pointsC.length - 1; r++) {
                    for (int s = r + 1; s < pointsC.length; s++) {
                        if (pointsC[p].slopeTo(pointsC[q]) == pointsC[p].slopeTo(pointsC[r]) &&
                                pointsC[p].slopeTo(pointsC[q]) == pointsC[p].slopeTo(pointsC[s])) {
                            foundSegments.add(new LineSegment(pointsC[p], pointsC[s]));
                        }
                    }
                }
            }
        }
        segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }
    
}
