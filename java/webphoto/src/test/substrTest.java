package test;

public class substrTest
{
    public static void main(String[] args)
    {
        String uri = "http://localhost:8080/webphoto/Index";
        System.out.println(uri.substring(uri.length()-4));
        System.out.println(uri.substring(uri.lastIndexOf("/")));
    }
}
