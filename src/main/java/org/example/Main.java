package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        private static Connection getConnection() throws URISyntaxException, SQLException {
           String dbUrl = System.getenv("postgres://kexafudwppoppl:c0abaa9ed698fdce77c4c79079ca966d7b06eb9f7a524cb3db5a90faf9c8eb6c@ec2-54-147-36-107.compute-1.amazonaws.com:5432/deum74j36kqraj");
           return DriverManager.getConnection(dbUrl);
        }
    }
}
