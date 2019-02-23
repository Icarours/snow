package com.syl.snow.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Bright on 2019/2/22.
 *
 * @Describe 部门信息
 * http://cloud.lanlyc.cn/lanlyc_gongdi/persDepartment/getPersonAndSubDepartmentByCon?paramJson={"departmentId":-1,"mobile":"15989469069"}
 * @Called
 */
public class DepartmentE implements Serializable {

    /**
     * sub_department_list : [{"department_id":75,"department_name":"项目部","person_counts":44},{"department_id":76,"department_name":"施工队","person_counts":19}]
     * person_counts : 0
     * person_list : []
     * sub_department_counts : 2
     */

    private int person_counts;
    private int sub_department_counts;
    private List<PersonE> sub_department_list;
    private List<PersonE> person_list;

    public DepartmentE() {
    }

    @Override
    public String toString() {
        return "DepartmentE{" +
                "person_counts=" + person_counts +
                ", sub_department_counts=" + sub_department_counts +
                ", sub_department_list=" + sub_department_list +
                ", person_list=" + person_list +
                '}';
    }

    public int getPerson_counts() {
        return person_counts;
    }

    public void setPerson_counts(int person_counts) {
        this.person_counts = person_counts;
    }

    public int getSub_department_counts() {
        return sub_department_counts;
    }

    public void setSub_department_counts(int sub_department_counts) {
        this.sub_department_counts = sub_department_counts;
    }

    public List<PersonE> getSub_department_list() {
        return sub_department_list;
    }

    public void setSub_department_list(List<PersonE> sub_department_list) {
        this.sub_department_list = sub_department_list;
    }

    public List<PersonE> getPerson_list() {
        return person_list;
    }

    public void setPerson_list(List<PersonE> person_list) {
        this.person_list = person_list;
    }
}
