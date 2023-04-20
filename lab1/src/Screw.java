public class Screw extends Detail {
    private String threadSize;
    private double length;

    public Screw(String form, String material, double weight, double size, String threadSize, double length) {
        super(form, material, weight, size);
        this.threadSize = threadSize;
        this.length = length;
    }

    public String getThreadSize() {
        return threadSize;
    }

    public void setThreadSize(String threadSize) {
        this.threadSize = threadSize;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    @Override
    public String getDetailType() {
        return "Винт";
    }
}