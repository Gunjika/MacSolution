package gunjika.varshney.gla.macsolution;

public class uploadPdf {

    String namee;
    String url;

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public uploadPdf() {
    }


    public uploadPdf(String name, String url) {
        this.namee = name;
        this.url = url;
    }
    public String getNamee() {
        return namee;
    }

    public String getUrl() {
        return url;
    }

}
