public class Vector2 {
    public double x;
    public double y;

    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }
    static double DotProduct(Vector2 a, Vector2 b){
        return (a.x * b.x) + (a.y*b.y);
    }
    static Vector2 Distance(Vector2 a, Vector2 b){
        return new Vector2(b.x - a.x, b.y-a.y);
    }
    public double Magnitude(){
        return Math.sqrt(x*x + y*y);
    }

    public Vector2 Normalized(){
        var magnitude = Magnitude();
        return new Vector2(this.x / magnitude, this.y / magnitude);
    }
}
