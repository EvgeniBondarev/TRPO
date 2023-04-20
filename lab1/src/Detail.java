public abstract class Detail {
    protected String form;
    protected String material;
    protected double weight;
    protected double size;

    /**
     *
     * @param form - форма детали, заданная в виде строки.
     * @param material - материал, из которого изготовлена деталь, заданный в виде строки.
     * @param weight - вес детали, заданный в килограммах, тип данных - double.
     * @param size - размер детали, заданный в единицах, определяемых конкретной деталью, тип данных - double.
     */
    public Detail(String form, String material, double weight, double size) {
        this.form = form;
        this.material = material;
        this.weight = weight;
        this.size = size;
    }

    public Detail Clone(){
        return this;
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
    public abstract String getThreadSize();
    public abstract void setThreadSize(String model);
}