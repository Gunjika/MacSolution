package gunjika.varshney.gla.macsolution;

public class UserInformation {
    public String name;
    public String latitude;
    public String longitude;
    public UserInformation()
    {

    }

    public UserInformation(String username, String latitude, String longitude){
        this.name=username;
        this.latitude= latitude;
        this.longitude= longitude;

    }
}
