package pl.bialekkostrzewa.model;

public class Client extends User{

    private ClientType type;
    //private String typeName;

    public Client(){
        type = new NormalClient();
    }

    public Client(String login, String password, String name, String surname, ClientType type) {
        super(login, password, name, surname);
        this.type = type;
    }

    @Override
    public String toString() {
        String tmp;
        if(isActive()){
            tmp = "is active";
        }
        else tmp = "is inactive";
        return "Client login " + getLogin() + " full name " + getName()
                + " " + getSurname() + " " + getType() + " type " + tmp;
        /*return "Client{" +
                "login='" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", type=" + type + '\'' +
                ", isActive='" + isActive() + '\'' +
                '}';*/
    }

    public ClientType getType() {
        return type;
    }

    public void setType(ClientType type) {
        this.type = type;
    }

    public double getDiscount(double base){
        return type.countDiscount(base);
    }
}
