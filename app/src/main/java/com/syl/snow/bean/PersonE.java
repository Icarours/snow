package com.syl.snow.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by Bright on 2019/2/22.
 *
 * @Describe 部门信息和人员信息的并集
 * http://cloud.lanlyc.cn/lanlyc_gongdi/persDepartment/getPersonAndSubDepartmentByCon?paramJson={"departmentId":-1,"mobile":"15989469069"}
 *
 * http://cloud.lanlyc.cn/lanlyc_gongdi/persPerson/getPersonListByCondition?paramJson={"mobile":"15989469069"}
 * @Called
 */
public class PersonE implements Serializable,MultiItemEntity {

    /**
     * person_id : 328
     * person_name : 黄冠
     * mobile_phone : 15927055037
     * photo_path : http://59.175.153.46:8068/EmpPicUpload/NewPersonImg/MbGSx_1538970397179(1)20181121165948.jpg
     * pin : 3000004
     * gender : M
     * birthday : 727286400000
     * dept_id : 77
     * parent_id : 75
     * department_name : 研发部
     * card_no : null
     * cert_type : 2
     * cert_number : 421381199301182851
     * workname : 电工
     */

    private String person_id;
    private String person_name;
    private String mobile_phone;
    private String photo_path;
    private String pin;
    private String gender;
    private String birthday;
    private String dept_id;
    private String parent_id;
    private String card_no;
    private String cert_type;
    private String cert_number;
    private String workname;//工种
    private String department_id;
    private String department_name;
    private String person_counts;
    private boolean father_node;//是否有父节点
    private boolean son_node;//是否有子节点
    private boolean checked;//是否被选中
    private int itemType;//条目类型

    public PersonE() {
    }

    @Override
    public String toString() {
        return "PersonE{" +
                "person_id='" + person_id + '\'' +
                ", person_name='" + person_name + '\'' +
                ", mobile_phone='" + mobile_phone + '\'' +
                ", photo_path='" + photo_path + '\'' +
                ", pin='" + pin + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", dept_id='" + dept_id + '\'' +
                ", parent_id='" + parent_id + '\'' +
                ", card_no='" + card_no + '\'' +
                ", cert_type='" + cert_type + '\'' +
                ", cert_number='" + cert_number + '\'' +
                ", workname='" + workname + '\'' +
                ", department_id='" + department_id + '\'' +
                ", department_name='" + department_name + '\'' +
                ", person_counts='" + person_counts + '\'' +
                ", father_node=" + father_node +
                ", son_node=" + son_node +
                ", checked=" + checked +
                ", itemType=" + itemType +
                '}';
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getDept_id() {
        return dept_id;
    }

    public void setDept_id(String dept_id) {
        this.dept_id = dept_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getCard_no() {
        return card_no;
    }

    public void setCard_no(String card_no) {
        this.card_no = card_no;
    }

    public String getCert_type() {
        return cert_type;
    }

    public void setCert_type(String cert_type) {
        this.cert_type = cert_type;
    }

    public String getCert_number() {
        return cert_number;
    }

    public void setCert_number(String cert_number) {
        this.cert_number = cert_number;
    }

    public String getWorkname() {
        return workname;
    }

    public void setWorkname(String workname) {
        this.workname = workname;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getPerson_counts() {
        return person_counts;
    }

    public void setPerson_counts(String person_counts) {
        this.person_counts = person_counts;
    }

    public boolean isFather_node() {
        return father_node;
    }

    public void setFather_node(boolean father_node) {
        this.father_node = father_node;
    }

    public boolean isSon_node() {
        return son_node;
    }

    public void setSon_node(boolean son_node) {
        this.son_node = son_node;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }
}
