package ar.edu.itba.TP4_Oscillator;

public class Particle {
	final int id;
	final double r;

	double x;
	double y;
	double prevX;
	double prevY;

	double speedX;
	double speedY;

	double prevSpeedX;
	double prevSpeedY;

	final double mass;

	public Particle(int id, double r, double x, double y, double prevX, double prevY, double speedX, double speedY,
			double prevSpeedX, double prevSpeedY, double mass) {
		super();
		this.id = id;
		this.r = r;
		this.x = x;
		this.y = y;
		this.prevX = prevX;
		this.prevY = prevY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.prevSpeedX = prevSpeedX;
		this.prevSpeedY = prevSpeedY;
		this.mass = mass;
	}

	public Particle(int id, double r, double x, double y, double prevX, double prevY, double speedX, double speedY,
			double mass) {
		super();
		this.id = id;
		this.r = r;
		this.x = x;
		this.y = y;
		this.prevX = prevX;
		this.prevY = prevY;
		this.speedX = speedX;
		this.speedY = speedY;
		this.mass = mass;
	}

	public Particle(int id, double r, double x, double y, double speedX, double speedY, double mass) {
		super();
		this.id = id;
		this.r = r;
		this.x = x;
		this.y = y;
		this.speedX = speedX;
		this.speedY = speedY;
		this.mass = mass;
	}

	public Particle(int id, double r, double prevX, double prevY, double mass) {
		super();
		this.id = id;
		this.r = r;
		this.prevX = prevX;
		this.prevY = prevY;
		this.mass = mass;
	}


	@Override
	public String toString() {
		return String.format("[(%f, %f) - (%f, %f) - %f]", x, y, speedX, speedY, r);
	}
}