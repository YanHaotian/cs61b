import javax.swing.*;

/**
 * NBody is a class that will actually run your simulation.
 */
public class NBody {
    /**
     * Given a file name, it should return a double
     * corresponding to the radius of the universe
     * in "./data/planets.txt".
     */
    public static double readRadius(String path) {
        In in = new In(path);
        int number = in.readInt();
        return in.readDouble();
    }


    /**
     * Given a file name, it should return an array of Planets
     * corresponding to the planets in "./data/planets.txt".
     */
    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int number = in.readInt();
        double radius = in.readDouble();
        Planet[] planets = new Planet[number];
        for (int i = 0; i < number; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }


    /**
     * COMPILE and RUN in command line:
     *     javac NBody.java
     *     java NBody 157788000.0 25000.0 data/planets.txt
     */
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        // Read the radius & background picture.
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // Set the background picture.
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");


        // Draw 5 planets in the beginning.
        for (Planet p : planets) {
            p.draw();
        }

        // A graphics technique to prevent flickering in the animation.
        StdDraw.enableDoubleBuffering();


        // Update the animation.
        for (double t = 0; t < T; t += dt) {
            // At every time step, the net force of each planet need to be calculated.
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            // Update the next position and velocity using dt, net X force and net Y force.
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // The background need to be draw before the next planets draw.
            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet planet : planets) {
                planet.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : planets) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos,
                    planet.xxVel, planet.yyVel,
                    planet.mass, planet.imgFileName);
        }
    }
}
