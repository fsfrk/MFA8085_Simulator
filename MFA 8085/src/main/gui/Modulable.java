package main.gui;

public interface Modulable<T> {
    /**
     * 
     * @param value
     *            set the value of this Modul
     */
    public void setValue(T value);

    /**
     * 
     * @return the Value of this Modul
     */
    public T getValue();

    /**
     * 
     * @return Name of Modul
     */
    public String getName();
}
