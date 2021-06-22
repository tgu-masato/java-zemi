package model.user;

import model.Default;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

public class User extends Default {
    
    // 属性
    private String name;
    private String email;
    private String password;

    // コンストラクタ
    public User(Integer id, String name, String email, String password, Timestamp createdAt, Timestamp updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // setメソッド
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    // getメソッド
    public String getName() { return this.name; }
    public String getEmail() { return this.email; }
    public String getPassword() { return this.password; }

    //controller/User/SignUpUser.javaからの呼び出し
    public void registUser(){
        this.hashPassword();
        UserDAO.registUser(this);
    }
//    //User認証の機構
//    public boolean authenticateUser(HttpServletRequest request) {
//        //Mailをもとにユーザーが存在するか調べる
//        User persistedUser = UserDAO.selectUserByMail(this.mail);
//        if (persistedUser == null) {    //Mailをもつユーザーがいなければ
//            return false;
//        }
//        //ここからはMailをもつユーザーがいればの話
//        this.hashPassword();    //入力されたパスワードをハッシュ化
//        if (this.password.equals(persistedUser.password)) { //ハッシュ化したものとDBのパスワードが一致すれば
//            HttpSession session = request.getSession(); //セッションを作って
//            session.setAttribute(currentUserKey, persistedUser);    //セッションスコープにユーザー情報保存
//            return true;
//        } else {    //パスワードが違ったらfalseを返す
//            return false;
//        }
//    }
    //ハッシュ化
    public void hashPassword(){
        this.password=getHash(this.email,this.password);
    }
    private String getHash(String email, String password) {
        final String HASH_ALGORITHM = "SHA-256";
        final int STRETCH_COUNT = 1024;
        final String FIXED_SALT = "vBjRGHZ6awqJL9JDQuNftAzaPSnHszQN";
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(HASH_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String salt = email + FIXED_SALT;
        messageDigest.update(salt.getBytes());
        byte[] hashed = messageDigest.digest(password.getBytes());
        for (int i = 0; i < STRETCH_COUNT; i++) {
            hashed = messageDigest.digest(hashed);
        }
        return getHexString(hashed);
    }
    private String getHexString(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            stringBuffer.append(Integer.toHexString((bytes[i] >> 4) & 0x0f));
            stringBuffer.append(Integer.toHexString(bytes[i] & 0x0f));
        }
        return stringBuffer.toString();
    }
//    public static User getCurrentUser(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        return (User) session.getAttribute(currentUserKey);
//    }
//    //セッションスコープからcurrentUserKeyを取り除く
//    public static void logoutUser(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        session.removeAttribute(currentUserKey);
//    }
}
