//Najszybszym i najbardziej optymalnym sposobem byloby u¿ycie struktury tylko do odczytu, ale w Javie nie sa obs³ugiwane
public class ImportLine {
	public double x;
	public double y;
	
	public ImportLine(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return String.format(x + ", " + y);
	}
}
