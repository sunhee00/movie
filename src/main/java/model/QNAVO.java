package model;

import java.sql.Date;

public class QNAVO {
   private int qnum;
   private String id;
   private String qtitle;
   private String qcontent;
   private Date qdate;
   private int q_ref;
   private int q_re_step;
   private int q_re_level;
   private int readcount;
   
   public int getQnum() {
      return qnum;
   }
   public void setQnum(int qnum) {
      this.qnum = qnum;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public String getQtitle() {
      return qtitle;
   }
   public void setQtitle(String qtitle) {
      this.qtitle = qtitle;
   }
   public String getQcontent() {
      return qcontent;
   }
   public void setQcontent(String qcontent) {
      this.qcontent = qcontent;
   }
   public Date getQdate() {
      return qdate;
   }
   public void setQdate(Date qdate) {
      this.qdate = qdate;
   }
   public int getQ_ref() {
      return q_ref;
   }
   public void setQ_ref(int q_ref) {
      this.q_ref = q_ref;
   }
   public int getQ_re_step() {
      return q_re_step;
   }
   public void setQ_re_step(int q_re_step) {
      this.q_re_step = q_re_step;
   }
   public int getQ_re_level() {
      return q_re_level;
   }
   public void setQ_re_level(int q_re_level) {
      this.q_re_level = q_re_level;
   }
   public int getReadcount() {
      return readcount;
   }
   public void setReadcount(int readcount) {
      this.readcount = readcount;
   }
   
   @Override
   public String toString() {
      return "QNAVO [qnum=" + qnum +", id=" + id +", qtitle=" + qtitle + ", qcontent=" + qcontent + ", qdate=" + qdate +
            ", q_ref=" + q_ref + ", q_re_step=" + q_re_step + ", q_re_level=" + q_re_level + ", readcount=" + readcount +"]";
   }
}