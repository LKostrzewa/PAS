package pl.bialekkostrzewa.model;

public class Client extends User{

    private ClientType type;

    public Client(){
        type = new NormalClient();
    }

    public Client(String login, String name, String surname, ClientType type) {
        super(login, name, surname);
        this.type = type;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + getLogin() + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", type=" + type +
                '}';
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
