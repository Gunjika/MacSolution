package gunjika.varshney.gla.macsolution;

public class Profile {
    private String name;
    private String email;
    private String address;
    private String Contact;
    private String education;
    private String post;
    private String state;
    private String district;
    private String tehsil;
    private String pin;

    public Profile()
    {

    }

    public String getEducation() {
        return education;
    }

    public String getPost() {
        return post;
    }

    public String getState() {
        return state;
    }

    public String getDistrict() {
        return district;
    }

    public String getTehsil() {
        return tehsil;
    }

    public String getPin() {
        return pin;
    }

    public Profile(String name, String email, String address, String contact, String education, String post, String state, String district, String tehsil, String pin) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.Contact = contact;
        this.education=education;
        this.post=post;
        this.state=state;
        this.district=district;
        this.tehsil=tehsil;
        this.pin=pin;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }




}
