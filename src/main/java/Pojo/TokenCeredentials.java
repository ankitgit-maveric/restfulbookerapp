package Pojo;

import java.util.HashMap;
import java.util.Map;

public class TokenCeredentials {

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;
    private String password;






    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }
/*
    public Map<String, String> JsonTokenCreation(){
        Map<String, String> authPayLoad= new HashMap<String, String>();
        authPayLoad.put("username",userName);
        authPayLoad.put("password",password);
        return authPayLoad;

    }*/

}
