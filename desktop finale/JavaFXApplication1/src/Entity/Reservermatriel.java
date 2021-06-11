/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author HP
 */
public class Reservermatriel {
    public int id;
    public String staff_name;
     public String matriel_type;
    public Date date_res;
    public Date date_ret;
    public int ids;
    public int idm;

    public Reservermatriel(int idm,int ids,Date date_res, Date date_ret ) {
        this.date_res = date_res;
        this.date_ret = date_ret;
        this.ids = ids;
        this.idm = idm;
    }

    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }

    public int getIdm() {
        return idm;
    }

    public void setIdm(int idm) {
        this.idm = idm;
    }
    
    public Reservermatriel(String staff_id, String matriel_id, Date date_res, Date date_ret) {
        this.staff_name = staff_id;
        this.matriel_type = matriel_id;
        this.date_res = date_res;
        this.date_ret = date_ret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_id) {
        this.staff_name = staff_id;
    }

    public String getMatriel_type() {
        return matriel_type;
    }

    public void setMatriel_type(String matriel_id) {
        this.matriel_type= matriel_id;
    }

    public Date getDate_res() {
        return date_res;
    }

    public void setDate_res(Date date_res) {
        this.date_res = date_res;
    }

    public Date getDate_ret() {
        return date_ret;
    }

    public void setDate_ret(Date date_ret) {
        this.date_ret = date_ret;
    }

    
    
}
