package practice;

/**
 * Se dă un cerc prin coordonatele centrului C(x0, y0) şi raza r.
 * 
 * a)metodă care determină şi afişează toate punctele de coordonate întregi din
 * interiorul cercului dat.
 * 
 * b)metodă care determină coordonatele întregi ale domeniului de intersecţie a
 * două cercuri.
 */
public class Circle
{
    public static void main(String[] args) {
        printPointsWithinCircle(0, 0, 30);
    }
    
    private static void printPointsWithinCircle(int xCenter, int yCenter, int radius)
    {
        for (int x = xCenter - radius; x <= xCenter; x++) {
            for (int y = yCenter - radius ; y <= yCenter; y++) {
                boolean pointIsWithinBounds = (x - xCenter) * (x - xCenter) + (y - yCenter) * (y - yCenter) <= radius * radius;
                
                if (pointIsWithinBounds) {
                    // (x, y), (-x, y), (x, -y), (-x, -y) are in the circle
                    int xSym = xCenter - (x - xCenter);
                    int ySym = yCenter - (y - yCenter);
                    
                    System.out.println(String.format("Points (%d, %d), (%d, %d), (%d, %d), (%d, %d) are within the circle.", x, y, x, ySym, xSym, y, xSym, ySym));
                }
            }
        }
    }
}
