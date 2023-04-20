public class Nut extends Detail {
    private String threadSize;

    public Nut(String form, String material, double weight, double size, String threadSize) {
        super(form, material, weight, size);
        this.threadSize = threadSize;
    }

    public String getThreadSize() {
        return threadSize;
    }

    public void setThreadSize(String threadSize) {
        this.threadSize = threadSize;
    }

    @Override
    public String getDetailType() {
        return "Гайка";
    }


}
