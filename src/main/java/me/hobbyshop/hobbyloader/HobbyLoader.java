package me.hobbyshop.hobbyloader;

public class HobbyLoader {

    private static HobbyLoader instance;

    public void start() {
        System.out.println("Hello from the other side");
    }

    public void stop() {

    }

    public static HobbyLoader getInstance() {
        if(instance == null)
            instance = new HobbyLoader();

        return instance;
    }
}
