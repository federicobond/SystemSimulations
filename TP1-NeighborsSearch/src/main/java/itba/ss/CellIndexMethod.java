package itba.ss;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * Example input:
10
1
0.25
4
1 1 1
1 1 1
1 1 1
1 1 1
1
 */

public class CellIndexMethod {

	Cell[][] matrix;
	Map<Particle, Point> allParticles;
	double L;
	double rc;
	double cellLen;
	int N;
	int M;
	boolean contour;

	public CellIndexMethod(int L, int M, double rc, boolean contour) {
		this.L = L;
		this.rc = rc;
		this.contour = contour;
		this.M = M;
	}

	private void addNeighbors(Cell c, Particle p, Map<Particle, Set<Particle>> m, int deltaX, int deltaY) {
		for (Particle candidate : c.set) {
			if (!candidate.equals(p)) {
				if (!m.get(p).contains(candidate)) {
					double distance = Math.sqrt(Math.pow(p.x - (candidate.x + deltaX * L), 2)
							+ Math.pow(p.y - (candidate.y + deltaY * L), 2)) - p.r - candidate.r;
					if (distance <= rc) {
						m.get(p).add(candidate);
						if (!m.containsKey(candidate))
							m.put(candidate, new HashSet<>());
						m.get(candidate).add(p);
					}
				}
			}
		}
	}

	public Map<Particle, Set<Particle>> findNeighbors() {
		Map<Particle, Set<Particle>> map = new HashMap<>();

		for (Particle particle : allParticles.keySet()) {
			if (!map.containsKey(particle))
				map.put(particle, new HashSet<>());
			Point coords = allParticles.get(particle);
			Cell aux;

			aux = matrix[coords.x][coords.y];
			addNeighbors(aux, particle, map, 0, 0);

			aux = matrix[(coords.x - 1) % M][coords.y];
			if (coords.x - 1 >= 0) {
				addNeighbors(aux, particle, map, 0, 0);
			} else if (contour) {
				addNeighbors(aux, particle, map, -1, 0);
			}

			aux = matrix[(coords.x - 1) % M][(coords.y + 1) % M];
			if (coords.x - 1 >= 0 && coords.y + 1 < M) {
				addNeighbors(aux, particle, map, 0, 0);
			} else if (contour) {
				addNeighbors(aux, particle, map, coords.x - 1 >= 0 ? 0 : -1, coords.y + 1 < M ? 0 : -1);
			}

			aux = matrix[coords.x][(coords.y + 1) % M];
			if (coords.y + 1 < M) {
				addNeighbors(aux, particle, map, 0, 0);
			} else if (contour) {
				addNeighbors(aux, particle, map, 0, -1);
			}

			aux = matrix[(coords.x + 1) % M][(coords.y + 1) % M];
			if (coords.x + 1 < M && coords.y + 1 < M) {
				addNeighbors(aux, particle, map, 0, 0);
			} else if (contour) {
				addNeighbors(aux, particle, map, coords.x + 1 < M ? 0 : 1, coords.y + 1 < M ? 0 : -1);
			}

		}

		return map;
	}

	private double getDistance(Particle fixed, Particle moving, int deltaX, int deltaY) {
		return Math
				.sqrt(Math.pow(fixed.x - (moving.x + deltaX * L), 2) + Math.pow(fixed.y - (moving.y + deltaY * L), 2))
				- fixed.r - moving.r;
	}

	public Map<Particle, Set<Particle>> findNeighborsBruteForce() {
		Map<Particle, Set<Particle>> map = new HashMap<>();

		for (Particle particle : allParticles.keySet()) {
			if (!map.containsKey(particle))
				map.put(particle, new HashSet<>());

			for (Particle particle2 : allParticles.keySet()) {
				if (!particle.equals(particle2) && !map.get(particle).contains(particle2)) {
					double distance = getDistance(particle, particle2, 0, 0);

					if (contour) {
						double distance1, distance2, distance3, distance4;
						distance1 = getDistance(particle, particle2, -1, 0);
						distance2 = getDistance(particle, particle2, 1, 0);
						distance3 = getDistance(particle, particle2, 0, -1);
						distance4 = getDistance(particle, particle2, 0, 1);

						double min = Math.min(distance1, Math.min(distance2, Math.min(distance3, distance4)));

						distance = Math.min(distance, min);
					}

					if (distance <= rc) {
						map.get(particle).add(particle2);
						if (!map.containsKey(particle2))
							map.put(particle2, new HashSet<>());
						map.get(particle2).add(particle);
					}
				}
			}
		}
		return map;
	}

}