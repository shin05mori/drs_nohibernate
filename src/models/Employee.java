package models;

import java.sql.Timestamp;

public class Employee {
    private Integer id;
    private String code;
    private String name;
    private String password;
    private Integer admin_flag;
    private Timestamp created_at;
    private Timestamp updated_at;
    private Integer delete_flag;
    private int count;
    private int follow_id;

    public Employee() {
    }

    public Employee(String code, String name) {
        this.code = code;
        this.name = name;
    }



    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Integer getAdmin_flag() {
        return admin_flag;
    }
    public void setAdmin_flag(Integer admin_flag) {
        this.admin_flag = admin_flag;
    }
    public Timestamp getCreated_at() {
        return created_at;
    }
    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    public Timestamp getUpdated_at() {
        return updated_at;
    }
    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }
    public Integer getDelete_flag() {
        return delete_flag;
    }
    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFollow_id() {
        return follow_id;
    }

    public void setFollow_id(int follow_id) {
        this.follow_id = follow_id;
    }

}
