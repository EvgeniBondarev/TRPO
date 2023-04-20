public class Bolt extends Detail {
    private String threadSize;

    public Bolt(String form, String material, double weight, double size, String threadSize) {
        super(form, material, weight, size);
        this.threadSize = threadSize;
        super.size = size;
    }

    public String getThreadSize() {
        return threadSize;
    }

    public void setThreadSize(String threadSize) {
        this.threadSize = threadSize;
    }

    @Override
    public String getDetailType() {
        return "Болт";
    }
}