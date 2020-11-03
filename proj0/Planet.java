public class Planet {
    public double xxPos;   // current x position.
    public double yyPos;   // current y position.
    public double xxVel;   // current velocity in the x direction.
    public double yyVel;   // current velocity in the y direction.
    public double mass;    // mass.
    public String imgFileName;     // The name of the file that corresponds to the image that depicts the planet.


    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }


    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }


    // 1. Calculates the distance between two Planets.
    public double calcDistance(Planet p1) {
        double dx = p1.xxPos - this.xxPos;
        double dy = p1.yyPos - this.yyPos;
        return Math.hypot(dx, dy);  // return sqrt(x^2 + y^2)
    }


    // 2. Calculates the pairwise Force between two Planets.
    public double calcForceExertedBy(Planet p1) {
        double G = 6.67e-11;
        double r = this.calcDistance(p1);
        return (G * this.mass * p1.mass) / Math.pow(r, 2);
    }


    // 3.1 Calculates the force exerted in the X directions.
    public double calcForceExertedByX(Planet p1) {
        double dx = p1.xxPos - this.xxPos;
        double r = this.calcDistance(p1);
        double F = this.calcForceExertedBy(p1);
        return (F * dx) / r;
    }


    // 3.2 Calculates the force exerted in the Y directions.
    public double calcForceExertedByY(Planet p1) {
        double dy = p1.yyPos - this.yyPos;
        double r = this.calcDistance(p1);
        double F = this.calcForceExertedBy(p1);
        return (F * dy) / r;
    }


    // 4.1 Calculates the net X force.
    public double calcNetForceExertedByX(Planet[] planets) {
        double FxNet = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                FxNet += this.calcForceExertedByX(p);
            }
        }
        return FxNet;
    }


    // 4.2 Calculates the net Y force.
    public double calcNetForceExertedByY(Planet[] planets) {
        double FyNet = 0.0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                FyNet += this.calcForceExertedByY(p);
            }
        }
        return FyNet;
    }


    // 5. Update the position and velocity.
    public void update(double dt, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        this.xxVel += dt * ax;
        this.yyVel += dt * ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }


    // 6. Draw the planet.
    public void draw() {
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}
