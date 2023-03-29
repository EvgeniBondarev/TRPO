public abstract class Detail {
    protected String form;
    protected String material;
    protected double weight;
    protected double size;

    public Detail(String form, String material, double weight, double size) {
        this.form = form;
        this.material = material;
        this.weight = weight;
        this.size = size;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public abstract String getDetailType();
}