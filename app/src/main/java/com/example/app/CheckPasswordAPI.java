package com.example.app;

public class CheckPasswordAPI {
    public static boolean ckPasswd(String src, String cpasswd){
        boolean isRight=false;
        if (cpasswd.startsWith("$2a$10$")){
            //check 明碼 與 加密過後的碼
            isRight=BCrypt.checkpw(src,cpasswd);
        }
        return  isRight;
    }
}
