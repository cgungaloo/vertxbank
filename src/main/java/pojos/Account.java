package pojos;
                 
public class Account
{
    private String name;

    private String id;

    private String Balance;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getBalance ()
    {
        return Balance;
    }

    public void setBalance (String Balance)
    {
        this.Balance = Balance;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+", id = "+id+", Balance = "+Balance+"]";
    }
}