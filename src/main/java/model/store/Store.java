package model.store;

import model.Default;

import java.sql.Timestamp;

public class Store extends Default {
    // 属性
    private String name;

    // コンストラクタ
    public Store(Integer id, String name, Timestamp createdAt, Timestamp updatedAt){
        super(id, createdAt, updatedAt);
        this.name = name;
    }

    // setメソッド
    public void setName(String name) { this.name = name; }

    // getメソッド
    public String getName() { return this.name; }

    public void registStore(){
        StoreDAO.registStore(this);
    }
}