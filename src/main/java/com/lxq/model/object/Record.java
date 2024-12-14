package com.lxq.model.object;


//考勤
public class Record {
    //唯一ID
    private String id;
    //学生ID
    private String userId;
    //教师ID
    private String teacherId;
    //课程ID
    private String curriculumId;
    //申请内容
    private String content;
    //申请状态
    private String state;
    //反馈备注
    private String remarks;
    //考勤日期
    private String time;

    private Integer timeNum;
    private Integer timeNumMax;
    private Integer timeNumMin;

    private User studentMessage;
    private User teacherMessage;
    private Curriculum curriculumMessage;

    public Integer getTimeNumMax() {
        return timeNumMax;
    }

    public void setTimeNumMax(Integer timeNumMax) {
        this.timeNumMax = timeNumMax;
    }

    public Integer getTimeNumMin() {
        return timeNumMin;
    }

    public void setTimeNumMin(Integer timeNumMin) {
        this.timeNumMin = timeNumMin;
    }

    public Integer getTimeNum() {
        return timeNum;
    }

    public void setTimeNum(Integer timeNum) {
        this.timeNum = timeNum;
    }

    public User getStudentMessage() {
        return studentMessage;
    }

    public void setStudentMessage(User studentMessage) {
        this.studentMessage = studentMessage;
    }

    public User getTeacherMessage() {
        return teacherMessage;
    }

    public void setTeacherMessage(User teacherMessage) {
        this.teacherMessage = teacherMessage;
    }

    public Curriculum getCurriculumMessage() {
        return curriculumMessage;
    }

    public void setCurriculumMessage(Curriculum curriculumMessage) {
        this.curriculumMessage = curriculumMessage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(String curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
