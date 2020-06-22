package Model;

public class Listitem {


    private String name;
    private String description;
    private String reduction;
    private int image;

    public  Listitem (String name,String description,String reduction,int image){
      this.name=name;
      this.description=description;
      this.reduction=reduction;
      this.image=image;

    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getReduction() {
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
