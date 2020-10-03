package practice;

/**
 * Se dau coordonatele a 3 puncte în planul real (R^2). Coordonatele sunt de tip
 * întreg.
 * 
 * a) metodă care determină dacă cele 3 puncte formează un triunghi.
 * 
 * b) metodă care afişează toate punctele de coordonate întregi care sunt în
 * interiorul triunghiului.
 */
public class TriangleCoordinates
{
    private static class Point
    {
        public int x;
        public int y;

        public Point(int x, int y)
        {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args)
    {
        Point A = new Point(0, 0);
        Point B = new Point(3, 2);
        Point C = new Point(4, 0);

        double ABCArea = getTriangleArea(A, B, C);
        System.out.println("Full triangle area: " + ABCArea);
        System.out.println(String.format("It is %s that the points form a triangle.", ABCArea > 0));

        System.out.println("=======================");

        /*
         * Show points that are within triangle.
         * 
         * The X axis point must lie between A and C.
         */
        for (int x = A.x; x <= C.x; x++) {
            /*
             * The Y axis point cannot be larger than B.
             */
            for (int y = 0; y <= B.y; y++) {
                System.out.println(String.format("Point %d %d ... ", x, y));
                Point P = new Point(x, y);
                double PABArea = getTriangleArea(P, A, B);
                System.out.println("Partial area: " + PABArea);
                double PBCArea = getTriangleArea(P, B, C);
                System.out.println("Partial area: " + PBCArea);
                double PACArea = getTriangleArea(P, A, C);
                System.out.println("Partial area: " + PACArea);

                double totalArea = PABArea + PBCArea + PACArea;
                System.out.println("Total area: " + totalArea);

                if (totalArea == ABCArea) {
                    System.out.println(String.format("Point %d %d is found inside the triangle!", x, y));
                }
                
                System.out.println("=======================");
            }
        }
    }

    private static double getTriangleArea(Point A, Point B, Point C)
    {
        return Math.abs((A.x * (B.y - C.y) + B.x * (C.y - A.y) + C.x * (A.y - B.y)) * 0.5);
    }
}
