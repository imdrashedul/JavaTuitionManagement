package library;

public class JcbItem<T> {
	private T key;
    private String value;

    public JcbItem(String value, T key)
    {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }

    public String getValue()
    {
        return value;
    }

    public T getKey()
    {
        return key;
    }
}